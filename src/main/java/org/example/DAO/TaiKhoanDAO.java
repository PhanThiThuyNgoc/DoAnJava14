package org.example.DAO;

import org.example.objectGson.Taikhoan;

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

    public static boolean DangKyTaiKhoanOnDB(Taikhoan tk){
        // tạo dữ liệu trên bảng taikhoan
        String query = "INSERT INTO `taikhoan`(`Ten`, `password`, `Email`, `Level`) VALUES ('"+tk.getTen()+"','"+tk.getPassword()+"','"+tk.getEmail()+"','1')";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
            // tạo dữ liệu trên bảng nhanvien
            query = "INSERT INTO `nhanvien`(`HoVaTen`, `Gmail`, `Trangthai`) VALUES ('"+tk.getTen()+"','"+tk.getEmail()+"','2')";
            new ConnectDB().getStmt().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
