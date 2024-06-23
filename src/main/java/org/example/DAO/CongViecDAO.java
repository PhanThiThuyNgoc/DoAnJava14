package org.example.DAO;

import org.example.Model.CongViec;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static ObservableList<CongViec> getListCongViec(String email, String trangThai){
        ObservableList<CongViec> listCV = FXCollections.observableArrayList();
        listCV.clear();
        String query = "SELECT `IDCongViec`, `HanNop`, `Trangthaithuchien`, `Tencongviec` FROM `congviec` INNER JOIN `nhanvien` ON congviec.IDNhanVien = nhanvien.IDNhanVien AND nhanvien.Gmail = '"+email+"' AND `Trangthaithuchien` = '"+trangThai+"'";
        try {
            ResultSet Congviec = new ConnectDB().getStmt().executeQuery(query);
            while (Congviec.next()){
                CongViec cv = new CongViec();
                cv.setIDCongviec(Congviec.getString("IDCongViec"));
                cv.setTencongviec(Congviec.getString("Tencongviec"));
                cv.setHanNop(Congviec.getString("HanNop"));
                // xử lý trạng thái công việc
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                String trangthai  = null;
                if (trangThai.equals("1")){
                    trangthai = "Hoàn thành";
                } else if(trangThai.equals("2")){
                    trangthai = "Đang thực hiện";
                } else if (trangThai.equals("3")) {
                    trangthai = "Bị hủy ";

                } else  {
                    trangthai = "Chưa hoàn thành ";
                }
                cv.setTrangthaithuchien(trangthai);
                listCV.add(cv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCV;
    }
    public  static ObservableList<CongViec> getDanhSachCongViec(String email){
        String query = "SELECT `IDCongViec`,`HanNop`, `BaoCaoCongViec`, `LinkNopSanPham`, `Trangthaithuchien`, `Tencongviec` FROM `congviec` INNER JOIN nhanvien ON nhanvien.IDNhanVien = congviec.IDNhanVien WHERE nhanvien.Gmail = '"+email+"'  ";
        ObservableList<CongViec> listCongViec = FXCollections.observableArrayList();
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);
            while(cv.next()){
                CongViec cvTmp = new CongViec();
                cvTmp.setIDCongviec(cv.getString("IDCongViec"));
                cvTmp.setHanNop(cv.getString("HanNop"));
                cvTmp.setTencongviec(cv.getString("Tencongviec"));

                // xử lý trạng thái công việc
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                String trangthai  = null;
                if (cv.getString("Trangthaithuchien").equals("1")){
                    trangthai = "Hoàn thành";
                } else if(cv.getString("Trangthaithuchien").equals("2")){
                    trangthai = "Đang thực hiện";
                } else if (cv.getString("Trangthaithuchien").equals("3")) {
                    trangthai = "Bị hủy ";

                } else  {
                    trangthai = "Chưa hoàn thành ";

                }
                cvTmp.setTrangthaithuchien(trangthai);
                listCongViec.add(cvTmp);

            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listCongViec;
    }

    public static boolean ChuyenTrangThaiCongViec(String IdCongViec){
        String query = "UPDATE `congviec` SET `Trangthaithuchien`='2' WHERE `IDCongViec`= '"+IdCongViec+"' ";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean HoanThanhCongViec(CongViec cv){
        String query = "UPDATE `congviec` SET `BaoCaoCongViec`='"+cv.getBaocaocongviec()+"',`LinkNopSanPham`='"+cv.getLinkNopsanpham()+"',`Trangthaithuchien`='1' WHERE `IDCongViec` = '"+cv.getIDCongviec()+"' ";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
