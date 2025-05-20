import java.util.Scanner;
public class UserInterface {
	private SlotManager slotManager1;
	private Scanner input;
	private AuthenticationService authenticationService;
	private User currentUser;
	
	public UserInterface(SlotManager slotManager,String dbUrl, String dbUsername, String dbPassword) {
		this.slotManager1 = slotManager;
		this.input = new Scanner(System.in);
		UserRepository userRepository = new UserRepository(dbUrl,dbUsername,dbPassword);
		this.authenticationService = new AuthenticationService(userRepository);
	}
	public void DisplayMenu() {
		while(true) {
			if (currentUser==null) {
				showAuthenticationMenu();	
			} else {
				displayBookingMenu();
			}
		}	
	}
	public void showAuthenticationMenu() {
		System.out.println("\n--- User Authentication ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
        case 1:
        	registerUser();
        	break;
        case 2: 
        	loginUser();
        	break;
        case 3:
        	System.out.println("Exiting system...");
            input.close();
            System.exit(0);
        default:
            System.out.println("Invalid choice. Please try again.");
        }	
	}
	public void registerUser() {
		System.out.print("\nEnter a username: ");
        String username = input.nextLine();
        System.out.print("Enter a password: ");
        String password = input.nextLine();
		if (authenticationService.register(username, password)) {
			System.out.println("Registration successful! You can now log in.");
		} else {
        System.out.println("Registration failed. Username might already be taken.");
		}
	}
	public void loginUser() {
		System.out.print("\nEnter your username: ");
        String username = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        if (authenticationService.login(username, password)) {
        	System.out.println("Login successful! Welcome, " + username + ".");
        	currentUser = authenticationService.getLoggedInUser(username);
        } else {
        	System.out.println("Invalid username or password. Please try again.");
        }
		
	}
	
	public void displayBookingMenu() {
		while(true) {
			System.out.println("\n--- Pool Booking System ---");
            System.out.println("1. Check available slots");
            System.out.println("2. Slot booking");
            System.out.println("3. Booking cancellation");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
            case 1:
                // View available slots
                slotManager1.checkAvailableSlots();
                break;
            case 2:
                // Book a slot
                bookSlot();  // Call bookSlot which handles user input for the slot number
                break;
            case 3:
                // Cancel a booking
                cancelBooking();
                break;
            case 4:
                // Exit the program
                logoutUser();
                return;  // Exit the method to login window
            case 5:
            	System.out.println("Exiting system...");
                input.close();
                System.exit(0);
            default:
                // Invalid choice
                System.out.println("Invalid choice. Please try again.");
            }
		}
	}
	public void logoutUser( ) {
		System.out.println("Logging out " + currentUser.getUsername() + ".");
		currentUser = null;
		
	}
	
	private void bookSlot() {
		System.out.print("\nEnter the slot number 1 to "+slotManager1.getNumberOfSlots()+" : ");
		int slot = input.nextInt() - 1;  // Convert user input to 0-based index
        input.nextLine();
        if (slot < 0 || slot >= 10) {
            System.out.println("Invalid input. Please choose a number between 1 and 10.");
        } else if (slotManager1.bookSlot(slot)) {  // Call bookSlot with the slot parameter
            System.out.println("Slot " + (slot + 1) + " booked.");
        } else {
            System.out.println("slot"+(slot+1)+" is already booked");
        }
	
	}
	private void cancelBooking() {
		System.out.print("\nEnter the slot number you would like to cancel: ");
		int slot = input.nextInt()-1;
		if (slot < 0 || slot >= 10) {
            System.out.println("Invalid input. Please choose a number between 1 and 10.");
		} else if (slotManager1.cancelBooking(slot)) {
			System.out.println("Slot cancelled");
		} else {System.out.println("Slot was not booked yet");}
            
	}
	
}
