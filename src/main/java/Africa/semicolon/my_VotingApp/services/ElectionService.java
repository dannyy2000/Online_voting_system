package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.request.ElectionRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDtoResponse;
import Africa.semicolon.my_VotingApp.data.models.Election;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

public interface ElectionService {

     ElectionDtoResponse createElection(ElectionRequestDto createElectionRequest);


     ElectionDtoResponse getElectionById(Long electionId);


     ElectionDtoResponse updateElection(Long electionId, JsonPatch updatePayload);

     Page<Election>getAllElection(int pageNumber);

     void deleteElection(Long electionId);


}
