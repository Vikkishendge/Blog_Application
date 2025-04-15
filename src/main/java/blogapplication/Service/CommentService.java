package blogapplication.Service;

import blogapplication.Payloads.CommentDto;

public interface CommentService {

    public CommentDto addComment(CommentDto comment,int postId);
    
    public void deleteComment(int comment_id);
}
