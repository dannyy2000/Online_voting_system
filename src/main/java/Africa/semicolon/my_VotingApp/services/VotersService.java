package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import org.springframework.data.domain.Page;

public interface VotersService {

  VoterResponseDto register(VoterRequestDto voterRegisterRequest);

  VoterResponseDto getVoterById(Long votersId);
//
//  Voter updateVoter(Long votersId, JsonPatch uploadPayLoad);

  VoterResponseDto updateVoter(Long votersId, VoterRequestDto voterRegisterRequest);


  Page<Voter> getAllVoters(int pageNumber);

  void deleteVoter(Long votersId);
}
