package valio.admin_service.services;

import java.util.List;
import java.util.UUID;

import valio.admin_service.entities.TaskAssign;

public interface TaskAssignService{
	TaskAssign assignTaskTo(UUID taskId, UUID houseKeeperId);
	TaskAssign cancelTaskTo(UUID taskId, UUID houseKeeperId);
	List<TaskAssign> getAllTasks();
}
