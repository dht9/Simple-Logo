import controller.Driver;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage stage) {
		Driver driver = new Driver(stage);
		driver.run();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
