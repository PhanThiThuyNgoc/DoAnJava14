package org.example;

import com.google.gson.Gson;
import org.example.objectGson.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class openConnnect {
    public  static  Gson gson = new Gson();
    private int PORT = 4556;

    public openConnnect(){
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server open port [" + PORT + "]");
            while (true){
                Socket socket = serverSocket.accept();// đợi kết nối từ clien
                Thread thread = new Thread(){
                    public  void run () {
                        Controller(socket);
                    }
                };
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    public  void Controller( Socket socket){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                String data = reader.readLine(); // chứa giá trị ở dạng json
                Request request = gson.fromJson(data,Request.class); // chuyển từ từ json sang object của class
                System.out.println("Request: " + request.getRequest());
                switch (request.getRequest()) {
                    case "/dangnhaptaikhoan":
                        Controller.Dangnhaptaikhoan(socket);
                        break;
                    case "/soluongnhanvien":
                        Controller.getCoutNhanvien(socket);
                        break;
                    case "/soluongcongviec":
                        Controller.getCoutCongviec(socket);
                        break;
                    case "/congviechoanthanh":
                        Controller.getCoutCongviechoanthanh(socket);
                        break;
                    case "/laythongtinnhanvienByID":
                        Controller.getThongtinNVbyID(socket);
                        break;
                }
            } catch ( IOException e){
                throw  new RuntimeException(e);

            }

    }
}
