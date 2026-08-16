package valio.booking_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import valio.booking_service.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID>{

}
