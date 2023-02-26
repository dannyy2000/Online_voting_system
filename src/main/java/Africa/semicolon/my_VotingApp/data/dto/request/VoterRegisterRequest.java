package Africa.semicolon.my_VotingApp.data.dto.request;

import Africa.semicolon.my_VotingApp.data.models.Gender;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class VoterRegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;

}
