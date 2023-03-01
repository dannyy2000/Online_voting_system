package Africa.semicolon.my_VotingApp.data.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<Candidate> candidates;
}
