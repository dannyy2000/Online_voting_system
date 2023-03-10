package Africa.semicolon.my_VotingApp.services.Implementation;
import Africa.semicolon.my_VotingApp.data.dto.request.ElectionRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDtoResponse;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.data.repositories.ElectionRepository;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import Africa.semicolon.my_VotingApp.mapper.ElectionMapper;
import Africa.semicolon.my_VotingApp.services.ElectionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    private final static int NO_OF_ITEMS_PER_PAGE = 3;

    @Override
    public ElectionDtoResponse createElection(ElectionRequestDto createElectionRequest) {
        log.info("Request to create an election with payload={}", createElectionRequest);
        Election election = ElectionMapper.map(createElectionRequest);
        Set<Long> candidateId = createElectionRequest.getCandidatesId();
        Set<Candidate>candidateSet = new HashSet<>(candidateRepository.findAllById(candidateId));

        if(candidateSet.size() != candidateId.size()) throw new GeneralServiceException("Some Candidate id " +
                "do not exists");

        election.setCandidates(candidateSet);
        electionRepository.save(election);
        ElectionDtoResponse electionDtoResponse = getElectionResponse(election);
        return electionDtoResponse;

    }

    private ElectionDtoResponse getElectionResponse(Election election) {
        ElectionDtoResponse electionDto = new ElectionDtoResponse();
        electionDto.setId(election.getId());
        electionDto.setName(election.getName());
        electionDto.setStartDate(election.getStartDate());
        electionDto.setEndDate(election.getEndDate());
        electionDto.setCandidates(election.getCandidates());
        return electionDto;
    }


    @Override
    public ElectionDtoResponse getElectionById(Long electionId) {
        return electionRepository.findById(electionId)
                .map(this::toElectionResponseDto)
                .orElseThrow(()-> new GeneralServiceException(String.format("Election id %d not found",electionId)));
    }

    @Override
    public ElectionDtoResponse updateElection(Long electionId, JsonPatch updatePayload) {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Election> election = electionRepository.findById(electionId);
        if(election.isEmpty())throw new GeneralServiceException("Election id not found");
        Election electionFound = election.get();

        JsonNode node = objectMapper.convertValue(electionFound, JsonNode.class);

        try {
            JsonNode updateNode = updatePayload.apply(node);
            Election savedElection = objectMapper.convertValue(updateNode, Election.class);
            electionRepository.save(savedElection);
            return getElectionResponse(savedElection);
        } catch (JsonPatchException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public Page<Election> getAllElection(int pageNumber) {
        if(pageNumber < 1){
            pageNumber = 0;
        }
        else{
            pageNumber = pageNumber - 1;
        }
        Pageable pageable = PageRequest.of(pageNumber,NO_OF_ITEMS_PER_PAGE);
        return electionRepository.findAll(pageable);
    }

    private ElectionDtoResponse toElectionResponseDto(Election election) {
        return new ElectionDtoResponse(
                election.getId(),
                election.getName(),
                election.getStartDate(),
                election.getEndDate(),
                election.getCandidates()
        );
    }


    @Override
    public void deleteElection(Long electionId) {
          electionRepository.deleteById(electionId);
    }
}


