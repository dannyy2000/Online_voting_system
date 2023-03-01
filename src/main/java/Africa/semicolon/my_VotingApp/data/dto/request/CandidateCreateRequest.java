package Africa.semicolon.my_VotingApp.data.dto.request;

import Africa.semicolon.my_VotingApp.data.models.Candidate;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateCreateRequest {

    private String name;
    private String party;
}
