package Africa.semicolon.my_VotingApp.data.repositories;

import Africa.semicolon.my_VotingApp.data.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotersRepository extends JpaRepository<Voter,Long> {
}
