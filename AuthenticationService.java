
public class AuthenticationService {
	private UserRepository userRepository;
	public AuthenticationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public boolean register(String username, String password) {
		if (userRepository.findUserByUsername(username)!=null) {
			System.out.println("Username already exists.");
			return false;
		}
		String hashedpassword = Integer.toString(password.hashCode());
		User newUser = new User(username, hashedpassword);
		if (userRepository.registerUser(newUser)) {
			System.out.println("User registered successfully!");
			return true;
		} else {
			System.out.println("Registration failed.");
			return false;
		}
	}
	public boolean login(String username, String password) {
		User user = userRepository.findUserByUsername(username);
		if (user!=null) {
			String hashedPassword = Integer.toString(password.hashCode());
			if (user.getPassword().equals(hashedPassword)) {
				System.out.println("Login successful!");
                return true;
			}	
		}
		System.out.println("Invalid username or password.");
		return false;
	}
	public User getLoggedInUser(String username) {
        return userRepository.findUserByUsername(username);
    }

}
