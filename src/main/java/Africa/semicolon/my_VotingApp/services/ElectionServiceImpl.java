package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateCreateRequest;
import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.data.repositories.ElectionRepository;
import Africa.semicolon.my_VotingApp.exception.CandidateServiceException;
import Africa.semicolon.my_VotingApp.exception.ElectionServiceException;
import Africa.semicolon.my_VotingApp.mapper.ElectionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionServiceImpl implements ElectionService {
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
            candidateRepository.save(candidate);
            candidates.add(candidate);
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
    public Election getElectionById(Long electionId) {
        return electionRepository.findById(electionId).orElseThrow(() ->
                new ElectionServiceException(String.format("Election with id %d,does not exists", electionId)));
    }

    @Override
    public Election updateElection(Long electionId, CreateElectionRequest createElectionRequest,
                                   CandidateCreateRequest candidateCreateRequest) {
        Optional<Election> optionalElection = electionRepository.findById(electionId);
        if (optionalElection.isEmpty()) {
            throw new ElectionServiceException(String.format("Election with id %d,does not exists", electionId));
        }
        Election updatedElection = optionalElection.get();
        updatedElection.setName(createElectionRequest.getName());
        updatedElection.setEndDate(String.valueOf(LocalDate.of(2023, 3, 20)));
        updatedElection.setStartDate(String.valueOf(LocalDate.of(2023, 3, 1)));

        Set<Candidate> candidates = new HashSet<>();
        for (Candidate candidate : updatedElection.getCandidates()) {
            Long candidateId = candidate.getId();
            Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
            if (optionalCandidate.isPresent()) {
                Candidate updatedCandidate = optionalCandidate.get();
                updatedCandidate.setName(candidateCreateRequest.getName());
                updatedCandidate.setParty(candidateCreateRequest.getParty());
                candidateRepository.save(candidate);
                candidates.add(updatedCandidate);
                updatedElection.setCandidates(candidates);
                electionRepository.save(updatedElection);
            }else throw new CandidateServiceException(String.format("Candidate with id %d,does not exists",candidateId));
        }
        return updatedElection;
    }

    @Override
    public void deleteElection(Long electionId) {

    }
}


