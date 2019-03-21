package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.Courses;
import service.CourseService;

public class CourseController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private MenuItem m_logout;

  @FXML
  private MenuItem m_exit;

  @FXML
  private TableView<?> tab_course;

  @FXML
  private TableColumn<?, ?> c_name;

  @FXML
  private TableColumn<?, ?> c_lastname;

  @FXML
  private TableColumn<?, ?> c_email;

  @FXML
  private TableColumn<?, ?> c_course;

  @FXML
  private TableColumn<?, ?> c_trainer;

  @FXML
  private TableColumn<?, ?> c_date;

  @FXML
  private ComboBox<Courses> cb_save;

  @FXML
  private Button btn_save;

  @FXML
  private Button btn_delete;

  @FXML
  private ComboBox<Courses> cb_update;

  @FXML
  private Button btn_update;

  @FXML
  private Label lbl_course_count;

  @FXML
  private Label lbl_submission_count;

  @FXML
  void deleteAction(ActionEvent event) {

  }

  @FXML
  void exitAction(ActionEvent event) {

  }

  @FXML
  void logoutAction(ActionEvent event) {

  }

  @FXML
  void saveAction(ActionEvent event) {

  }

  @FXML
  void selectRowAction(MouseEvent event) {

  }

  @FXML
  void updateAction(ActionEvent event) {

  }

//wykonuje się na początku
  @FXML
  void initialize() throws SQLException {
    CourseService courseService=new CourseService();
    System.out.println(LoginController.id_logged);

    //wykonanie zapytania ile jest dostępnych kursów
int allCoursesCount = courseService.getCountAllCourses();
String allCoursesCounLabel= "liczba dostępnych kursów: "+allCoursesCount;
lbl_course_count.setText(allCoursesCounLabel);
    //wykonanie zapytania na ile kursów jestem zapisany
    int myCourseCount=courseService.getMyCourses(LoginController.id_logged);
    String myCoursesCountLabel="liczba kursów na które jesteś zapisany: "+myCourseCount;
    lbl_submission_count.setText(myCoursesCountLabel);
    //wpisanie kursów z db do kontrolki combobox
    cb_save.setItems(courseService.getAllCourses());
    cb_update.setItems(courseService.getAllCourses());

  }
}
