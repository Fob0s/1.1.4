package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        //пустой конструктор, я х3 для чего
    }
    public void createUsersTable() {
         String sql = """ 
                create table new_table_users ( 
                ID int primary key AUTO_INCREMENT,
                Name varchar(50),
                LastName varchar(50),
                Age int
                );
                """; // переименовать в users;

        try {
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = """
                drop table new_table_users;
                """;
        try {
            PreparedStatement ps = Util.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                insert into new_table_users (Name, Lastname, Age) values (?, ?, ?)
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
                delete from new_table_users where ID = ?;
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
                select * from new_table_users;
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
                truncate new_table_users;
                """;
        try{
            PreparedStatement sp = Util.getConnection().prepareStatement(sql);
            sp.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
