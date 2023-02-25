package Africa.semicolon.my_VotingApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyVotingAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testDatabaseConnection() {
		DriverManagerDataSource
				dataSource = new DriverManagerDataSource("jdbc:mysql://127.0.0.1:3306/", "root",
				"Oluwatomiwa2020");
		try {
			Connection connection = dataSource.getConnection();
			assertThat(connection).isNotNull();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
