package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountVotesResponseDto {
    private Long postId;
    private Long count;
}
