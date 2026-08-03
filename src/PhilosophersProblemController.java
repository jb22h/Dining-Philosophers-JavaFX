import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class controls the Dining Philosophers simulation.
 * It initializes five philosophers and five chopsticks, assigns
 * them graphical representations, and starts the simulation.
 * Each philosopher alternates between thinking, eating, and idling.
 * Eating is only possible when the philosopher successfully picks up
 * both adjacent chopsticks.
 */

public class PhilosophersProblemController {

	@FXML private ImageView phil0, phil1, phil2, phil3, phil4;
	@FXML private ImageView stick0, stick1, stick2, stick3, stick4;

	private static final int NUM = 5; // Number of philosophers and chopsticks

	// Array to hold chopstick objects
	private final Chopsticks[]   sticks       = new Chopsticks[NUM];
	// Array to hold philosopher threads
	private final Philosopher[] philosophers = new Philosopher[NUM];

	// Images used to visually represent each philosopher's state and direction
	private final Image idleStraightImg = new Image(getClass().getResource("/pictures/idle_straight.png").toExternalForm());
	private final Image idleLeftImg = new Image(getClass().getResource("/pictures/idle_left.png").toExternalForm());
	private final Image idleRightImg = new Image(getClass().getResource("/pictures/idle_right.png").toExternalForm());
	private final Image thinkingStraightImg = new Image(getClass().getResource("/pictures/thinking_straight.png").toExternalForm());
	private final Image thinkingLeftImg = new Image(getClass().getResource("/pictures/thinking_left.png").toExternalForm());
	private final Image thinkingRightImg = new Image(getClass().getResource("/pictures/thinking_right.png").toExternalForm());
	private final Image eatingStraightImg = new Image(getClass().getResource("/pictures/eating_straight.png").toExternalForm());
	private final Image eatingLeftImg = new Image(getClass().getResource("/pictures/eating_left.png").toExternalForm());
	private final Image eatingRightImg = new Image(getClass().getResource("/pictures/eating_right.png").toExternalForm());


	public void initialize() {
		//Create chopstick objects and associate them with their visual representations
		ImageView[] stickViews = { stick0, stick1, stick2, stick3, stick4 };
		for (int i = 0; i < NUM; i++) {
			sticks[i] = new Chopsticks(i, stickViews[i]);
		}
		//Create philosopher threads and assign their left/right chopsticks and images
		ImageView[] philViews = { phil0, phil1, phil2, phil3, phil4 };
		for (int i = 0; i < NUM; i++) {
			int left,right;
			Image thinkingImg,eatingImg,idleImg; 
			if(i==0) {
				//Philosopher 0 is in a "straight" position
				left = 4;
				right = 0;
				idleImg = idleStraightImg;
				thinkingImg = thinkingStraightImg;
				eatingImg = eatingStraightImg;
			}
			else if (i==1 || i==2) {
				//Philosophers 1 and 2 face right
				left=i-1;
				right=i;
				idleImg = idleRightImg;
				thinkingImg = thinkingRightImg;
				eatingImg = eatingRightImg;
			}
			else {
				//Philosophers 3 and 4 face left
				left=i-1;
				right=i;
				idleImg = idleLeftImg;
				thinkingImg = thinkingLeftImg;
				eatingImg = eatingLeftImg;
			}
			//Create philosopher, assigned chopsticks, visual node, and state images
			philosophers[i] = new Philosopher(sticks[left],sticks[right],philViews[i],thinkingImg,eatingImg,idleImg);

		}
		//Start all philosopher threads
		for (int i = 0; i < NUM; i++) {
			philosophers[i].start();  
		}
	}
}
