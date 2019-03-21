package service;

import configuration.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Courses;

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
    ResultSet resultSet = stm.executeQuery("SELECT COUNT(*) FROM submission WHERE id_u="+id);
    if(resultSet.next()){
      return resultSet.getInt(1);
    }
    resultSet.close();;
    stm.close();
    return 0;
  }

  public ObservableList<Courses> getAllCourses() throws SQLException {
    Statement stm=connection.createStatement();
    String sql;
    ResultSet resultSet=stm.executeQuery("SELECT * FROM courses");

        //wprowadzanie rekordów z db do listy obiektów klasy modelu
    ObservableList<Courses> courses_list=  FXCollections.observableArrayList();
    while (resultSet.next()){
      Courses c=new Courses(
              resultSet.getInt(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getDate(4).toLocalDate(),
              resultSet.getString(5),
              resultSet.getDouble(6),
              resultSet.getInt(7));
      courses_list.add(c);

    }  return courses_list;
  }


}
