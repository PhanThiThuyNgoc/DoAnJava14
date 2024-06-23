package org.example;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import org.example.DAO.CongViecDAO;
import org.example.DAO.CongViecNV;
import org.example.DAO.NhanVienDAO;
import org.example.DAO.TaiKhoanDAO;
import org.example.Model.CongViec;
import org.example.Model.NhanVien;
import org.example.Model.ObjectGson.ID;

import org.example.objectGson.DangNhap;
import org.example.objectGson.Request;
import org.example.objectGson.Status;
import org.example.objectGson.Taikhoan;

import java.io.*;
import java.net.Socket;



public class Controller {
    public static void Dangnhaptaikhoan(Socket socket) {
        System.out.println("Đã vào đăng nhập tài khoản");
        StreamSocket.XacNhanrequest(socket);

        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            String dataJson = reader.readLine(); // tạo cái biến để hứng reader
            System.out.println("Server đã nhận: "+ dataJson);
            DangNhap dangnhap = gson.fromJson(dataJson, DangNhap.class); // chuyển từ json sang object của class

            String tk = TaiKhoanDAO.dangnhapDAO(dangnhap.getMatkhau(), dangnhap.getEmail());
            String Json = gson.toJson(new Status(true, tk));
            System.out.println(Json);
            writer.write(Json +"\n");
            writer.flush(); // flush là xac nhan đã gửi
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getCoutNhanvien(Socket socket) {
        StreamSocket.XacNhanrequest(socket);
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            String trangthai = reader.readLine();
            String soluongnhanvien = NhanVienDAO.getCoutNhanvien(trangthai);
            System.out.println("Số lượng NV: " + soluongnhanvien);
            writer.write(soluongnhanvien+"\n");
            writer.flush();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCoutCongviec(Socket socket) {
        StreamSocket.XacNhanrequest(socket);
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                int soluongcongviec = CongViecDAO.getCoutCongviec();
            writer.write(soluongcongviec +"\n");
            writer.flush();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCoutCongviechoanthanh(Socket socket) {
        StreamSocket.XacNhanrequest(socket);
        Gson gson = new Gson();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            int congviechoanthanh = CongViecDAO.getCoutCongviechoanthanh();
            writer.write(congviechoanthanh +"\n");
            writer.flush();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getThongtinNVbyID(Socket socket){
        ID idNV = new StreamSocket<ID>().readDataFromClient(socket, ID.class);
        System.out.println(idNV);
        NhanVien nv = NhanVienDAO.getInfoUserByID(idNV.getId());
        new StreamSocket<NhanVien>().sendDataToClient(socket, nv);
    }
    public static void getListCongViec(Socket socket){
        //đọc dữ liệu
        CongViecNV congViecNV = new StreamSocket<CongViecNV>().readDataFromClient(socket, CongViecNV.class);
        //truy vấn
        ObservableList<CongViec> listCV;
        if(congViecNV.getData() == null){
            listCV = CongViecDAO.getDanhSachCongViec(congViecNV.getEmail());
        }else{
            listCV= CongViecDAO.getListCongViec(congViecNV.getEmail(), congViecNV.getData());
        }
        // gửi dữ liệu
        new StreamSocket<CongViec>().sendDataToClientObser(socket, listCV);
    }

    public static void ChuyenTrangThaiCongViec(Socket socket){
        ID idCongViec = new StreamSocket<ID>().readDataFromClient(socket, ID.class);
        boolean check = CongViecDAO.ChuyenTrangThaiCongViec(idCongViec.getId());
        new StreamSocket<Boolean>().sendDataToClient(socket, check);
    }
    public static void HoanThanhCongViec(Socket socket){
        CongViec cv = new StreamSocket<CongViec>().readDataFromClient(socket, CongViec.class);
        boolean check = CongViecDAO.HoanThanhCongViec(cv);
        new StreamSocket<Boolean>().sendDataToClient(socket, check);
    }
    public static void DangKyTaiKhoan(Socket socket){
        Taikhoan tk = new StreamSocket<Taikhoan>().readDataFromClient(socket, Taikhoan.class);
        boolean check = TaiKhoanDAO.DangKyTaiKhoanOnDB(tk);
        new StreamSocket<Boolean>().sendDataToClient(socket, check);
    }

    public static void getInfoUser(Socket socket){
        String email = new StreamSocket<String>().readDataFromClient(socket, String.class);
        NhanVien nv = NhanVienDAO.getInfoUser(email);
        new StreamSocket<NhanVien>().sendDataToClient(socket, nv);
    }
    public static void CapNhatDuLieuNguoiDung(Socket socket){
        NhanVien nv = new StreamSocket<NhanVien>().readDataFromClient(socket, NhanVien.class);
        boolean check = NhanVienDAO.CapNhatDuLieuNguoiDungOnDB(nv);
        new StreamSocket<Boolean>().sendDataToClient(socket, check);
    }

    public static void LayDanhSachNhanVien(Socket socket){
        new StreamSocket<String>().readDataFromClient(socket, String.class);
        ObservableList<String> listNV = NhanVienDAO.getDanhSachTenNhanVien();
        new StreamSocket<String>().sendDataToClientObser(socket, listNV);
    }
}
