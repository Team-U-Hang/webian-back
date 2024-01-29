package uhang.uhang.upgrade.domain.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import uhang.uhang.upgrade.domain.Upgrade;
import uhang.uhang.upgrade.domain.repository.UpgradeRepository;

import java.net.URI;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UpgradeRepositoryTest {


    @Autowired UpgradeRepository upgradeRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testUpgrade() throws Exception{
        //given
        Upgrade upgrade = new Upgrade();
        upgrade.setGId(1);
        upgrade.setGName("ddd");
        upgrade.setGIntro("OpenDDS");
        upgrade.setGImageUrl(URI.create("OdpenDDS"));
        //when
    //    int savedGId = UpgradeRepository.saveGId(upgrade);
        int savedGId = upgradeRepository.saveGId(upgrade);



        Upgrade findGroup = upgradeRepository.find(savedGId);
        Assertions.assertThat(findGroup.getGId()).isEqualTo(upgrade.getGId());
        Assertions.assertThat(findGroup.getGName()).isEqualTo(upgrade.getGName());

        Assertions.assertThat(findGroup.getGIntro()).isEqualTo(upgrade.getGIntro());


        Assertions.assertThat(findGroup).isEqualTo(upgrade); //JPA 엔티티 동일성 보장
    }


        //when

        //then

}