package blogapplication.Service;

import java.util.List;

import blogapplication.Payloads.PostDto;

public interface PostService {

	public PostDto createPost(PostDto post,int userId,int categoryId);
	
	public PostDto updatePost(PostDto postdto,int postId);
	
	public PostDto getSinglePost(int postId);
	
	public List<PostDto> getAllPost();
	
	public void deletePost(int id);
	
	public List<PostDto> getPostByUser(int userId);
	
	public List<PostDto> getPostByCategory(int categoryId);
	
}
