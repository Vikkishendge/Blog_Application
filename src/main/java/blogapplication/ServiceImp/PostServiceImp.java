package blogapplication.ServiceImp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogapplication.Exceptions.ResourceNotFoundException;
import blogapplication.Model.Category;
import blogapplication.Model.Posts;
import blogapplication.Model.User;
import blogapplication.Payloads.PostDto;
import blogapplication.Repository.CategoryRepo;
import blogapplication.Repository.PostRepo;
import blogapplication.Repository.UserRepo;
import blogapplication.Service.PostService;

@Service
public class PostServiceImp implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postdto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));

		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
		
		Posts post=this.modelMapper.map(postdto,Posts.class);
		
		post.setUpload_date(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Posts savePost=this.postRepo.save(post);
		
		PostDto postDto=this.modelMapper.map(savePost,PostDto.class);
		return postDto;
	}

	@Override
	public PostDto updatePost(PostDto postdto,int postId) {
		
		Posts post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));

		post.setPostTitle(postdto.getPostTitle());
		post.setContent(postdto.getContent());
		post.setImage(postdto.getImage());
		post.setUpload_date(new Date());
		
		Posts updatedPost=this.postRepo.save(post);
		
		PostDto postDto=this.modelMapper.map(updatedPost, PostDto.class);
		
		return postDto;
		
	}

	@Override
	public PostDto getSinglePost(int postId) {
		
		Posts post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
	
		PostDto postDto=this.modelMapper.map(post,PostDto.class);
		
		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {
	
		List<Posts> post = this.postRepo.findAll();
		
		List<PostDto> postDto=post.stream().map(posts-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public void deletePost(int id) {
	
		Posts post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		List<Posts> post=this.postRepo.findByUser(user);
		
		List<PostDto> postDto = post.stream().map(posts-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
        		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));

		List<Posts> post= this.postRepo.findByCategory(category);
		
		return post.stream().map(posts-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
	}


}
