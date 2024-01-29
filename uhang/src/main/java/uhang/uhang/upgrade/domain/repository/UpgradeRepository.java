package uhang.uhang.upgrade.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uhang.uhang.upgrade.domain.Upgrade;

import java.util.List;
public interface UpgradeRepository extends CrudRepository<Upgrade, Integer> {  // <관리대상 entity, 대표값 타입>

}