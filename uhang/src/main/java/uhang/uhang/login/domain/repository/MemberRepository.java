package uhang.uhang.login.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uhang.uhang.login.domain.Member;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }
    public List<Member> findbyStudNum (String studNum) {
        return em.createQuery(
                "select m from Member m where m.studNum = :studNum", Member.class)
                .setParameter("studNum" , studNum)
                .getResultList();
    }

    public Member findOne(int memberId) {
        return em.find(Member.class,memberId);
    }
}
