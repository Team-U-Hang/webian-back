package uhang.uhang.interest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uhang.uhang.interest.domain.Category;
import uhang.uhang.interest.domain.InterestCategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}