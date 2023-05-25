package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.api.application.ports.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.in.TrainingUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.domains.training.Training;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import Fit4You.Fit4YouBackend.api.dto.request.RecommendCreate;
import Fit4You.Fit4YouBackend.api.dto.request.TrainingCreate;
import Fit4You.Fit4YouBackend.api.dto.response.ExercisesResponse;
import Fit4You.Fit4YouBackend.api.dto.response.TrainingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService implements ExerciseUseCase {

    private final ExercisePort exercisePort;
    private final WorkoutPort workoutPort;

    @Override
    public ExercisesResponse getAll() {
        return ExercisesResponse.builder()
                .exercises(exercisePort.getAll())
                .build();
    }

    @Override
    public Resource getVideo(Long workoutId) {

        String videoName = workoutPort.getOne(workoutId).getExercise().getVideoLink();

        // 다운로드할 파일의 경로를 가져옵니다.
        String filePath = "src/main/resources/videos/{}.mp4".replace("{}",videoName);

        // 파일을 Resource로 로드합니다.
        return new FileSystemResource(filePath);
    }


}