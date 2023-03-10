package Africa.semicolon.my_VotingApp.data.dto.response;

import Africa.semicolon.my_VotingApp.data.models.Address;
import Africa.semicolon.my_VotingApp.data.models.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoterResponseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String NIN;
    private String VIN;
    private Gender gender;
    private Address address;
    @JsonIgnore
    private String message;

}
