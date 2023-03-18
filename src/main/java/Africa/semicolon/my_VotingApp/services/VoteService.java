package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.VoteRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoteResponseDto;

public interface VoteService {

    VoteResponseDto castVote(VoteRequestDto voteRequestDto);
}
