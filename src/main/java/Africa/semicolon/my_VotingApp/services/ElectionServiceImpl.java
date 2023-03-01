package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateCreateRequest;
import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.data.repositories.ElectionRepository;
import Africa.semicolon.my_VotingApp.exception.ElectionServiceException;
import Africa.semicolon.my_VotingApp.mapper.ElectionMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionServiceImpl implements ElectionService{
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public ElectionDto createElection(CreateElectionRequest createElectionRequest) {
        Election election = ElectionMapper.map(createElectionRequest);
        Set<Candidate> candidates = new HashSet<>();
        for (CandidateCreateRequest candidateCreateRequest : createElectionRequest.getCandidates()) {
            Candidate candidate = new Candidate();
            candidate.setName(candidateCreateRequest.getName());
            candidate.setParty(candidateCreateRequest.getParty());
//            candidate.setElection(election);
            candidates.add(candidate);
//            candidateRepository.save(candidate);

            election.setCandidates(candidates);
            election = electionRepository.save(election);

        }

        ElectionDto electionDto = getElectionResponse(election);
        return electionDto;


    }

    private ElectionDto getElectionResponse(Election election) {
        ElectionDto electionDto = new ElectionDto();
        electionDto.setId(election.getId());
        electionDto.setName(election.getName());
        electionDto.setStartDate(election.getStartDate());
        electionDto.setEndDate(election.getEndDate());
        return electionDto;
    }

    @Override
    public Election addElection(CreateElectionRequest createElectionRequest) {
        return null;
    }

    @Override
    public Election getElectionById(Long electionId) {
        return electionRepository.findById(electionId).orElseThrow(()->
            new ElectionServiceException(String.format("Election with id %d,does not exists",electionId)));
    }

    @Override
    public Election updateElection(Long id, CreateElectionRequest createElectionRequest) {
        return null;
    }

    @Override
    public void deleteElection(Long electionId) {

    }
}
