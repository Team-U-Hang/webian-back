
package uhang.uhang.login.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uhang.uhang.commentlike.dto.ClikeDto;
import uhang.uhang.login.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
     Member findByMemberEmail(String memberEmail);
     boolean existsByMemberEmail(String email);

     Member findByMemberId(Long memberId);
}

