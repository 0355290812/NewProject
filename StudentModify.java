package QLSV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class StudentModify {
    public static List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSV", "root", "");
            String sql = "select * from SinhVien";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student std = new Student(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("gender"),
                        resultSet.getString("age"), resultSet.getString("email"), resultSet.getString("phone_number"));
                studentList.add(std);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentList;
    }

    public static void insert(Student student) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSV", "root", "");
            String sql = "insert into SinhVien(name, gender, age, email, phone_number) values (?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, student.getName());
            statement.setString(2, student.getGender());
            statement.setInt(3, Integer.parseInt(student.getAge()));
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone_number());
            
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSV", "root", "");
            String sql = "delete from SinhVien where id = ?";
            statement = connection.prepareCall(sql);

            statement.setInt(1, id);
            
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void update(Student student, int id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSV", "root", "");
            String sql = "update SinhVien set name = ?, gender = ?, age = ?, email = ?, phone_number = ? where id = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1, student.getName());
            statement.setString(2, student.getGender());
            statement.setInt(3, Integer.parseInt(student.getAge()));
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone_number());
            statement.setInt(6, id);
            
            statement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static List<Student> search(String name) {
        List<Student> studentList = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLSV", "root", "");
            String sql = "select * from SinhVien where name like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%"+name+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student std = new Student(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("gender"),
                        resultSet.getString("age"), resultSet.getString("email"), resultSet.getString("phone_number"));
                studentList.add(std);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return studentList;
    }
}
