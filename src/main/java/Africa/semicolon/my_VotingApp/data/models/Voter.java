package Africa.semicolon.my_VotingApp.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String NIN;
    private String VIN;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @OneToOne
    private Address address;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String votersImage;
}
