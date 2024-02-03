
package uhang.uhang.login.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uhang.uhang.login.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByMemberEmail(String memberEmail);
    public boolean existsByMemberEmail(String email);

    public Member findByMemberId(int memberId);

}

