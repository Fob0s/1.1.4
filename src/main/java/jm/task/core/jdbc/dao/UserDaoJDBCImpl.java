package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        //пустой конструктор, я х3 для чего
    }
    public void createUsersTable() {
         String sql = """ 
                CREATE TABLE new_table_users ( 
                ID INT PRIMARY KEY  AUTO_INCREMENT,
                Name VARCHAR(50),
                LastName VARCHAR(50),
                Age INT
                );
                """; // переименовать в users;

        try {
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("Таблица с таким именем уже существует");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE new_table_users;
                """;
        try {
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("Таблицы с именем test_database.new_table_users, не существует.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO new_table_users (Name, Lastname, Age) VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3,  age);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE FROM new_table_users WHERE ID = ?;
                """;
        try {
            PreparedStatement pr = Util.getConnection().prepareStatement(sql);
            pr.setLong(1, id);
            pr.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = """
                SELECT * FROM new_table_users;
                """;
        try {
            Statement pr = Util.getConnection().createStatement();
            ResultSet a = pr.executeQuery(sql);
            while (a.next()){
                result.add(new User(a.getString(2), a.getString(3), a.getByte(4)));
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return result;

    }

    public void cleanUsersTable() {
        String sql = """
                TRUNCATE TABLE new_table_users;
                """;
        try{
            PreparedStatement sp = Util.getConnection().prepareStatement(sql);
            sp.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
