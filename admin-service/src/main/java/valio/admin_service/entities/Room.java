package valio.admin_service.entities;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import valio.admin_service.enums.RoomStatus;
import valio.admin_service.enums.RoomType;


@Entity
@Table(name = "rooms")
@SQLRestriction("visible = true")
@Data
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private int roomNumber;
	
	@Enumerated(EnumType.STRING)
	private RoomStatus roomStatus;
	
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	private BigDecimal price;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private FileMetadata image;

	private Boolean visible = true;
}
