package Africa.semicolon.my_VotingApp.data.repositories;

import Africa.semicolon.my_VotingApp.data.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

}
