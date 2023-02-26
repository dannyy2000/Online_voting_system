package Africa.semicolon.my_VotingApp.data.dto.request;

import Africa.semicolon.my_VotingApp.data.models.Candidate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CreateElectionRequest {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<Candidate> candidates;
}
