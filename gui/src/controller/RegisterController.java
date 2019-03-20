package controller;

import configuration.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import service.AlertService;
import service.WindowService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class RegisterController {

  DBConnector dbConnector;
  Connection connection;
  PreparedStatement ps;

  @FXML
  private TextField tf_name;

  @FXML
  private TextField tf_lastname;

  @FXML
  private TextField tf_email;

  @FXML
  private PasswordField pf_password;


  @FXML
  private PasswordField pf_password1;

  private void clear() {
    tf_name.clear();
    tf_lastname.clear();
    tf_email.clear();
    pf_password.clear();
    pf_password1.clear();
  }

  @FXML
  void clearAction(ActionEvent event) {
    clear();
  }

  @FXML
  void keyRegisterAction(KeyEvent event) throws IOException {
    //ENTER-rejestracja,ESC-clear
    if (event.getCode() == KeyCode.ENTER) {
      insertData();
    } else if (event.getCode() == KeyCode.ESCAPE) {
      clear();
    }

  }

  @FXML
  void registerAction(ActionEvent event) throws SQLException, IOException {
    insertData();

  }

  private void insertData() throws IOException {

    //insert into INSERT INTO `blog`.`users` (`id`, `login`, `password`, `gender`, `active`, `date_added`) VALUES (DEFAULT, 'mk', 'mk', 'M', 1, '2019-03-18');
    //przygotowanie zapytania
    try {
      //sprawdzenie czy pola są puste
      if (tf_email.getText().equals("") || tf_lastname.getText().equals("") || tf_name.getText().equals("") || pf_password.getText().equals("")) {
        throw new NullPointerException();
      }

      //sprawdzenie czy hasła są jednakowe
      if (!pf_password.getText().equals(pf_password1.getText())) {
        throw new InputMismatchException();
      }

      ps = connection.prepareStatement("INSERT INTO users VALUES (default,?,?,?,?,default ,default ,default ) ");
      ps.setString(1, tf_name.getText());
      ps.setString(2, tf_lastname.getText());
      ps.setString(3, tf_email.getText());
      ps.setString(4, pf_password.getText());
      ps.executeUpdate();

      AlertService.showAlert(Alert.AlertType.INFORMATION, "Zarejestrowano użytkownika", "Zarejestrowano) użytkownika" + tf_email.getText());

      clear();
      //zamknięcie okna
      WindowService.showWindow("/view/loginView.fxml", "panel logowania");
      WindowService.closeWindow(tf_email);

    } catch (SQLException e) {
      AlertService.showAlert(Alert.AlertType.INFORMATION, "Podany login już istnieje", "Musisz podać inny login");
    } catch (InputMismatchException e) {
      AlertService.showAlert(Alert.AlertType.INFORMATION, "Podane hasła nie są jednakowe", "Musisz podać jednakowe hasła");
    } catch (NullPointerException e) {
      AlertService.showAlert(Alert.AlertType.INFORMATION, "Nie uzupełnione dane", "Musisz wypełnić wszystkie pola");
    }


  }

  public void initialize() throws SQLException {
    dbConnector = new DBConnector();
    connection = dbConnector.initConnection();

  }

}
