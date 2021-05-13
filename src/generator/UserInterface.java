package generator;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class UserInterface
{
    private FlowPane root_pane;
    private Scene scene;
    private

    UserInterface()
    {
        root_pane = new FlowPane();
        root_pane.setMinSize(400, 500);
        scene = new Scene(root_pane, 400, 600);
    }

    public void setScene(Stage stage)
    {
        stage.setScene(this.scene);
    }
}
