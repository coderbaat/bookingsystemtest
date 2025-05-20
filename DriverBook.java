public class DriverBook {
	public static void main(String[] args) {
		SlotManager slotManager1 = new SlotManager(10);
		
		UserInterface userInterface1 = new UserInterface(slotManager1,dbUrl,dbUsername,dbPassword);
		userInterface1.displayBookingMenu();
	}
}
