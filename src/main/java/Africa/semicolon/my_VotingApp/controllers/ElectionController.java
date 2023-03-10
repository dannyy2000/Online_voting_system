package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.ElectionRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDtoResponse;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.services.ElectionService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/election")
@AllArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping("create")
    public ResponseEntity<?> createElection(@RequestBody ElectionRequestDto createElectionRequest){
        ElectionDtoResponse electionDto = electionService.createElection(createElectionRequest);
        return new ResponseEntity<>(electionDto, HttpStatus.CREATED);
    }

    @GetMapping("{electionId}")
    public ResponseEntity<?> getElectionById(@PathVariable Long electionId){
        ElectionDtoResponse election = electionService.getElectionById(electionId);
        return ResponseEntity.status(HttpStatus.FOUND).body(election);
    }

   @PatchMapping(value = "{electionId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateElection(@PathVariable Long electionId, @RequestBody JsonPatch updatePatch){
        try {
            ElectionDtoResponse electionDtoResponse = electionService.updateElection(electionId,updatePatch);
            return ResponseEntity.status(HttpStatus.OK).body(electionDtoResponse);

        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

   }
}
