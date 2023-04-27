package Fit4You.Fit4YouBackend.member.application.services;

import Fit4You.Fit4YouBackend.member.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.member.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.member.domains.Exercise;
import Fit4You.Fit4YouBackend.member.domains.Member;
import Fit4You.Fit4YouBackend.member.domains.Training;
import Fit4You.Fit4YouBackend.member.domains.Workout;
import Fit4You.Fit4YouBackend.member.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.member.dto.response.TrainingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService implements TrainingUseCase {

    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;
    private final ExercisePort exercisePort;
    private final LoadMemberPort loadMemberPort;


    /**
     * result 는 나중에 업데이트..?
     */
    @Override
    @Transactional
    public TrainingResponse createTraining(TrainingCreate trainingCreate) {
        Member member = loadMemberPort.loadMember(trainingCreate.getEmail());
        Training training = Training.builder()
                .member(member)
                .numOfWork(trainingCreate.getNumOfWork())
                .build();

        Long trainingId = trainingPort.create(training);

        List<Exercise> exercises = exercisePort.getAll();

        //TODO setNum이 exercise의 총 개수보다 커서는 안될듯; setNum 제한조건(constraint) 필요
        for (int i = 0; i < trainingCreate.getNumOfWork(); i++) {
            Exercise exercise = exercises.get(i);//TODO 우선순위 기반으로 바꿀 것
            Workout workout = Workout.builder()
                    .numOfSet(trainingCreate.getNumOfSet())
                    .training(training)
                    .exercise(exercise)
                    .build();
            workoutPort.create(workout);//TODO 리스트로 모았다가 한번에 쿼리로 전환
        }

        return TrainingResponse.builder()
                .trainingId(trainingId)
                .build();
    }


}
