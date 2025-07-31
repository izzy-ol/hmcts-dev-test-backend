package uk.gov.hmcts.reform.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.dev.models.TaskDetails;

public interface TaskRepository extends  JpaRepository<TaskDetails, Integer> {
}
