package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * Sistemas operativos I
 * Programación concurrente
 * 28/08/2021
 * 
 * Luis Renato Granados Ogaldez ---- 1190-19-4642
 * Diego Alejando Catalaán 		---- 1190-19-3644
 * @author USUARIO
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("./view/Taller.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("/application/partials/LOGO.png"));
			primaryStage.setTitle("Concurrencia en java");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
