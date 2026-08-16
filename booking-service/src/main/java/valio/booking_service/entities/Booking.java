package valio.booking_service.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import valio.booking_service.enums.RoomType;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private UUID guestId;
	private UUID paymentId;
	private String fullname;
	private String phoneNumber;
	private String email;
	private RoomType roomType;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
	private BigDecimal bookingDeposit;
	private LocalDateTime createAt;
	private Boolean isPaid = false;
}
