package org.example.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {
    public static String dangnhapDAO(String matkhau, String Email) {
        ConnectDB connectDB = new ConnectDB();
        String query = "SELECT `Email`, `Level` FROM `taikhoan` WHERE `password` = '" + matkhau + "' AND `Email` = '" + Email + "'";
        String check = "0";
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);
            if(cv.next()) {
                check = cv.getString("Level");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
}
