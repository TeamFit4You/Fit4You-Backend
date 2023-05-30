package Fit4You.Fit4YouBackend.api.training.application.service;

import Fit4You.Fit4YouBackend.api.training.application.port.in.ExerciseUseCase;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.ExercisePort;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.WorkoutPort;
import Fit4You.Fit4YouBackend.api.training.domains.Disease;
import Fit4You.Fit4YouBackend.api.training.domains.Exercise;
import Fit4You.Fit4YouBackend.api.training.dto.request.ExerciseRequest;
import Fit4You.Fit4YouBackend.api.training.dto.response.ExerciseResponse;
import Fit4You.Fit4YouBackend.api.training.dto.response.InfoResponse;
import Fit4You.Fit4YouBackend.config.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService implements ExerciseUseCase {

    private final ExercisePort exercisePort;
    private final WorkoutPort workoutPort;
    private final LoadMemberPort loadMemberPort;
    private final PriorityService priorityService;

    @Value("${video.folder.path}")
    private String folderPath;

    @Override
    @Transactional
    public InfoResponse getInfo(Long exerciseId) {
        Exercise exercise = exercisePort.getOne(exerciseId);
        Disease disease = exercise.getDisease();
        return InfoResponse.builder()
                .name(exercise.getName())
                .detail(exercise.getDetail())
                .part(disease.getRelatedPart())
                .diseaseName(disease.getName())
                .build();

    }


    @Override
    @Transactional
    public List<ExerciseResponse> getAll(ExerciseRequest request) {
        List<Exercise> exercises = exercisePort.getAll();

        //우선순위에 따라 정렬
        priorityService.sortByPriority(loadMemberPort.loadMember(request.getEmail()), exercises);

        List<ExerciseResponse> responseList = new ArrayList<>();
        for (Exercise exercise : exercises) {
            Disease disease = exercise.getDisease();
            if (!request.getPart().equals(Part.전체.label()) && !disease.getRelatedPart().equals(request.getPart())){
                continue;
            }
            ExerciseResponse response = ExerciseResponse.builder()
                    .exerciseId(exercise.getId())
                    .detail(exercise.getDetail())
                    .diseaseName(disease.getName())
                    .part(disease.getRelatedPart())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }


    @Override
    @Transactional
    public Resource getVideoByWorkout(Long workoutId) {

        String videoName = workoutPort.getOne(workoutId).getExercise().getVideoLink();

        // 다운로드할 파일의 경로를 가져옵니다.
        String filePath = folderPath.replace("{}",videoName);

        // 파일을 Resource로 로드합니다.
        return new FileSystemResource(filePath);
    }



    @Override
    @Transactional
    public Resource getVideo(Long exerciseId) {
        String videoName = exercisePort.getOne(exerciseId).getVideoLink();

        // 다운로드할 파일의 경로를 가져옵니다.
        String filePath = folderPath.replace("{}",videoName);

        // 파일을 Resource로 로드합니다.
        return new FileSystemResource(filePath);
    }
}
