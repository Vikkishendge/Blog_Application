package blogapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogapplication.Model.Category;
import blogapplication.Model.Posts;
import blogapplication.Model.User;
@Repository
public interface PostRepo extends JpaRepository<Posts, Integer>{

	public List<Posts> findByUser(User user);
	
	public List<Posts> findByCategory(Category category);
	
}
