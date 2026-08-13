package valio.admin_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import valio.admin_service.enums.Currency;

@Entity
@Data
public class Setting {
	
	@Id
	private Long id;

	@Column(nullable = false)
	private String propertyName = "Valio";

	@Column(nullable = false, length = 3)
	@Enumerated(EnumType.STRING)
	private Currency currency = Currency.USD;

	@Column(nullable = false)
	private String timezone = "UTC";

	@Column(nullable = false)
	private Boolean maintenance = false;
}
