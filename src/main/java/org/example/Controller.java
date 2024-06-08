package org.example;

import com.google.gson.Gson;
import org.example.DAO.TaiKhoanDAO;
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
}
