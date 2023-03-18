package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostResponseDto {
    private Long id;
    private String message;
    private boolean isSuccessful;
}
