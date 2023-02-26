package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.data.repositories.ElectionRepository;
import Africa.semicolon.my_VotingApp.mapper.ElectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ElectionServiceImpl implements ElectionService{
    private final ElectionRepository electionRepository;

    @Override
    public Election createElection(CreateElectionRequest createElectionRequest) {
        Election election = ElectionMapper.map(createElectionRequest);
        return  electionRepository.save(election);
    }

    @Override
    public Election addElection(CreateElectionRequest createElectionRequest) {
        return null;
    }

    @Override
    public Election getElectionById(Long electionId) {
        return null;
    }

    @Override
    public Election updateElection(Long id, CreateElectionRequest createElectionRequest) {
        return null;
    }

    @Override
    public void deleteElection(Long electionId) {

    }
}
