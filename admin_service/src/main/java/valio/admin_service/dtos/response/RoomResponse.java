package valio.admin_service.dtos.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;
import valio.admin_service.enums.RoomStatus;
import valio.admin_service.enums.RoomType;

@Data
public class RoomResponse {
	private UUID id;
	private int roomNumber;
    private BigDecimal price;
    private FileMetadataResponse image;
    private RoomStatus roomStatus;
    private RoomType roomType;
    private Boolean visibility;
}
