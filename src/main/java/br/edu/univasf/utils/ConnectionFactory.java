package br.edu.univasf.utils;
import java.sql.*;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5433/EngSoftware";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public Connection getConnection (){
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if(conn !=  null){
                System.out.println("Connection Established");
            }else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }

    public static void createTable(Connection conn, String tableName, String query)
    {
        Statement statement;
        try{

            String finalQuery = "create table " + tableName + " " + query ;
            //System.out.println(finalquery);
            statement = conn.createStatement();
            statement.executeUpdate(finalQuery);
            System.out.println("Tabela" + tableName + "criada com sucesso!");

        }catch(Exception e){
            System.out.println(e);
        }

    }

}
