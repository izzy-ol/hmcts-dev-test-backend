package uk.gov.hmcts.reform.dev.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetTaskDTO {
    private int id;
    private String caseNumber;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDateTime;
}
