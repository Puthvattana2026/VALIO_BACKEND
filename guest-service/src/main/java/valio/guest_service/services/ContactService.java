package valio.guest_service.services;

import valio.guest_service.entities.Contact;

public interface ContactService {
	Contact sendByContact(Contact request);
}
