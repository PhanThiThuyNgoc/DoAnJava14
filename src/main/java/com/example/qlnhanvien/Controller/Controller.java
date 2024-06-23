package com.example.qlnhanvien.Controller;

import com.example.qlnhanvien.Controller.ConnectSV.ConnectServer;
import com.example.qlnhanvien.Model.ObjectGson.Request;
import com.example.qlnhanvien.Model.ObjectGson.Status;
import com.example.qlnhanvien.Model.ObjectGson.Taikhoan;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.StringJoiner;

public class Controller {
    public static Gson gson = new Gson();
    public static boolean dangkitaikhoan(String ten, String Email, String password) {
        Socket sc = ConnectServer.getConnect();
        // guiwr du lieu len server
        Taikhoan tk = new Taikhoan(ten, Email , password);
        String Json = gson.toJson(new Request("/dangkytaikhoan"));
        System.out.println(Json);


        try {
            BufferedWriter writer  = new BufferedWriter( new OutputStreamWriter(sc.getOutputStream(),"UTF-8"));
            writer.write(Json +"\n"); // vừa gửi vừa chứa tập tin
            writer.flush(); // xac nhan tap tin da dc gui xong
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        // xác nhận đ đọc thông báo là server đã dọc được thông tin
        StreamSocket.XacNhanThongTinServer(sc);
        Status st;
        // gửi dữ liệu lên cho server laanf 2

        try {
            BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream(), "UTF-8"));
            Json = gson.toJson(tk);
            Write.write(Json +"\n");// lệnh gửi dữ liệu json lên sever
            Write.flush(); // xác nhận đã gửi  lên sever
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(sc.getInputStream(), "UTF-8")); // doc du lieu tu server gửi về
            String  check = reader.readLine(); // đọc và lưu trữ dữ liệu luu về
            st = gson.fromJson(check,Status.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Trạng thái đăng kí tài khoản " + st.isCheck());
        return st.isCheck();

    }

    public static void main(String[] args) {
        dangkitaikhoan("tran thi quynh nhu","nhutran","1234");

    }

}

