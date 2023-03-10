package Africa.semicolon.my_VotingApp.utils;

import lombok.*;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {

    private ZonedDateTime time;
    private Object data;
    private int statusCode;
    private Boolean isSuccessful;
}
