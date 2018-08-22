package caa.sportify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import caa.sportify.database.Fixtures;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Crispin A.
 *
 */
public class App extends Application {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
		Fixtures.startUpUpdate();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage = getMainStage();
		primaryStage.show();
	}

	/**
	 * 
	 * Sets the title of the stage, sets the stage dimensions and specifies the
	 * scene to be used on the stage.
	 * 
	 * @return the main stage of the application
	 * @throws IOException
	 */
	private Stage getMainStage() throws IOException {
		Stage stage = new Stage();
		stage.setTitle(com.vendor.App.getName());
		stage.setMinWidth(1250.0);
		stage.setMinHeight(700.0);
		stage.setScene(getMainScene());
		return stage;
	}

	/**
	 * 
	 * Loads the main application FXML layout into a new scene and returns the
	 * scene.
	 * 
	 * @return the main scene of the application
	 * @throws IOException
	 */
	private Scene getMainScene() throws IOException {
		Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Main.fxml")));
		return scene;
	}

}
