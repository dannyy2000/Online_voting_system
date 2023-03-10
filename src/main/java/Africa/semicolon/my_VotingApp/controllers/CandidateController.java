package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.CandidateRequestDto;
import Africa.semicolon.my_VotingApp.data.dto.response.CandidateResponseDto;
import Africa.semicolon.my_VotingApp.data.models.Candidate;
import Africa.semicolon.my_VotingApp.data.repositories.CandidateRepository;
import Africa.semicolon.my_VotingApp.services.CandidateService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/candidate")
@AllArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    @PostMapping("create")
    public ResponseEntity<?>create(@RequestBody CandidateRequestDto candidateRequestDto){
        CandidateResponseDto candidateResponseDto = candidateService.createCandidate(candidateRequestDto);
        return new ResponseEntity<>(candidateResponseDto,HttpStatus.CREATED);
    }

    @GetMapping("{candidateId}")
    public ResponseEntity<?>getCandidateById(@PathVariable Long candidateId){
        CandidateResponseDto candidateResponseDto = candidateService.getCandidateById(candidateId);
        return new ResponseEntity<>(candidateResponseDto,HttpStatus.FOUND);
    }

//    @PatchMapping(value = "{candidateId}",consumes = "application/json-patch+json")
//    public ResponseEntity<?>updateCandidate(@PathVariable Long candidateId, @RequestBody JsonPatch updatePayload){
//
//        try{
//            Candidate candidate = candidateService.updateCandidate(candidateId, updatePayload);
//            return ResponseEntity.status(HttpStatus.OK).body(candidate);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PutMapping("{candidateId}")
    public ResponseEntity<?>updateCandidates(@PathVariable Long candidateId,@RequestBody CandidateRequestDto
            candidateRequestDto){
        CandidateResponseDto candidateResponseDto = candidateService.updateCandidate(candidateId,candidateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponseDto);
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<?>getAllCandidates(@PathVariable int pageNumber){
        var candidate = candidateService.getAllCandidates(pageNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(candidate);

    }
}
