package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteResponseDto {
    private Long id;
    private Long votersId;
    private Long postId;
    private LocalDateTime time;
}
