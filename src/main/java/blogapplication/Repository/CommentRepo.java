package blogapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogapplication.Model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer>{


}
