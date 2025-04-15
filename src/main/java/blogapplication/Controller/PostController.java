package blogapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blogapplication.Payloads.PostDto;
import blogapplication.Service.PostService;

@RestController
@RequestMapping("home/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("post/{userId}/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable int userId,@PathVariable int categoryId)
	{
		PostDto postDto=this.postService.createPost(postdto, userId, categoryId);
		
		return new ResponseEntity<>(postDto,HttpStatus.CREATED);
	}
	
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,int postId)
	{
		return ResponseEntity.ok(this.postService.updatePost(postdto, postId));
	}
	
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(int postId)
	{
		return ResponseEntity.ok(this.postService.getSinglePost(postId));
	}
	
	@GetMapping("post")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		List<PostDto> postDto=this.postService.getAllPost();
		
		return new ResponseEntity<>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable int postId)
	{
		this.postService.deletePost(postId);
		return ResponseEntity.ok("Post Delete Successfully.");
	}
	
	@GetMapping("post/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId)
	{
		List<PostDto> postdto=this.postService.getPostByUser(userId);
		return new ResponseEntity<>(postdto,HttpStatus.OK);
	}
	
	@GetMapping("post/{categotyId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId)
	{
		List<PostDto> postdto=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(postdto,HttpStatus.OK);
	}
	
} 
