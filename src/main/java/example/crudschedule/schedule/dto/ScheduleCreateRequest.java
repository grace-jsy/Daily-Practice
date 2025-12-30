package example.crudschedule.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {

    // 사용자가 보내는 값이기 때문에 final을 쓰지 않는다.

    private String title;
    private String content;
}
