package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoterRegisterResponse {
    private Long id;
    private String message;
    private int code;
    private boolean isSuccess;
}
