package uk.gov.hmcts.reform.dev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateTaskDTO {
    private int id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
}
