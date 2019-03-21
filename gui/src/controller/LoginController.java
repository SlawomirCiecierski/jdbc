package controller;

import configuration.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.WindowService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    //globalne obiekty połącenia do bazy danych
  Connection connection;
  PreparedStatement ps;
    //globalny obiet połaczenia do bazy danych
  DBConnector dbConnector;

  @FXML
  private TextField tf_login;

  @FXML
  private PasswordField pf_password;
  //globalny statyczny
  public static int id_logged;

  @FXML
  void loginAction(ActionEvent event) throws SQLException, IOException {
      //przygotowanie zapytania
    ps = connection.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");

      //przypisuję wartości do '?'
    ps.setString(1, tf_login.getText());
    ps.setString(2, pf_password.getText());

      //wykonuje zapytanie
      //dla SELECT - executeQuery() lub execute()
      //dla INSERT UPDATE, DELETE, CREATE, DROP -> executeUpdate()
      //wykonano i zwrócono wynik do tablicy wielowymiarowej
    ResultSet resultSet = ps.executeQuery();


    //przesuwam wskaźnik na pierwszą pozycję i sprawdzam czy jest not null
    if (resultSet.next()) {
      //jeżeli not null to pokazuję całośc
      id_logged = resultSet.getInt(1);
      WindowService.showWindow("/view/courseView.fxml","Formularz operacji na kursach");
      WindowService.closeWindow(tf_login);
    } else {
      System.out.println("Błąd logowania!");
    }
  }

  @FXML
  void registerAction(ActionEvent event) throws IOException {
    Stage stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/view/registerView.fxml"));
    stage.setTitle("panel rejestracji");
    stage.setScene(new Scene(root));
    stage.show();
  }


  public void initialize() throws SQLException {
    DBConnector dbConnector = new DBConnector();
    connection = dbConnector.initConnection();

  }
}
