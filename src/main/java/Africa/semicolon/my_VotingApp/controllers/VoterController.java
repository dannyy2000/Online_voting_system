package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRegisterRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterRegisterResponse;
import Africa.semicolon.my_VotingApp.services.VotersService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
//import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/voters")
@AllArgsConstructor
public class VoterController {

    private final VotersService votersService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody VoterRegisterRequest voterRegisterRequest){
        VoterRegisterResponse voterRegisterResponse = votersService.register(voterRegisterRequest);
        return ResponseEntity.status(voterRegisterResponse.getCode()).body(voterRegisterResponse);
    }

    @GetMapping("{votersId}")
    public ResponseEntity<?> getVotersById(@PathVariable Long votersId){
        var voter = votersService.getVoterById(votersId);
        return ResponseEntity.status(HttpStatus.OK).body(voter);
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<?> getAllVoters(@PathVariable int pageNumber){
        var voter = votersService.getAllVoters(pageNumber);
        return ResponseEntity.ok(voter.getContent());
    }

    @PatchMapping(value = "{votersId}",consumes = "application/json-patch+json")
    public ResponseEntity<?> updateVoters(@PathVariable Long votersId, @RequestBody JsonPatch updatePatch){
        try{
            var voter = votersService.updateVoter(votersId,updatePatch);
            return ResponseEntity.status(HttpStatus.OK).body(voter);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("{votersId}")
    public ResponseEntity<?> deleteVoters(@PathVariable Long votersId){
       votersService.deleteVoter(votersId);
       return ResponseEntity.ok("Voter deleted successfully");
    }

}
