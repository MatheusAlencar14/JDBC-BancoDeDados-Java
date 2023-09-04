package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInsert {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();

            pst = conn.prepareStatement("INSERT INTO seller " +
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, "Matheus Alencar");
            pst.setString(2, "matheusalencar@gmail.com");
            pst.setDate(3, new Date(sdf.parse("10/09/1994").getTime()));
            pst.setDouble(4, 13500);
            pst.setInt(5, 2);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
