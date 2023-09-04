package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramUpdate {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            pst = conn.prepareStatement("UPDATE seller " +
                    "SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)");

            pst.setDouble(1, 200.0);
            pst.setInt(2, 2);

            int rowsAffected = pst.executeUpdate();

            System.out.println("Linhas afetadas: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
