package example.crudschedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
