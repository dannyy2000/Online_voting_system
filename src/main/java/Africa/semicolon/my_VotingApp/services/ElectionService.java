package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDto;
import Africa.semicolon.my_VotingApp.data.models.Election;

public interface ElectionService {

     ElectionDto createElection(CreateElectionRequest createElectionRequest);

     Election addElection(CreateElectionRequest createElectionRequest);

     Election getElectionById(Long electionId);

     Election  updateElection(Long id, CreateElectionRequest createElectionRequest);

     void deleteElection(Long electionId);


}
