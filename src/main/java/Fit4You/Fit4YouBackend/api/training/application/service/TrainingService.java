package Fit4You.Fit4YouBackend.api.training.application.service;

import Fit4You.Fit4YouBackend.api.training.application.port.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.ExercisePort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.TrainingPort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.WorkoutPort;
import Fit4You.Fit4YouBackend.api.training.domains.Exercise;
import Fit4You.Fit4YouBackend.api.member.domains.Member;
import Fit4You.Fit4YouBackend.api.training.domains.Training;
import Fit4You.Fit4YouBackend.api.training.domains.Workout;
import Fit4You.Fit4YouBackend.api.training.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.training.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.training.dto.response.TrainingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingUseCase {

    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;
    private final ExercisePort exercisePort;
    private final LoadMemberPort loadMemberPort;

    private final PriorityService priorityService;
    private static final Integer workoutEa = 3;

    @Override
    @Transactional
    public TrainingResponse createTraining(TrainingCreate trainingCreate) {
        Member member = loadMemberPort.loadMember(trainingCreate.getEmail());
        List<Exercise> exercises = getExercisesById(trainingCreate.getSelects());

        Training training = Training.builder()
                .member(member)
                .workoutEa(workoutEa)
                .build();
        Long trainingId = trainingPort.create(training);



        return TrainingResponse.builder()
                .workoutIds(createWorkout(exercises, training))
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

        List<Exercise> exercises = getExercisesByPriority(member);

        return TrainingResponse.builder()
                .workoutIds(createWorkout(exercises, training))
                .build();
    }

    public List<Exercise> getExercisesById(List<Long> selects) {

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


        List<Exercise> exercises = exercisePort.getAll();
        //우선순위에 따라 정렬된 운동 리스트 반환
        priorityService.sortByPriority(member, exercises);

        return exercises;
    }


    private List<Long> createWorkout(List<Exercise> exercises, Training training) {
        ArrayList<Long> workouts = new ArrayList<>();
        for (int i = 0; i < workoutEa; i++) {
            Exercise exercise = exercises.get(i);
            Workout workout = Workout.builder()
                    .training(training)
                    .exercise(exercise)
                    .build();
            workouts.add(workoutPort.create(workout));
        }
        return workouts;
    }



}
