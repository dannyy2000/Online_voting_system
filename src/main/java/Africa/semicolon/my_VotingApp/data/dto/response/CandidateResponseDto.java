package Africa.semicolon.my_VotingApp.data.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidateResponseDto {
    private Long Id;
    private String name;
    private String party;
    @JsonIgnore
    private String message;
}
