package Fit4You.Fit4YouBackend.api.dto.response;

import Fit4You.Fit4YouBackend.api.domains.Exercise;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExercisesResponse {

    @Schema(description = "전체 운동 컬렉션")
    private List<Exercise> exercises;

    @Builder
    public ExercisesResponse(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
