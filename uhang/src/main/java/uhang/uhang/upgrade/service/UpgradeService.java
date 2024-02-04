package uhang.uhang.upgrade.service;


import com.sun.jdi.PrimitiveValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uhang.uhang.login.domain.Member;
import uhang.uhang.login.domain.repository.MemberRepository;
import uhang.uhang.upgrade.domain.Upgrade;
import uhang.uhang.upgrade.domain.repository.UpgradeRepository;
import uhang.uhang.upgrade.dto.UpgradeDto;

@Service
public class UpgradeService {

    private final UpgradeRepository upgradeRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public UpgradeService(UpgradeRepository upgradeRepository, MemberRepository memberRepository) {
        this.upgradeRepository = upgradeRepository;
        this.memberRepository = memberRepository;
    }
    public Upgrade saveUpgrade(Upgrade upgrade) {
        return upgradeRepository.save(upgrade);
    }

    public Upgrade saveUpgrade(UpgradeDto upgradeDto) {
        Upgrade upgrade = upgradeDto.toEntity();
        // Member 엔티티를 생성하여 설정
        upgrade.setMember(Member.builder().memberId(upgradeDto.getMemberId()).build());
        return saveUpgrade(upgrade);
    }
}
