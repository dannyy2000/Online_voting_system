package Africa.semicolon.my_VotingApp.services.Implementation;

import Africa.semicolon.my_VotingApp.data.dto.request.VoteRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoteResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Post;
import Africa.semicolon.my_VotingApp.data.models.Vote;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import Africa.semicolon.my_VotingApp.data.repositories.PostRepository;
import Africa.semicolon.my_VotingApp.data.repositories.VoteRepository;
import Africa.semicolon.my_VotingApp.data.repositories.VotersRepository;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import Africa.semicolon.my_VotingApp.services.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final PostRepository postRepository;
    private final VotersRepository votersRepository;

    private final VoteRepository voteRepository;
    @Override
    public VoteResponseDto castVote(VoteRequestDto voteRequestDto) {

        Voter voter = votersRepository.findById(voteRequestDto.getPostId()).orElseThrow(()-> new
                GeneralServiceException("Voter not found"));
        Post post = postRepository.findById(voteRequestDto.getPostId()).orElseThrow(()-> new
                GeneralServiceException("Post not found"));

        Vote vote = new Vote();
        vote.setAppUser(voter);
        vote.setPost(post);
        vote.setTime(LocalDateTime.now());

        voteRepository.save(vote);

        return getVoteResponse(vote);

    }

    private VoteResponseDto getVoteResponse(Vote vote) {
        VoteResponseDto voteResponseDto = new VoteResponseDto();
        voteResponseDto.setId(vote.getId());
        voteResponseDto.setVotersId(vote.getAppUser().getId());
        voteResponseDto.setPostId(vote.getPost().getId());
        voteResponseDto.setTime(vote.getTime());
        return voteResponseDto;
    }
}
