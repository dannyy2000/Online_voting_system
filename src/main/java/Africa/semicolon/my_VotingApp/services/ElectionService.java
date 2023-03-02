package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateCreateRequest;
import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDto;
import Africa.semicolon.my_VotingApp.data.models.Election;

public interface ElectionService {

     ElectionDto createElection(CreateElectionRequest createElectionRequest);


     Election getElectionById(Long electionId);

     Election  updateElection(Long electionId, CreateElectionRequest createElectionRequest, CandidateCreateRequest
                              candidateCreateRequest);

     void deleteElection(Long electionId);


}
