package valio.booking_service.services;

import valio.booking_service.entities.Booking;

public interface BookingService {
	Booking createBooking(Booking request);
}
