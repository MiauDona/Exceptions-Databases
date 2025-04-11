package miau.dona.db;

import java.sql.*;

public class Test3 {
    public static void main(String[] args)  {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miaudonatest", "admindona", "miaudona");
            introducirMiau(con, consultarID(con));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        } finally {
            try {
                con.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int consultarID(Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select id from test1 order by id desc limit 1");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    public static void introducirMiau(Connection con, int ultimoID) throws SQLException {
        PreparedStatement ps = con.prepareStatement("insert into test1(nombre) values(?)");
        int maxIter = ultimoID % 3 == 0 ? ultimoID + ultimoID/3 : (ultimoID % 2 == 0 ? ultimoID + ultimoID/2 : (ultimoID <= 25 ? ultimoID*2 : ultimoID + ultimoID/5));

        int filas = 0;
        for (int i = ultimoID+1; i <= maxIter; i++) {
            ps.setString(1, "Miau " + i);
            filas += ps.executeUpdate();
        }

        System.out.println(filas + " introducidas");
    }
}
