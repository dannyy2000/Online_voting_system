package Africa.semicolon.my_VotingApp.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Candidate {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String party;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Election election;
}
