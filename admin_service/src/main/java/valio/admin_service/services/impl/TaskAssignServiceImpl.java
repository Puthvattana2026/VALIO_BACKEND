package valio.admin_service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valio.admin_service.dtos.response.UsersResponseDTO;
import valio.admin_service.entities.TaskAssign;
import valio.admin_service.repositories.TaskAssignRepository;
import valio.admin_service.services.GetUserByRoleFeignClient;
import valio.admin_service.services.TaskAssignService;

@Service
@RequiredArgsConstructor
public class TaskAssignServiceImpl implements TaskAssignService{
	
	private final GetUserByRoleFeignClient getUserByRoleFeignClient;
	private final TaskAssignRepository assignRepository;
	private final String HOUSE_KEEPER = "HOUSEKEEPING";
	
	/*
	 * |======================
	 * | Admin To HouseKeeper
	 * |======================
	 */

	@Override
	public TaskAssign assignTaskTo(UUID taskId, UUID houseKeeperId) {
	    if (houseKeeperId == null) {
	        throw new IllegalArgumentException("houseKeeperId must not be null.");
	    }
	    TaskAssign task = assignRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found."));
	    List<UsersResponseDTO> users = getUserByRoleFeignClient.getAllUsersByRole(HOUSE_KEEPER);
	    boolean isValidHouseKeeper = users.stream().anyMatch(u -> u.id().equals(houseKeeperId));
	    if (!isValidHouseKeeper) {
	        throw new IllegalArgumentException("Only HOUSEKEEPING role can be assigned.");
	    }
	    task.setAssignById(houseKeeperId);
	    return assignRepository.save(task);
	}

	@Override
	public TaskAssign cancelTaskTo(UUID taskId, UUID houseKeeperId) {
	    TaskAssign existing = assignRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found."));
	    List<UsersResponseDTO> users = getUserByRoleFeignClient.getAllUsersByRole(HOUSE_KEEPER);
	    boolean isValidHouseKeeper = users.stream().anyMatch(u -> u.id().equals(houseKeeperId));
	    if (!isValidHouseKeeper) {
	        throw new IllegalArgumentException("HOUSEKEEPER NOT FOUND");
	    }
	    existing.setIsDisable(true);
	    return assignRepository.save(existing);
	}

	@Override
	public List<TaskAssign> getAllTasks() {
		return assignRepository.findAll();
	}
	
	/*
	 * |======================
	 * | From HouseKeeper 
	 * |======================
	 */
	
	// read status, priority

}
