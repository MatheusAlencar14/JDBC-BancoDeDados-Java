package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program1 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM department");

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + " - " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
            DB.closeConnection();   
        }
    }
}
