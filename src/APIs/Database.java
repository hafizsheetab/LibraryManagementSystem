package APIs;


import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;

public class Database {
    static String url = "jdbc:oracle:thin:@localhost:1521/XE";;
    static String admin = "SYSTEM";
    static String password = "1234";
    public static boolean putUserName(int id,String userName, String password, String userType){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "INSERT INTO USERS VALUES ('"+id +"','" + userName +"','" + password + "','" + userType + "')";
            statement.execute(sqlQuery);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean signIn(int id, String password1) throws Exception {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM USERS WHERE ID = " + id + ", PASSWORD =" + password1 + "";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            return resultSet.next();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static Dictionary[] getBorrowedBooks(int id){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT BOOKS.NAME, BORROWED.BORROWED_DATE, BORROWED.TO_BE_RETURNED FROM BORROWED NATURAL JOIN BOOKS";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Dictionary<String,String>[] dictionary = new Dictionary[100];
            int count = 0;
            while(resultSet.next()){
                dictionary[count].put("bookName",resultSet.getString(1));
                dictionary[count].put("borrowedDate",resultSet.getString(2));
                dictionary[count].put("toBeReturnedDate",resultSet.getString(3));
                count++;
            }
            return dictionary;


        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static Dictionary getUser(int id){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM USERS WHERE ID = " + id +"";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Dictionary<String, String> dictionary = null;
            while(resultSet.next()){
                dictionary.put("name",resultSet.getString(2));
                dictionary.put("userType",resultSet.getString(4));
            }
            return dictionary;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static Dictionary[] getBooksToBorrow(int privilege){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url,admin,password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT BOOKS.NAME, WRITERS.NAME, BOOKS.ID FROM BOOKS NATURAL JOIN WRITERS WHERE BOOKS.PRIVILEGE < " + privilege +"";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Dictionary<String,String> [] dictionaries = new Dictionary[100];
            int count = 0;
            while(resultSet.next()){
                dictionaries[count].put("bookName",resultSet.getString(1));
                dictionaries[count].put("writerName",resultSet.getString(2));
                dictionaries[count].put("writerId",resultSet.getString(3));
                count++;
            }
            return dictionaries;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean borrowABook (int userId, int bookId){
        return false;
    }

    public static boolean returnABook (int userId, int bookId){
        return false;
    }

}