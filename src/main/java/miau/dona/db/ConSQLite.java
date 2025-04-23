package miau.dona.db;

import java.sql.*;

public class ConSQLite {
    public static void main(String[] args) {
        String conexion = "jdbc:sqlite:miau.db";

        try {
            Connection con = DriverManager.getConnection(conexion);
            Statement stmt = con.createStatement();

            createTable(con);
            System.out.println("Tabla creada");

            for (int i = 1; i <= 10; i++) {
                insertData(con, "test1", "nombre", "test ");
            }

            selectTable(con, "nombre", "test1");

            con.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable(Connection con) throws SQLException {
        Statement stmt = null;

        stmt = con.createStatement();

        stmt.execute("" +
                "CREATE TABLE IF NOT EXISTS test1 " +
                "(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(30)" +
                ")");

        stmt.close();
    }

    public static void selectTable(Connection con, String columna, String tabla) throws SQLException {
        Statement stmt = null;

        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT " + columna + " FROM " + tabla);

        while (rs.next()) {
            System.out.println(rs.getString(columna));
        }

        stmt.close();
    }

    public static void insertData(Connection con, String tabla, String columna, String valor) throws SQLException {
        Statement stmt = null;
        stmt = con.createStatement();
        int filasAnanidas = stmt.executeUpdate("INSERT INTO " + tabla + "(" + columna +  ") VALUES (" + valor + ")");
        System.out.println(filasAnanidas + " filas añadidas.");

        stmt.close();
    }
}
