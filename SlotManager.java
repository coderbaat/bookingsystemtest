
public class SlotManager {
	private boolean[] slots;
	
	public SlotManager(int numSlots) {
		slots = new boolean[numSlots];
	}
	
	public int getNumberOfSlots() {
        return slots.length;  
    }

	
	public boolean isSlotAvailable(int slot) {
		return (!slots[slot]);
	}
	public boolean bookSlot(int slot) {
		if (isSlotAvailable(slot)) {slots[slot] = true; return true;}
		return false;
	}
	public boolean cancelBooking(int slot) {
		if (!(isSlotAvailable(slot))) {slots[slot] = false; return true;}
		return false;		
	}
	public void checkAvailableSlots() {
		System.out.println("\n--- Available Slots ---");
		for (int i = 0;i<slots.length;i++) {
			if(!slots[i]) {
				System.out.println("Slot number "+(i+1)+" is available");
			}
		}
	}
}
