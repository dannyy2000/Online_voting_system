package Africa.semicolon.my_VotingApp.data.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ElectionDto {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
}
