package valio.admin_service.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import valio.admin_service.enums.CleanStatus;
import valio.admin_service.enums.Priority;
import valio.admin_service.enums.RoomType;

@Entity
@Table(name = "task_assign")
@Data
public class TaskAssign {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	 
	private String roomId;
	private Integer floor;
	private RoomType roomType;
	private UUID assignById;
	private String houseKeeperName;
	private Priority priority;
	private CleanStatus cleanStatus;
	private String notes;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime create_at;
	
	private Boolean isDisable = false;
}	
