//package uhang.uhang.login.domain.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//import uhang.uhang.login.domain.Member;
//
//import java.util.List;
//
//@Repository
//public class MemberRepository{
//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(Member member) {
//        em.persist(member);
//    }
//
//    public Member findOne(Long id) {
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll() {
//        return em.createQuery("select m from members m", Member.class)
//                .setParameter("memberEmail", memb)
//                .getResultList();
//    }
//}