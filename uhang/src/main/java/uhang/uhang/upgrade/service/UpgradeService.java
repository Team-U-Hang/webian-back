package uhang.uhang.upgrade.service;


import com.sun.jdi.PrimitiveValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.upgrade.domain.Upgrade;
import uhang.uhang.upgrade.domain.repository.UpgradeRepository;

@Service
public class UpgradeService {


    private final UpgradeRepository upgradeRepository;

    @Autowired
    public UpgradeService(UpgradeRepository upgradeRepository){
        this.upgradeRepository =upgradeRepository;
    }

    public Upgrade saveUpgrade(Upgrade upgrade){
        return upgradeRepository.save(upgrade);
    }


}
