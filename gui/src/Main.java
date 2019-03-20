import configuration.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.AlertService;
import service.WindowService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

    WindowService.showWindow("/view/loginView.fxml", "panel logowania");
        DBConnector db=new DBConnector();
        Connection connection= db.initConnection();
        db.showConnectionWarning(connection);
    }

    public void showConnectionWaring(Connection connection) throws SQLException {
        AlertService.showAlert(Alert.AlertType.WARNING, "DATABASE WARNING ", connection.getWarnings().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
