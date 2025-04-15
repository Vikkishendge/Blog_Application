package blogapplication.ServiceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogapplication.Exceptions.ResourceNotFoundException;
import blogapplication.Model.Comment;
import blogapplication.Model.Posts;
import blogapplication.Payloads.CommentDto;
import blogapplication.Repository.CommentRepo;
import blogapplication.Repository.PostRepo;
import blogapplication.Service.CommentService;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto addComment(CommentDto commentdto,int postId) {
		
		Posts post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		
		Comment comment = this.modelMapper.map(commentdto, Comment.class);
		
		comment.setPost(post);

	    Comment saveComment=this.commentRepo.save(comment);
	    
        return this.modelMapper.map(saveComment,CommentDto.class);
		
	}

	@Override
	public void deleteComment(int comment_id) {
		
	     Comment comment=this.commentRepo.findById(comment_id).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", comment_id));

	     this.commentRepo.delete(comment);

	}

	

}
