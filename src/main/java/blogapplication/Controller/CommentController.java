package blogapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogapplication.Payloads.CommentDto;
import blogapplication.Payloads.Responseapi;
import blogapplication.Service.CommentService;

@RestController
@RequestMapping("home/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	  @PostMapping("comment/{postId}")
	    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentdto,@PathVariable int postId)
	    {
	        System.out.println("content"+commentdto.getContent());
	        
	        CommentDto comment = this.commentService.addComment(commentdto,postId);

	        return new ResponseEntity<>(comment,HttpStatus.CREATED);
	    }

	    @DeleteMapping("comment/{commentId}")
	    public Responseapi deleteComment(@PathVariable int commentId)
	    {
	        this.commentService.deleteComment(commentId);
	        
	        Responseapi ar = new Responseapi("comments delete successfully",true);
			return ar;
	    }

}
