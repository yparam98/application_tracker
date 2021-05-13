package generator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    private static UserInterface userInterface;

    @Override
    public void start(Stage primaryStage)
    {
        userInterface.setScene(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) throws ClassNotFoundException
    {
        Class.forName("org.mariadb.jdbc.Driver");
        DatabaseManager manager = new DatabaseManager();
//        userInterface = new UserInterface();
//        Application.launch(args);
    }
}
