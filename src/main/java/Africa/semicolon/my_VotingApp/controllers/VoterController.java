package Africa.semicolon.my_VotingApp.controllers;

import Africa.semicolon.my_VotingApp.data.dto.request.VoterRegisterRequest;
import Africa.semicolon.my_VotingApp.data.dto.response.VoterRegisterResponse;
import Africa.semicolon.my_VotingApp.services.VotersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
