package Africa.semicolon.my_VotingApp.data.repositories;

import Africa.semicolon.my_VotingApp.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
