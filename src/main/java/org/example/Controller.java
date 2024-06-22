package org.example;

import com.google.gson.Gson;
import org.example.DAO.CongViecDAO;
import org.example.DAO.NhanVienDAO;
import org.example.DAO.TaiKhoanDAO;
import org.example.Model.NhanVien;
import org.example.Model.ObjectGson.ID;

import org.example.objectGson.DangNhap;
import org.example.objectGson.Request;
import org.example.objectGson.Status;

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
}
