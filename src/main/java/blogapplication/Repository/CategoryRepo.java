package blogapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogapplication.Model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
