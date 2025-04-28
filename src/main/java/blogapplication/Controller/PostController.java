package blogapplication.Controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import blogapplication.Payloads.PostDto;
import blogapplication.Payloads.Responseapi;
import blogapplication.Service.CloudinaryService;
import blogapplication.Service.PostService;

@RestController
@RequestMapping("home/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CloudinaryService clService;
	
	@PostMapping("post/{userId}/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestParam("postTitle") String postTitle,
		    @RequestParam("content") String content,
		    @RequestParam("image") MultipartFile imageFile,
		    @PathVariable int userId,
		    @PathVariable int categoryId) throws IOException
	   {
		
		String url = clService.uploadFile(imageFile);
		
		PostDto post=new PostDto();
		post.setPostTitle(postTitle);
		post.setContent(content);
		post.setImage(url);
		PostDto postDto=this.postService.createPost(post, userId, categoryId);
		
		return new ResponseEntity<>(postDto,HttpStatus.CREATED);
	}
	
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable int postId)
	{
		return ResponseEntity.ok(this.postService.updatePost(postdto, postId));
	}
	
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable int postId)
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
	public Responseapi deletePost(@PathVariable int postId)
	{
		this.postService.deletePost(postId);
		Responseapi apr = new Responseapi("this post deleted successfully",true);
		return apr;
	}
	
	@GetMapping("post/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId)
	{
		List<PostDto> postdto=this.postService.getPostByUser(userId);
		return new ResponseEntity<>(postdto,HttpStatus.OK);
	}
	
	@GetMapping("post/category/{categotyId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId)
	{
		List<PostDto> postdto=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(postdto,HttpStatus.OK);
	}
	
} 
