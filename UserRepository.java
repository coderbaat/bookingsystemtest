import java.sql.*;
public class UserRepository {
	private Connection connection;
	public UserRepository(String dbUrl, String dbUser, String dbPassword) {
		try {
			connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);	
		} catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException("Failed to connect to database");			
		}
	}
	public boolean registerUser(User user) {
		String sql = "INSERT INTO users(username, password) VALUES (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			return statement.executeUpdate()>0;
		} catch (SQLException e) {
			System.out.println("Error during registration: " + e.getMessage());
			return false;
		}
	}
	public User findUserByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Error finding user: " + e.getMessage());
		}
		return null;
	}
}
