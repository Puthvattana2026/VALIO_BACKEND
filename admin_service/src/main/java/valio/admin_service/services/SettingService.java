package valio.admin_service.services;

import valio.admin_service.entities.Setting;
import valio.admin_service.enums.Currency;

public interface SettingService {
	Setting changePropertyName(Long id, String name);
	Setting currency(Long id, Currency currency);
	Setting timezone(Long id, String tz);
	Setting toggleMaintenance(Long id, boolean enabled);
}
