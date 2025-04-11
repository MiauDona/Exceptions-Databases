package miau.dona.db;

import java.sql.*;

public class Test2 {
    public static void main(String[] args)  {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miaudonatest", "admindona", "miaudona");
            stmt = con.prepareStatement("insert into test1 (nombre) values(?)");

            insertMiaus(con, ultimoMiau(con));
            mostrarMiaus(con);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void mostrarMiaus(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select * from test1;");
        stmt.executeQuery();

        stmt.close();
    }

    public static void insertMiaus(Connection con, int inicio) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("insert into test1 (nombre) values(?)");

        int nfilas = 0;

        for (int i = inicio+1; i <= inicio+20; i++) {
            stmt.setString(1, "Miau " + i);
            nfilas += stmt.executeUpdate();
        }

        System.out.println("Executed. " + nfilas + " rows inserted.");

        stmt.close();
    }

    public static int ultimoMiau(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("select nombre from test1");
        ResultSet rs = stmt.executeQuery();

        int maxNum = 0;
        String actualNum = "";

        while (rs.next()) {
            String row = rs.getString(1);
            // System.out.println(row);

            boolean hayEspacio = false;
            for (char c : row.toCharArray()) {
                boolean esNum = (c>='0' && c<='9');
                boolean esEspacio = c == ' ';

                if (esEspacio) {
                    hayEspacio = true;
                }

                if (!esNum && !esEspacio && hayEspacio) {
                    hayEspacio = false;
                    continue;
                }

                if (esNum || esEspacio) {
                    if (esNum) {
                        actualNum += c;
                    }
                } else {
                    actualNum = "";
                }

                if (actualNum != "") {

                    if (Integer.parseInt(actualNum) > maxNum) {
                        maxNum = Integer.parseInt(actualNum);
                    }
                }
            }
        }


        rs.close();
        stmt.close();

        System.out.println("Max num: " + maxNum);
        return maxNum;
    }
}
