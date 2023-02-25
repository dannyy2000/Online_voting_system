package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRegisterRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterRegisterResponse;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

public interface VotersService {

  VoterRegisterResponse register(VoterRegisterRequest voterRegisterRequest);

  Voter getVoterById(Long votersId);

  Voter updateVoter(Long votersId, JsonPatch uploadPayLoad);

  Page<Voter> getAllVoters(int pageNumber);

  void deleteVoter(Long VotersId);
}
