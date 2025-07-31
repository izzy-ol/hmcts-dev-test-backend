package uk.gov.hmcts.reform.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.dto.CreateTaskDTO;
import uk.gov.hmcts.reform.dev.dto.GetTaskDTO;
import uk.gov.hmcts.reform.dev.models.TaskDetails;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public GetTaskDTO saveTask(CreateTaskDTO dto) {
        TaskDetails task = new TaskDetails();
        task.setCaseNumber(dto.getCaseNumber());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDateTime(dto.getDueDateTime());

        TaskDetails saved = taskRepository.save(task);
        return toDto(saved);
    }

    public List<GetTaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<GetTaskDTO> getTaskById(Integer id) {
        return taskRepository.findById(id).map(this::toDto);
    }

    public Optional<GetTaskDTO> updateTask(Integer id, String newStatus) {
        Optional<TaskDetails> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        TaskDetails task = optionalTask.get();
        task.setStatus(newStatus);
        return Optional.of(toDto(taskRepository.save(task)));
    }

    public boolean deleteTask(Integer id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }


    private GetTaskDTO toDto(TaskDetails task) {
        GetTaskDTO dto = new GetTaskDTO();
        dto.setId(task.getId());
        dto.setCaseNumber(task.getCaseNumber());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDateTime(task.getDueDateTime());
        return dto;
    }
}
