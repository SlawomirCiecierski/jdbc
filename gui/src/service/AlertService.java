package service;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class AlertService {



  public static void showAlert( Alert.AlertType alert, String header, String content){

      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setTitle("Ważna informacja");
      a.setHeaderText(header);
      a.setContentText(content);
      a.show();
  }
}
