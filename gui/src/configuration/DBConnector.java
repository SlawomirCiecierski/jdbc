package configuration;

import javafx.scene.control.Alert;
import service.AlertService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

  public Connection initConnection () throws SQLException {
    String url="jdbc:mysql://localhost:3306/course_manager";
    String user="app_user";
   String password="sda";
   return DriverManager.getConnection(url, user, password);

  }

  public void setTGransactional(Connection connection,boolean isCommited)
          throws SQLException {

    connection.setAutoCommit(isCommited);
  }

public void disconnect(Connection connection) throws SQLException {
    connection.close();


}
public void showConnectionWarning (Connection connection) throws SQLException {
    AlertService.showAlert(Alert.AlertType.WARNING,"DATABASE WARNING", connection.getWarnings()==null? "brak danych": connection.getWarnings().toString());
}

}
