import java.util.Random;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents a single philosopher in the Dining Philosophers simulation.
 * Each philosopher runs as a separate thread and continuously repeats the following cycle:
 * 1. Idle (not thinking or eating)
 * 2. Try to pick up both adjacent chopsticks 
 * 3. Eat for a random duration
 * 4. Release the chopsticks
 * 5. Think for a random duration
 * 
 * To prevent deadlock, philosophers always pick up the lower-numbered chopstick first.
 * Visual changes (state images) are updated on the JavaFX Application Thread using Platform.runLater().
 */

public class Philosopher extends Thread {

	private static final int MIN_TIME = 2000; //(5000) //Minimum thinking/eating/idle time
	private static final int MAX_TIME = 5000; //(10000) Maximum thinking/eating/idle time

	private final Chopsticks left, right; //References to left and right chopsticks
	private final ImageView view;    //ImageView representing the philosopher on the screen      
	private final Image thinkingImg;  //Image shown when thinking     
	private final Image eatingImg; //Image shown when eating
	private final Image idleImg;   //Image shown when idle
	private final Random rnd = new Random(); //Random number generator for sleep durations

	//Constructor
	public Philosopher(Chopsticks left,Chopsticks right,ImageView view,Image thinkingImg,Image eatingImg,Image idleImg) {
		this.left = left;
		this.right = right;
		this.view = view;
		this.thinkingImg = thinkingImg;
		this.eatingImg = eatingImg;
		this.idleImg = idleImg;           
	}

	//Show "idle" state and sleep for random duration
	private void idle() throws InterruptedException {
		
		Platform.runLater(() -> view.setImage(idleImg));
		Thread.sleep(random(MIN_TIME, MAX_TIME));
		
	}

	//Show "thinking" state and sleep for random duration
	private void think() throws InterruptedException {
		
		Platform.runLater(() -> view.setImage(thinkingImg));
		Thread.sleep(random(MIN_TIME, MAX_TIME));
		
	}

	//Try to pick up both chopsticks (in a consistent order), then eat
	private void eat() throws InterruptedException{
		//Determine the lower-numbered chopstick to pick up first to avoid deadlock
		Chopsticks first, second;
		if (left.getId() < right.getId()) {
			first = left;
			second = right;
		} 
		else {
			first = right;
			second = left;
		}
		//Attempt to pick up both chopsticks
		first.pickUp();
		
		try {
			second.pickUp();
			try {
				//Show "eating" state and sleep for random duration
				Platform.runLater(() -> view.setImage(eatingImg));
				Thread.sleep(random(MIN_TIME, MAX_TIME));
				} finally {
					second.putDown();
					}
			} finally {
				first.putDown();
				}
		}
	
	@Override
	public void run() {
	    try {
	        while (!Thread.currentThread().isInterrupted()) {
	            idle(); //Start by idling
	            eat(); //Try to pick up chopsticks and eat
	            think(); //Then think
	        }
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	//Generate a random time between min and max
	private int random(int min, int max) {
		return rnd.nextInt(max - min + 1) + min;
	}
}
