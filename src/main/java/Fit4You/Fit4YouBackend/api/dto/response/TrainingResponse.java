package Fit4You.Fit4YouBackend.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrainingResponse {

    @Schema(description = "수행할 workout의 ID들")
    private List<Long> workoutIds;

    @Builder
    public TrainingResponse(List<Long> workoutIds) {
        this.workoutIds = workoutIds;
    }
}
