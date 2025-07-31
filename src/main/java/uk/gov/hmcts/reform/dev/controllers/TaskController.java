package uk.gov.hmcts.reform.dev.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import uk.gov.hmcts.reform.dev.dto.CreateTaskDTO;
import uk.gov.hmcts.reform.dev.dto.GetTaskDTO;
import uk.gov.hmcts.reform.dev.models.TaskDetails;
import uk.gov.hmcts.reform.dev.service.TaskService;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    // Hard-coded mapping to example task
    @GetMapping(value = "/example-task", produces = "application/json")
    public ResponseEntity<TaskDetails> getExampleTask() {
        return ok(new TaskDetails(1, "ABC12345", "Case Title",
                                  "Case Description", "Case Status", LocalDateTime.now()
        ));
    }

    // Create a task
    @PostMapping
    public ResponseEntity<GetTaskDTO> saveTask(@RequestBody CreateTaskDTO dto) {
        try {
            GetTaskDTO createdTask = taskService.saveTask(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<GetTaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<GetTaskDTO> getTaskById(@PathVariable Integer id) {
        Optional<GetTaskDTO> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update task
    @PatchMapping("/{id}/status")
    public ResponseEntity<GetTaskDTO> updateTask(@PathVariable Integer id, @RequestBody String status) {
        Optional<GetTaskDTO> updated = taskService.updateTask(id, status.replace("\"", ""));
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
