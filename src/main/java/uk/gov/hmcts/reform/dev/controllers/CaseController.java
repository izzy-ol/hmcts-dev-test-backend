package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.models.TaskDetails;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CaseController {

    @GetMapping(value = "/get-example-case", produces = "application/json")
    public ResponseEntity<TaskDetails> getTaskDetails() {
        return ok(new TaskDetails(1, "ABC12345", "Case Title",
                                  "Case Description", "Case Status", LocalDateTime.now()
        ));
    }
}
