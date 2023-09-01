package application;

import db.DB;

import java.sql.Connection;

public class Program1 {

    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();
    }
}
