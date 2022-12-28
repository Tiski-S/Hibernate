package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "create table users ( id int primary key auto_increment, name varchar(50) not null , lastName varchar(50) not null, age tinyint not null)";
        Connection con = null;
        Statement statement = null;
        try {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = con.createStatement();
            statement.executeUpdate(createTable);
            con.commit();
            System.out.println("Таблица создана!");
        }
        catch (Exception e){
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            System.out.println("Error, таблица не заздалась!");
        } finally {
            try {
                con.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    public void dropUsersTable() {
        String dropTable = "drop table users";
        Connection con = null;
        Statement statement = null;
        try  {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = con.createStatement();
            statement.executeUpdate(dropTable);
            con.commit();
            System.out.println("Таблица удалена!");
        }
        catch (Exception e){
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            System.out.println("Error, таблица не существует!");
        }
        finally {
            try {
                con.close();
                statement.close();
            } catch (SQLException e) {
            }

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String createUser = "insert into users VALUE (id, ?, ?, ?)";
        Connection con = null;
        try {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement preparedStatement = con.prepareStatement(createUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            con.commit();
            System.out.println("User с именем " + name + " добавлен!");
        }
        catch (Exception e){
            System.out.println("Error, User не добавлен!!");
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Ошибка отката!");
            }
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }

    public void removeUserById(long id) {
        String deleteUser = "delete from users where id = ?";
        Connection con = null;
        try  {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement preparedStatement = con.prepareStatement(deleteUser);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            con.commit();
            System.out.println("User удалён!");
        } catch (Exception e){
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            System.out.println("Error, User не удалён!");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

    }

    public List<User> getAllUsers() {
        String getAll = "select * from users";
        Connection con = null;
        Statement statement = null;
        try {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                con.commit();
            }
            con.commit();
            return users;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            System.out.println("Ошибка вывода!");
        } finally {
            try {
                con.close();
                statement.close();
            } catch (SQLException e) {
            }
        }

        return null;
    }

    public void cleanUsersTable() {
        String cleanTable = "delete from users";
        Connection con = null;
        Statement statement = null;
        try  {
            con = Util.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = con.createStatement();
            statement.executeUpdate(cleanTable);
            con.commit();
            System.out.println("Все записи удалены!");
        }
        catch (Exception e){
            try {
                con.rollback();
            } catch (SQLException ex) {
            }
            System.out.println("Error, записи не удаляются!");
        }
        finally {
            try {
                con.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

}
