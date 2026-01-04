package example.crudschedule.schedule.service;

import example.crudschedule.schedule.dto.ScheduleResponse;
import example.crudschedule.schedule.entity.Schedule;
import example.crudschedule.schedule.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
