package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.VoteRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoteResponseDto;
import Africa.semicolon.my_VotingApp.services.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/votes")
public class VoteController {

    private final VoteService voteService;

    public ResponseEntity<?> castVote(@RequestBody VoteRequestDto voteRequestDto){
        VoteResponseDto voteResponseDto = voteService.castVote(voteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(voteResponseDto);
    }


}
