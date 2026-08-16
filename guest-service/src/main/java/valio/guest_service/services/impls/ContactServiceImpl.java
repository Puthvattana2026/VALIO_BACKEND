package valio.guest_service.services.impls;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valio.guest_service.entities.Contact;
import valio.guest_service.repositories.ContactRepository;
import valio.guest_service.services.ContactService;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{
	
	private final ContactRepository contactRepository;

	@Override
	public Contact sendByContact(Contact request) {
		
		return null;
	}

}
