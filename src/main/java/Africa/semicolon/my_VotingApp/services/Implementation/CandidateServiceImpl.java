package Africa.semicolon.my_VotingApp.services.Implementation;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CandidateResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
import Africa.semicolon.my_VotingApp.services.CandidateService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private static final int NUMBER_OF_ITEMS_PER_PAGE = 2;

    @Override
    public CandidateResponseDto createCandidate(CandidateRequestDto candidateRequestDto) {
        log.info("Request to create a candidate with payload={}", candidateRequestDto);
        Candidate candidate = new Candidate();
        candidate.setName(candidateRequestDto.getName());
        candidate.setParty(candidateRequestDto.getParty());
        candidateRepository.save(candidate);
       CandidateResponseDto candidateResponse = getCandidateResponse(candidate);
        return candidateResponse;
    }

    private CandidateResponseDto getCandidateResponse(Candidate candidate) {
        CandidateResponseDto candidateResponseDto = new CandidateResponseDto();
        candidateResponseDto.setId(candidate.getId());
        candidateResponseDto.setName(candidate.getName());
        candidateResponseDto.setParty(candidate.getParty());
        candidateResponseDto.setMessage("Candidate Created");
        return candidateResponseDto;
    }


    @Override
    public CandidateResponseDto getCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId)
                .map(this::toCandidateResponse)
                .orElseThrow(()-> new GeneralServiceException(String.format("Candidate with id %d does " +
                        "not exists",candidateId)));
    }

   private CandidateResponseDto toCandidateResponse(Candidate candidate) {
       return new CandidateResponseDto(
               candidate.getId(),
               candidate.getName(),
               candidate.getParty(),
               ""
       );
   }


    @Override
    public Page<Candidate> getAllCandidates(int pageNumber) {
           if(pageNumber < 1){
               pageNumber = 0;
           }
           else {
               pageNumber = pageNumber -1;
           }

        Pageable pageable = PageRequest.of(pageNumber,NUMBER_OF_ITEMS_PER_PAGE);
           return candidateRepository.findAll(pageable);
    }

    @Override
    public CandidateResponseDto updateCandidate(Long candidateId, CandidateRequestDto candidateRequestDto) {
        log.info("Request to update a candidate with the candidate id = {} with payload = {}",candidateId
                ,candidateRequestDto);
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if(candidate.isEmpty())throw new GeneralServiceException("Candidate not found");

        Candidate candidates = candidate.get();
        candidates.setName(candidateRequestDto.getName());
        candidates.setParty(candidateRequestDto.getParty());
        candidateRepository.save(candidates);

        CandidateResponseDto candidateResponseDto = new CandidateResponseDto();
        candidateResponseDto.setId(candidates.getId());
        candidateResponseDto.setName(candidates.getName());
        candidateResponseDto.setParty(candidates.getParty());
        candidateResponseDto.setMessage("Candidates updated");
        return candidateResponseDto;



    }

//    @Override
//    public Candidate updateCandidate(Long candidateId, JsonPatch uploadPayload) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        CandidateResponseDto candidate = getCandidateById(candidateId);
//        JsonNode node = objectMapper.convertValue(candidate, JsonNode.class);
//
//        try {
//            JsonNode updatedNode = uploadPayload.apply(node);
//            Candidate updatedCandidate = objectMapper.convertValue(updatedNode, Candidate.class);
//            candidateRepository.save(updatedCandidate);
//            return updatedCandidate;
//
//        } catch (JsonPatchException e) {
//            throw new RuntimeException(e);
//        }
//    }

        @Override
    public void deleteCandidate(Long candidateId) {
        candidateRepository.deleteById(candidateId);
    }
}
