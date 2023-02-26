package Africa.semicolon.my_VotingApp.mapper;

import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.models.Election;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ElectionMapper {

    public static Election map(CreateElectionRequest createElectionRequest){
        Election election = new Election();
        election.setName(createElectionRequest.getName());
        election.setStartDate(LocalDateTime.now());
        election.setEndDate(createElectionRequest.getEndDate());
        return election;
    }
}
