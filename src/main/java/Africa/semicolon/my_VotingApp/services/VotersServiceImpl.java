package Africa.semicolon.my_VotingApp.services;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRegisterRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterRegisterResponse;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import Africa.semicolon.my_VotingApp.data.repositories.VoteRepository;
import Africa.semicolon.my_VotingApp.data.repositories.VotersRepository;
import Africa.semicolon.my_VotingApp.exception.VotersServiceException;
import Africa.semicolon.my_VotingApp.mapper.VotersMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
@AllArgsConstructor
@Slf4j
public class VotersServiceImpl implements VotersService{

    private final VotersRepository votersRepository;

    private static final int NUMBER_OF_ITEMS_PER_PAGE = 5;

    @Override
    public VoterRegisterResponse register(VoterRegisterRequest voterRegisterRequest) {
        Voter voter = VotersMapper.map(voterRegisterRequest);
        voter.setNIN(UUID.randomUUID().toString());
        voter.setVIN(UUID.randomUUID().toString());
        voter.setCreatedAt(LocalDateTime.now().toString());

        Voter savedVoter =  votersRepository.save(voter);
        VoterRegisterResponse voterRegisterResponse = getVotersResponse(savedVoter);
        return voterRegisterResponse;

    }

    private VoterRegisterResponse getVotersResponse(Voter savedVoter) {
        VoterRegisterResponse voterRegisterResponse = new VoterRegisterResponse();
        voterRegisterResponse.setId(savedVoter.getId());
        voterRegisterResponse.setCode(HttpStatus.CREATED.value());
        voterRegisterResponse.setSuccess(true);
        voterRegisterResponse.setMessage("Voters registration successful");
        return voterRegisterResponse;
    }

    @Override
    public Voter getVoterById(Long votersId) {
        return votersRepository.findById(votersId).orElseThrow(()->
                new VotersServiceException("Voter does not exist"));
    }

    @Override
    public Voter updateVoter(Long votersId, JsonPatch uploadPayLoad) {
        ObjectMapper objectMapper = new ObjectMapper();
        Voter voter = getVoterById(votersId);

        JsonNode node = objectMapper.convertValue(voter, JsonNode.class);

        try{
            JsonNode updatedNode = uploadPayLoad.apply(node);
            var updatedVoter = objectMapper.convertValue(updatedNode, Voter.class);
             updatedVoter = votersRepository.save(updatedVoter);
             return updatedVoter;
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Page<Voter> getAllVoters(int pageNumber) {
        if(pageNumber < 1){
            pageNumber = 0;
        }
        else{
           pageNumber = pageNumber - 1;
        }
        Pageable pageable = PageRequest.of(pageNumber,NUMBER_OF_ITEMS_PER_PAGE);
        return votersRepository.findAll(pageable);
    }

    @Override
    public void deleteVoter(Long votersId) {
         votersRepository.deleteById(votersId);
    }


}
