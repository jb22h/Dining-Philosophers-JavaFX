import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhilosophersProblem extends Application{
	public void start(Stage stage) throws Exception{
		Parent root = (Parent)
				FXMLLoader.load(getClass().getResource("PhilosophersProblem.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("PhilosophersProblem");
		stage.setScene(scene);

		//Ensure the application exits completely when the window is closed
		stage.setOnCloseRequest(e -> {
			Platform.exit();  
			System.exit(0);    
		});

		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
		System.out.println();
	}
}

