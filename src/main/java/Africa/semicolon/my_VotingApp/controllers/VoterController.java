package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterResponseDto;
import Africa.semicolon.my_VotingApp.services.VotersService;
import Africa.semicolon.my_VotingApp.utils.ApiResponse;
import lombok.AllArgsConstructor;
//import lombok.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@Slf4j
@RequestMapping("/api/v1/voters")
@AllArgsConstructor
public class VoterController {

    private final VotersService votersService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody VoterRequestDto voterRegisterRequest) {
        log.info("Received voter registration request: {}", voterRegisterRequest);
        VoterResponseDto voterResponseDto = votersService.register(voterRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(voterResponseDto);
    }


    @GetMapping("{votersId}")
    public ResponseEntity<?> getVotersById(@PathVariable Long votersId){
        VoterResponseDto voterResponseDto = votersService.getVoterById(votersId);
        return ResponseEntity.status(HttpStatus.FOUND).body(voterResponseDto);
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<?> getAllVoters(@PathVariable int pageNumber){
        var voter = votersService.getAllVoters(pageNumber);
        return ResponseEntity.ok(voter.getContent());
    }

    @PutMapping("{votersId}")
    public ResponseEntity<?>updateVoters(@PathVariable Long votersId,@RequestBody VoterRequestDto voterUpdateRequest){
        log.info("Received voter update request: {}", voterUpdateRequest);
        VoterResponseDto voterResponseDto = votersService.updateVoter(votersId, voterUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(voterResponseDto);
    }

    @DeleteMapping("{votersId}")
    public ResponseEntity<?> deleteVoters(@PathVariable Long votersId){
       votersService.deleteVoter(votersId);
       return ResponseEntity.ok("Voter deleted successfully");
    }

}
