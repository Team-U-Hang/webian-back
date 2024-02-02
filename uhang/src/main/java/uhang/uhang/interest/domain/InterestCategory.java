package uhang.uhang.interest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uhang.uhang.login.domain.Member;

@Entity
@Table(name = "interestcategory")
@Getter
@Setter
public class InterestCategory {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


 */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "interest_category_id")
    private Long interestCategoryId;

    @ManyToOne
    @JoinColumn(name = "member_id")//, insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")//, insertable = false, updatable = false)
    private Category category;


    public void setMember(Member member) {
        this.member = member;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}