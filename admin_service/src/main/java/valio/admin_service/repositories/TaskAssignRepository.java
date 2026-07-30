package valio.admin_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import valio.admin_service.entities.TaskAssign;


public interface TaskAssignRepository extends JpaRepository<TaskAssign, UUID>{
	
}
