package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.PostRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CreatePostResponseDto;
import Africa.semicolon.my_VotingApp.data.dto.response.UpdatePostResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Post;

import java.util.Optional;

public interface PostService {

    CreatePostResponseDto createPost(PostRequestDto createPostRequest);

    Optional<Post> viewPost(Long postId);

    UpdatePostResponseDto editPost(Long postId, PostRequestDto postRequestDto);
}
