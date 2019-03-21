package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Courses;
import model.SubmissionView;
import service.CourseService;
import service.WindowService;

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
  private TableView<SubmissionView> tab_course;

  @FXML
  private TableColumn<SubmissionView, String> c_name;

  @FXML
  private TableColumn<SubmissionView, String> c_lastname;

  @FXML
  private TableColumn<SubmissionView, String> c_email;

  @FXML
  private TableColumn<SubmissionView, String> c_course;

  @FXML
  private TableColumn<SubmissionView, String> c_trainer;

  @FXML
  private TableColumn<SubmissionView, LocalDate> c_date;

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
    System.exit(0);

  }

  @FXML
  void logoutAction(ActionEvent event) throws IOException {
    WindowService.showWindow("/view/loginWiev.fxml", "Panel logowania");

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
    CourseService courseService = new CourseService();
    System.out.println(LoginController.id_logged);

    //wykonanie zapytania ile jest dostępnych kursów
    int allCoursesCount = courseService.getCountAllCourses();
    String allCoursesCounLabel = "liczba dostępnych kursów: " + allCoursesCount;
    lbl_course_count.setText(allCoursesCounLabel);

    //wykonanie zapytania na ile kursów jestem zapisany
    int myCourseCount = courseService.getMyCourses(LoginController.id_logged);
    String myCoursesCountLabel = "liczba kursów na które jesteś zapisany: " + myCourseCount;
    lbl_submission_count.setText(myCoursesCountLabel);

    //wpisanie kursów z db do kontrolki combobox
    cb_save.setItems(courseService.getAllCourses());
    cb_update.setItems(courseService.getAllCourses());

    //wypisanie rekordów z widoku do modelu
    ObservableList<SubmissionView> submissions_list = courseService.getAllSubmissions(LoginController.id_logged);

//wypisanie rekordów z widoku d tabeli
    c_name.setCellValueFactory(new PropertyValueFactory<>("username"));
    c_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    c_email.setCellValueFactory(new PropertyValueFactory<>("email"));
    c_course.setCellValueFactory(new PropertyValueFactory<>("course_name"));
    c_trainer.setCellValueFactory(new PropertyValueFactory<>("trainer"));
    c_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));

//wprowadzenie wartości z listy
    tab_course.setItems(submissions_list);
  }
}
