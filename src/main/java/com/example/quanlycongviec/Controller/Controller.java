package com.example.quanlycongviec.Controller;

import com.example.quanlycongviec.Controller.ConnectSV.ConnectServer;
import com.example.quanlycongviec.Model.ObjectGson.*;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;

public class Controller {
    public static Gson gson = new Gson();
    public static Status  kiemtradangnhap(String Email, String matkhau) {
        Socket socket = ConnectServer.getConnect();
        Status st;
        DangNhap tk = new DangNhap(Email, matkhau);
        String Json = gson.toJson(new Request("/dangnhaptaikhoan"));
        String DataJson = gson.toJson(tk);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // đọc
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")); //gửi
            writer.write(Json +"\n");
            System.out.println(Json);
            writer.flush();
            // xác nhận server đã nhận được dữ liệu
            //DBUtils.checkStatusServer(socket);

            String text = reader.readLine();
            System.out.println(text);

            System.out.println("đã nhận đc");

            writer.write(DataJson +"\n");
            System.out.println(DataJson);
            writer.flush();
            String check = reader.readLine();
            st = gson.fromJson(check, Status.class);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        System.out.println("Trạng thái đăng nhập thành công " + st.isCheck());
        return st;

    }
}
