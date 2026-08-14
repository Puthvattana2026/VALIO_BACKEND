package valio.admin_service.dtos.request;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;
import valio.admin_service.dtos.response.FileMetadataResponse;
import valio.admin_service.enums.RoomStatus;
import valio.admin_service.enums.RoomType;

@Data
public class RoomRequest {
	private int roomNumber;
    private BigDecimal price;
    private FileMetadataResponse image;
    private RoomStatus roomStatus;
    private RoomType roomType;
    private Boolean visibility;
}
