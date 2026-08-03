import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 * This class represents a single chopstick used in the Dining Philosophers simulation.
 * Each chopstick is a shared resource between two neighboring philosophers.
 * A chopstick can be either "taken" or "available".
 * 
 * When a philosopher wants to pick up a chopstick, it must be available.
 * If it is taken, the philosopher waits .
 * When a chopstick is released, waiting philosophers are notified.
 * Visibility of the chopstick in the UI is updated accordingly.
 */

public class Chopsticks {

	private final int id; //Unique ID for this chopstick (0 to 4)
	private boolean taken = false;  //Indicates whether the chopstick is currently held
	private final ImageView view; //Reference to the UI representation of the chopstick


	//Constructor
	public Chopsticks(int id, ImageView view) {
		this.id = id;
		this.view = view;
	}

	//Returns whether the chopstick is currently taken
	public synchronized boolean isTaken() {
		return taken;
	}

	/**
	 * Philosopher tries to pick up the chopstick.
	 * If it is already taken, the thread waits.
	 * Once available, it marks it as taken and hides the visual stick.
	 */
	public synchronized void pickUp() throws InterruptedException {
		while (taken) { 
			wait(); //Wait until the chopstick is available
		}
		
		taken = true;
		//Hide the chopstick in the GUI once picked up
		Platform.runLater(() -> view.setVisible(false));  
	}

	/**
	 * Philosopher releases the chopstick.
	 * It becomes available, is shown again in the UI, and any waiting threads are notified.
	 */
	public synchronized void putDown() {
		taken = false;
		//Show the chopstick again in the GUI
		Platform.runLater(() -> view.setVisible(true));  
		//Notify any philosopher waiting for this chopstick
		notifyAll();
	}

	//Returns the ID of the chopstick
	public int getId() {
		return id;
	}
}
