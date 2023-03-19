package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.CountVotesRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.request.VoteRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CountVotesResponseDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoteResponseDto;
import Africa.semicolon.my_VotingApp.services.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/votes")
public class VoteController {

    private final VoteService voteService;


   @PostMapping("cast")
    public ResponseEntity<?> castVote(@RequestBody VoteRequestDto voteRequestDto){
        VoteResponseDto voteResponseDto = voteService.castVote(voteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(voteResponseDto);
    }

    @GetMapping("count")
    public ResponseEntity<?> countVotes(@RequestBody CountVotesRequestDto countVotesRequestDto){
        CountVotesResponseDto countVotesResponseDto = voteService.countVotesByPostId(countVotesRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(countVotesResponseDto);
    }


}
