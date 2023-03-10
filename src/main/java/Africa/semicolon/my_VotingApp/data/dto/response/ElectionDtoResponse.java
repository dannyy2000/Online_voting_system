package Africa.semicolon.my_VotingApp.data.dto.response;

import Africa.semicolon.my_VotingApp.data.models.Candidate;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ElectionDtoResponse {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private Set<Candidate>candidates;
}
