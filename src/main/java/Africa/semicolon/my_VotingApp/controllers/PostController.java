package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.PostRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CreatePostResponseDto;
import Africa.semicolon.my_VotingApp.data.dto.response.UpdatePostResponseDto;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import Africa.semicolon.my_VotingApp.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>createPost(@ModelAttribute PostRequestDto createPostRequest){
       try{
           CreatePostResponseDto postResponseDto = postService.createPost(createPostRequest);
           return ResponseEntity.ok(postResponseDto);
       }catch (GeneralServiceException exception){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
       }
    }

    @GetMapping("{postId}")
    public ResponseEntity<?>getPostById(@PathVariable Long postId){
        var post = postService.viewPost(postId);
        return ResponseEntity.status(HttpStatus.FOUND).body(post);
    }

    @PatchMapping(value = "/{postId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editPost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto) {
        try {
            UpdatePostResponseDto postResponseDto = postService.editPost(postId, postRequestDto);
            return ResponseEntity.ok(postResponseDto);
        } catch (GeneralServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
