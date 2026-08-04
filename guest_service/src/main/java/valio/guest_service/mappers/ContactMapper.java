package valio.guest_service.mappers;

import org.mapstruct.Mapper;

import valio.guest_service.dtos.request.ContactRequest;
import valio.guest_service.dtos.response.ContactResponse;
import valio.guest_service.entities.Contact;

@Mapper
public interface ContactMapper {

	Contact toContact(ContactRequest toContactRequest);
	ContactResponse toContactResponse(Contact toContact);
}
