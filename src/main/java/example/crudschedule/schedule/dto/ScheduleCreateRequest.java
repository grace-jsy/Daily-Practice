package example.crudschedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {

    // 사용자가 보내는 값이기 때문에 final을 쓰지 않는다.

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
