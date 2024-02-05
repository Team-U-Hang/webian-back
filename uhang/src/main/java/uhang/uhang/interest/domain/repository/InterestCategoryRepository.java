package uhang.uhang.interest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uhang.uhang.interest.domain.InterestCategory;
import uhang.uhang.login.domain.Member;

import java.util.List;

@Repository
public interface InterestCategoryRepository extends JpaRepository<InterestCategory,Long> {
    @Modifying //insert,update,delete 쿼리에서 벌크 연산시 사용한다
    @Query("delete from InterestCategory mr where mr.member = :member") // 비효율을 막기위해 where절 지정
    void deleteByMember(@Param("member") Member member);

    List<InterestCategory> findByMember_MemberId(Long memberId);

}
