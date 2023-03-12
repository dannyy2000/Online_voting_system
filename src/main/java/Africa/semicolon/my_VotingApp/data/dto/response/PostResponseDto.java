package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String message;
    private int code;
    private boolean isSuccessful;

}
