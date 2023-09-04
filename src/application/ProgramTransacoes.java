package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramTransacoes {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 4090 WHERE DepartmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 4590 WHERE DepartmentId = 4");

            conn.commit();

            System.out.println("Linha 1: " + rows1);
            System.out.println("Linha 2: " + rows2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transação não concluída. Erro: " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Erro: " + ex.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
