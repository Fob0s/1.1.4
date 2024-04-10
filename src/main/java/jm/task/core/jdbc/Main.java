package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserDao userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Rustam", "Bashaev", (byte) 36);
        userDaoJDBC.saveUser("Ivan", "Petrov", (byte) 26);
        userDaoJDBC.saveUser("Petr", "Vasiliev", (byte) 30);
        userDaoJDBC.saveUser("Marya", "Ivanova", (byte) 18);
        List<User> userList = userDaoJDBC.getAllUsers();
        for(User user : userList){
            System.out.println(user);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }
}
