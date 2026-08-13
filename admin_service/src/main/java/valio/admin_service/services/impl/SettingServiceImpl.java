package valio.admin_service.services.impl;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import valio.admin_service.entities.Setting;
import valio.admin_service.enums.Currency;
import valio.admin_service.repositories.SettingRepository;
import valio.admin_service.services.SettingService;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
	
	private final SettingRepository settingRepository;
	
	private Setting findSettingsOrThrow(Long id) {
		return settingRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Settings not found"));
	}

	@Override
	public Setting changePropertyName(Long id, String name) {
		Setting setting = findSettingsOrThrow(id);
		setting.setPropertyName(name);
		return settingRepository.save(setting);
	}

	@Override
	public Setting currency(Long id, Currency currency) {
		Setting setting = findSettingsOrThrow(id);
		setting.setCurrency(currency);
		return settingRepository.save(setting);
	}

	@Override
	public Setting timezone(Long id, String tz) {
		Setting setting = findSettingsOrThrow(id);
		setting.setTimezone(tz);
		return settingRepository.save(setting);
	}

	@Override
	public Setting toggleMaintenance(Long id, boolean enabled) {
		Setting setting = findSettingsOrThrow(id);
		setting.setMaintenance(enabled);
		return settingRepository.save(setting);
	}

}
