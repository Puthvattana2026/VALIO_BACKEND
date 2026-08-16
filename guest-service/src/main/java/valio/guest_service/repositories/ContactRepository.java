package valio.guest_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import valio.guest_service.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID>{
	
}
