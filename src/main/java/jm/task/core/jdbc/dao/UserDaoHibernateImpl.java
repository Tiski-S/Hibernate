package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String createTable = "create table IF NOT EXISTS users ( id int primary key auto_increment, name varchar(50) not null , lastName varchar(50) not null, age tinyint not null)";
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана!");
        } catch (HibernateException e) {
            if (transaction != null) {
                System.out.println("ERROR!");
                transaction.rollback();

            }
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String dropTable = "drop table if exists users";
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена!");
        } catch (HibernateException e) {
                if (transaction != null) {
                    System.out.println("ERROR!");
                    transaction.rollback();
                }

//            System.out.println("ERROR!");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User создан!");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("ERROR!");
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("USer удалён!");
        } catch (HibernateException e) {
            if (transaction != null) {
                System.out.println("ERROR!");
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (HibernateException e) {
            System.out.println("Ошибка вывода!");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        String cleanTable = "delete from users";
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(cleanTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена!");
        } catch (HibernateException e) {
            if (transaction != null) {
                System.out.println("ERROR!");
                transaction.rollback();

            }
        }

    }
}
