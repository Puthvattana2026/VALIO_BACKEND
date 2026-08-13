package valio.admin_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import valio.admin_service.entities.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long>{

}
