package Africa.semicolon.my_VotingApp.services.Implementation;

import Africa.semicolon.my_VotingApp.cloud.CloudService;
import Africa.semicolon.my_VotingApp.data.dto.request.PostRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CreatePostResponseDto;
import Africa.semicolon.my_VotingApp.data.dto.response.UpdatePostResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.models.Post;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.data.repositories.PostRepository;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import Africa.semicolon.my_VotingApp.exception.ImageUploadException;
import Africa.semicolon.my_VotingApp.services.PostService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final CloudService cloudService;
    private final CandidateRepository candidateRepository;
    private final PostRepository postRepository;
    @Override
    public CreatePostResponseDto createPost(PostRequestDto createPostRequest) {
        log.info("Request to create an post with payload={}", createPostRequest);
        Set<Long> candidateId = createPostRequest.getCandidatesId();
        Set<Candidate> candidateSet = new HashSet<>(candidateRepository.findAllById(candidateId));

        if(candidateId.size() != candidateSet.size())throw new GeneralServiceException("Some candidate id does " +
                "not exists");
        var imageUrl = cloudService.upload(createPostRequest.getCandidateImage());
        if(Objects.isNull(imageUrl))throw new ImageUploadException("Post creation failed");
        Post post = new Post();
        post.setCandidates(candidateSet);
        post.setDescription(createPostRequest.getDescription());
        post.setImageUrl(imageUrl);
       Post savedPost = postRepository.save(post);

        return getPostResponseDto(savedPost);

    }

    @Override
    public Optional<Post> viewPost(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public UpdatePostResponseDto editPost(Long postId, PostRequestDto postRequestDto) {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new GeneralServiceException("Post not found with id " + postId));

            ObjectMapper objectMapper = new ObjectMapper();
            try{
                JsonPatch patch = objectMapper.readValue(postRequestDto.getPatch().getBytes(), JsonPatch.class);
                JsonNode patched = patch.apply(objectMapper.convertValue(post, JsonNode.class));
            Post patchedPost = objectMapper.convertValue(patched, Post.class);

            Set<Long> candidateIds = patchedPost.getCandidates().stream().map(Candidate::getId).collect(Collectors.toSet());
            Set<Candidate> candidates = new HashSet<>(candidateRepository.findAllById(candidateIds));
//            if (candidateIds.size() != candidates.size()) {
//                throw new GeneralServiceException("Some candidate id does not exists");
//            }

            var imageUrl = cloudService.upload(postRequestDto.getCandidateImage());
            if (Objects.isNull(imageUrl)) {
                throw new ImageUploadException("Post creation failed");
            }

            patchedPost.setCandidates(candidates);
            patchedPost.setImageUrl(imageUrl);

            Post updatedPost = postRepository.save(patchedPost);

                return getPostResponse(updatedPost);
        } catch (IOException|JsonPatchException e) {
                throw new RuntimeException(e);
            }

    }

    private CreatePostResponseDto getPostResponseDto(Post savedPost) {
        CreatePostResponseDto postResponseDto = new CreatePostResponseDto();
        postResponseDto.setId(savedPost.getId());
        postResponseDto.setSuccessful(true);
        postResponseDto.setMessage("Post Created Successfully");
        return postResponseDto;

    }

    private UpdatePostResponseDto getPostResponse(Post updatedPost) {
        UpdatePostResponseDto postResponseDto = new UpdatePostResponseDto();
        postResponseDto.setId(updatedPost.getId());
        postResponseDto.setSuccessful(true);
        postResponseDto.setMessage("Post Updated Successfully");
        return postResponseDto;

    }




}
