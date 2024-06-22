package org.example.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CongViecDAO {

    public  static  int getCoutCongviec(){
        String query = "SELECT COUNT(*) FROM congviec ";
        int soluongcongviec = 0;
        try {
            ResultSet congviec = new ConnectDB().getStmt().executeQuery(query);
            while (congviec.next()){
                soluongcongviec = congviec.getInt(1);
            }
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return  soluongcongviec;
    }

    public  static  int getCoutCongviechoanthanh(){
        String query = "SELECT COUNT(*) FROM congviec WHERE `Trangthaithuchien` = '1'";
        int  congviechoanthanh =0;
        try {
            ResultSet data = new ConnectDB().getStmt().executeQuery(query);
            while (data.next()){
                congviechoanthanh = data.getInt(1);
            }

        }catch(SQLException e){
            throw  new RuntimeException(e);
        }
        return  congviechoanthanh;
    }
}
