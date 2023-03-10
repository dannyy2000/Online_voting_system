package Africa.semicolon.my_VotingApp.services.Implementation;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Voter;
import Africa.semicolon.my_VotingApp.data.repositories.VotersRepository;
import Africa.semicolon.my_VotingApp.exception.GeneralServiceException;
//import Africa.semicolon.my_VotingApp.mapper.VotersMapper;
import Africa.semicolon.my_VotingApp.mapper.VotersMapper;
import Africa.semicolon.my_VotingApp.services.VotersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
@Slf4j
public class VotersServiceImpl implements VotersService {

    private final VotersRepository votersRepository;

    private static final int NUMBER_OF_ITEMS_PER_PAGE = 5;

    @Override
    public VoterResponseDto register(VoterRequestDto voterRegisterRequest) {
        log.info("Received voter registration request: {}", voterRegisterRequest);
        Voter voter = VotersMapper.map(voterRegisterRequest);
        voter.setNIN(UUID.randomUUID().toString());
        voter.setVIN(UUID.randomUUID().toString());

        Voter savedVoter = votersRepository.save(voter);
        VoterResponseDto voterRegisterResponse = getVotersResponse(savedVoter);
        return voterRegisterResponse;

    }

    private VoterResponseDto getVotersResponse(Voter savedVoter) {
        VoterResponseDto voterRegisterResponse = new VoterResponseDto();
        voterRegisterResponse.setFirstName(savedVoter.getFirstName());
        voterRegisterResponse.setLastName(savedVoter.getLastName());
        voterRegisterResponse.setGender(savedVoter.getGender());
        voterRegisterResponse.setEmail(savedVoter.getEmail());
        voterRegisterResponse.setPhoneNumber(savedVoter.getPhoneNumber());
        voterRegisterResponse.setNIN(savedVoter.getNIN());
        voterRegisterResponse.setVIN(savedVoter.getVIN());
        voterRegisterResponse.setAddress(savedVoter.getAddress());
        voterRegisterResponse.setMessage("Voters registration successful");
        return voterRegisterResponse;
    }

    @Override
    public VoterResponseDto getVoterById(Long votersId) {
//        if (Objects.isNull(votersId)) throw new GeneralServiceException("Voters id cannot be null");
        return votersRepository.findById(votersId)
                .map(this::toVotersResponseDto)
                .orElseThrow(() -> new GeneralServiceException(String.format("Voter with id %d does not exist"
                        , votersId)));

    }

    @Override
    public VoterResponseDto updateVoter(Long votersId, VoterRequestDto voterRegisterRequest) {
        Voter voter = votersRepository.findById(votersId).orElseThrow(()->new
                GeneralServiceException("Voter not found"));

        voter.setLastName(voterRegisterRequest.getLastName());
        voter.setFirstName(voterRegisterRequest.getFirstName());
        voter.setEmail(voterRegisterRequest.getEmail());
        voter.setAddress(voterRegisterRequest.getAddress());
        voter.setPhoneNumber(voterRegisterRequest.getPhoneNumber());
        voter.setGender(voterRegisterRequest.getGender());
        Voter updatedVoter =  votersRepository.save(voter);

         VoterResponseDto voterResponseDto = getVotersResponse(updatedVoter);

//
          return voterResponseDto;

    }



    public VoterResponseDto toVotersResponseDto(Voter voter) {
        return new VoterResponseDto(
                voter.getFirstName(),
                voter.getLastName(),
                voter.getEmail(),
                voter.getPhoneNumber(),
                voter.getNIN(),
                voter.getVIN(),
                voter.getGender(),
               voter.getAddress(),
                ""

        );
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
