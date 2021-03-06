package service;

import configuration.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Courses;
import model.SubmissionView;

import java.sql.*;
import java.time.LocalDate;

public class CourseService {
  private Connection connection;


  public CourseService() throws SQLException {
    DBConnector db = new DBConnector();
    connection = db.initConnection();
  }

  public int getCountAllCourses() throws SQLException {
    //select count(*) from courses;

    Statement stm = connection.createStatement();
    ResultSet resultSet = stm.executeQuery("SELECT COUNT(*) FROM courses");

    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
//    resultSet.close();
//    stm.close();
    return 0;

  }

  public int getMyCourses(int id) throws SQLException {

    //select count(*) from submission where id_u=1;
    Statement stm = connection.createStatement();
    ResultSet resultSet = stm.executeQuery("SELECT COUNT(*) FROM submission WHERE id_u=" + id);
    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
    resultSet.close();
    ;
    stm.close();
    return 0;
  }

  public ObservableList<Courses> getAllCourses() throws SQLException {
    Statement stm = connection.createStatement();

    ResultSet resultSet = stm.executeQuery("SELECT * FROM courses");

    //wprowadzanie rekordów z db do listy obiektów klasy modelu
    ObservableList<Courses> courses_list = FXCollections.observableArrayList();
    while (resultSet.next()) {
      Courses c = new Courses(
              resultSet.getInt(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getDate(4).toLocalDate(),
              resultSet.getString(5),
              resultSet.getDouble(6),
              resultSet.getInt(7));
      courses_list.add(c);

    }
    return courses_list;
  }

  public ObservableList<SubmissionView> getAllSubmissions(int id) throws SQLException {
    Statement stm = connection.createStatement();

    ResultSet rs = stm.executeQuery("SELECT * FROM submission_view WHERE email = (SELECT email FROM users WHERE id_u ="+id+")");
    ObservableList<SubmissionView> submission_list = FXCollections.observableArrayList();
    while (rs.next()) {
      SubmissionView sv = new SubmissionView(
              rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
              rs.getString(4),
              rs.getString(5),
              rs.getString(6),
              rs.getDate(7).toLocalDate()
      );
      submission_list.add(sv);
    }
    return submission_list;

  }

  public void saveUserOnCourse (int id_u, int id_c) throws SQLException {

    PreparedStatement ps= connection.prepareStatement(
            "INSERT INTO submission VALUES (default, ?, ?)"
    );
    ps.setInt(1, id_u);
    ps.setInt(2,id_c);
    ps.executeUpdate();

  }

  public void deleteMyselfFromCourse(int id_s) throws SQLException {

    PreparedStatement ps=connection.prepareStatement(
            "DELETE FROM submission WHERE id_s=?"
            );
    ps.setInt(1, id_s);
    ps.executeUpdate();



  }

  public void updateSubmission(int id_s, int id_c) throws SQLException {


    PreparedStatement ps = connection.prepareStatement(
            "UPDATE submission SET id_c=? WHERE id_s=?"
    );
    ps.setInt(1, id_c);
    ps.setInt(2, id_s);
    ps.executeUpdate();
  }

}
