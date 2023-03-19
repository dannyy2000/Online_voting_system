package Africa.semicolon.my_VotingApp.data.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountVotesRequestDto {

    private Long postId;
}
