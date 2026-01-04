package example.crudschedule.schedule.service;

import example.crudschedule.schedule.dto.ScheduleResponse;
import example.crudschedule.schedule.entity.Schedule;
import example.crudschedule.schedule.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Transactional
    public ScheduleResponse create(String title, String content) {

        Schedule schedule = new Schedule(title, content);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getCreatedAt()
        );
    }

    // 일정 단건 조회
    public ScheduleResponse getSchedule(Long id) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Schedule not found")
        );

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    public List<ScheduleResponse> getAllSchedules() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponse::toDto)
                .toList();
    }


    // 일정 수정
    public ScheduleResponse updateSchedule(Long id, String title, String content) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid schedule id");
        }

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Schedule not found")
        );

        schedule.update(title, content);

        // Schedule savedSchedule = scheduleRepository.save(schedule);
        /*
            @Transactional이 붙어있는 메서드가 실행되면 영속성 컨텍스트 라는 관리 공간이 생긴다
            DB에서 엔티티를 조회하면 그 엔티티를 JPA가 관리(추적)하기 시작함
            즉, schedule 객체를 JPA가 계속 관찰하고 있는 상태
            JPA가 트랜잭션이 끝날 때(커밋할 때) 처음 조회했을 때 값과 지금 값을 비교해서 바뀌었으면,
            UPDATE SQL을 자동으로 날려준기 때문에 save가 없어도 됨.
         */

        return ScheduleResponse.toDto(schedule);
    }
}
