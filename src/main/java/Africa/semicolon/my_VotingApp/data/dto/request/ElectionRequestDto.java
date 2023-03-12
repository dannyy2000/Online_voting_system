package Africa.semicolon.my_VotingApp.data.dto.request;

import lombok.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ElectionRequestDto {

    private String name;
    private Set<Long> candidatesId;
}
