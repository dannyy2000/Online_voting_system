package Africa.semicolon.my_VotingApp.data.repositories;

import Africa.semicolon.my_VotingApp.data.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election,Long>  {
}
