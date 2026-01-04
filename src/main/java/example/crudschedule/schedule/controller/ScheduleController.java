package example.crudschedule.schedule.controller;

import example.crudschedule.schedule.dto.ScheduleCreateRequest;
import example.crudschedule.schedule.dto.ScheduleResponse;
import example.crudschedule.schedule.dto.ScheduleUpdateRequest;
import example.crudschedule.schedule.repository.ScheduleRepository;
import example.crudschedule.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor // 꼭 필요한 의존성만 생성자로 주입하게 해 주는 어노테이션
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> create(@RequestBody ScheduleCreateRequest request) {

        ScheduleResponse scheduleResponse = scheduleService.create(request.getTitle(), request.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {

        ScheduleResponse scheduleResponse = scheduleService.getSchedule(id);

        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {

        List<ScheduleResponse> scheduleResponses = scheduleService.getAllSchedules();

        return new ResponseEntity<>(scheduleResponses, HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleUpdateRequest request) {

        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(id, request.getTitle(), request.getContent());

        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
    }
}
