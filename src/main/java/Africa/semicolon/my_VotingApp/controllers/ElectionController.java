package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.CreateElectionRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.ElectionDto;
import Africa.semicolon.my_VotingApp.data.models.Election;
import Africa.semicolon.my_VotingApp.services.ElectionService;
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
    public ResponseEntity<?> createElection(@RequestBody CreateElectionRequest createElectionRequest){
        ElectionDto electionDto = electionService.createElection(createElectionRequest);
        return new ResponseEntity<>(electionDto, HttpStatus.CREATED);
    }

    @GetMapping("{electionId}")
    public ResponseEntity<?> getElectionById(@PathVariable Long electionId){
        Election election = electionService.getElectionById(electionId);
        return ResponseEntity.status(HttpStatus.OK).body(election);
    }
}
