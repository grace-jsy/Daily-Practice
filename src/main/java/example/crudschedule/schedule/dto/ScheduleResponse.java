package example.crudschedule.schedule.dto;

import example.crudschedule.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {

    // Response Dto는 절대 바뀌면 안 되는 값이기 때문에 final을 쓴다.

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleResponse(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    /*
        엔티티(Schedule)를 API 응답용 DTO(ScheduleResponse)로 변환해 주는 메서드
        API 응답을 안전하게 만들기 위한 핵심 역할

        엔티티를 그대로 노출하지 않기 위해 응답 전용 DTO를 두고 변환 책임을 DTO 내부의 static 메서드로 분리함
     */
    public static ScheduleResponse toDto(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
