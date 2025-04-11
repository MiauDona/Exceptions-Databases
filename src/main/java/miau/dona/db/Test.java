package miau.dona.db;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            System.out.println("---------------------------Empieza");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miaudonatest", "admindona", "miaudona");

            preparedStatement = con.prepareStatement("select id, nombre from test1");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("---------------nofunicona");
            System.out.println(e);

        } finally {
            System.out.println("---------------------Termina");

            try {
                con.close();
                rs.close();
                preparedStatement.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
