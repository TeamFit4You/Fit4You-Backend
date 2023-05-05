package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.domains.training.Training;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingUseCase {

    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;
    private final ExercisePort exercisePort;
    private final LoadMemberPort loadMemberPort;

    private final Integer workoutEa = 3;

    @Override
    public TrainingResponse createTraining(TrainingCreate trainingCreate) {
        Member member = loadMemberPort.loadMember(trainingCreate.getEmail());
        List<Exercise> exercises = getExercisesById(trainingCreate.getSelects());

        Training training = Training.builder()
                .member(member)
                .workoutEa(workoutEa)
                .build();
        Long trainingId = trainingPort.create(training);

        createWorkout(exercises, training);

        return TrainingResponse.builder()
                .trainingId(trainingId)
                .build();
    }

    @Override
    @Transactional
    public TrainingResponse createRecommend(RecommendCreate recommendCreate) {
        Member member = loadMemberPort.loadMember(recommendCreate.getEmail());

        Training training = Training.builder()
                .member(member)
                .workoutEa(workoutEa)
                .build();
        Long trainingId = trainingPort.create(training);

        List<Exercise> exercises = getExercisesByPriority(member);;//TODO ENUM or 싱글톤으로 관리
        createWorkout(exercises, training);

        return TrainingResponse.builder()
                .trainingId(trainingId)
                .build();
    }

    public List<Exercise> getExercisesById(List<Long> selects) {
        //TODO exercises 및 mapper싱글톤으로 관리
        List<Exercise> exercises = exercisePort.getAll();
        HashMap<Long, Exercise> mapper = new HashMap<>();
        for (Exercise exercise : exercises) {
            mapper.put(exercise.getId(), exercise);
        }

        //
        List<Exercise> results = new ArrayList<>();
        for (Long select : selects) {
            results.add(mapper.get(select));
        }

        return results;
    }

    public List<Exercise> getExercisesByPriority(Member member) {

        // 현재 상태에 따라 가중치 up
        Condition condition = member.getCondition();

        Map<String, Float> weightMap = new HashMap<>();
        weightMap.put("neck",condition.getNeck());
        weightMap.put("shoulder",condition.getShoulder());
        weightMap.put("lumbar",condition.getLumbar());
        weightMap.put("wrist",condition.getWrist());
        weightMap.put("elbow",condition.getElbow());
        weightMap.put("ankle",condition.getAnkle());


        // 과거병력이 있는 경우 가중치 up
        List<MedicalHist> medicalHists = member.getMedicalHists();
        Float histWeight = 1f;
        for (MedicalHist hist : medicalHists) {
            String part = hist.getDisease().getRelatedPart();
            weightMap.put(part,weightMap.get(part)+histWeight);
        }


        // K,V = exercise, Float(운동, 가중치) 으로 hashmap 생성
        List<Exercise> exercises = exercisePort.getAll();
        Map<String, Float> priority = exercises.stream()
                .collect(Collectors.toMap(Exercise::getName, e -> 0f));

        // 가중치를 우선순위에 반영
        for (Exercise e : exercises) {
            Float weight = weightMap.get(e.getDisease().getRelatedPart());
            priority.put(e.getName(), weight);
        }

        // TODO? 최근에 수행했던 운동인 경우 우선순위 소폭 down

        // TODO** 과거병력이 있는 질환과 상극인 운동 우선순위 최하위로 변경 or 운동목록에서 제거
        //선형탐색? 해시?

        //우선순위에 따라 정렬된 운동 리스트 반환
        exercises.sort(getComparator(priority));

        return exercises;
    }
    private void createWorkout(List<Exercise> exercises, Training training) {
        for (int i = 0; i < workoutEa; i++) {
            Exercise exercise = exercises.get(i);
            Workout workout = Workout.builder()
                    .training(training)
                    .exercise(exercise)
                    .build();
            workoutPort.create(workout);//TODO 리스트로 모았다가 한번에 쿼리로 전환
        }
    }
    private static Comparator<Exercise> getComparator(Map<String, Float> priority) {
        Comparator<Exercise> comparator = new Comparator<>() {
            @Override
            public int compare(Exercise o1, Exercise o2) {
                float diff = priority.get(o2.getName()) - priority.get(o1.getName()); // 내림차순
                if (diff < 0) return -1;
                else if (diff == 0) return 0;
                else return 1;
            }
        };
        return comparator;
    }

}
