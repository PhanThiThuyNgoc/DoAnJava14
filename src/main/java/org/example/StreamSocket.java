package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class StreamSocket<In> {
    public static void XacNhanrequest(Socket socket) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            writer.write("oke\n");
            writer.flush();
            System.out.println("Đã gửi xác nhận");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //===================================================================================================
    //===================================================================================================
    //===================================================================================================
    // Hàm đọc dữ liệu sau request nhận được
    public In readDataFromClient(Socket socket, Class<In> inClass){
        In dataIn = null;
        Gson gson = new Gson();

        //Khởi tạo phương thức đọc, ghi stream socket
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")); //gửi
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // đọc
            //1. Xác nhận đã nhận được request từ Client (Gửi giá trị bất kì cho Client)
            writer.write("oke\n");
            writer.flush();
            System.out.println("Đã gửi xác nhận");

            //2. Đọc dữ liệu từ Client gửi lên lần 2
            //2.1. Đọc giá trị ở dạng json
            String jsonDataIn = reader.readLine();
            //2.2. Chuyển sang object
            try{
                dataIn = gson.fromJson(jsonDataIn, inClass);
                System.out.println("Dữ liệu nhận được từ Client: "+ jsonDataIn);
            }catch (Exception e){
                System.out.println("Lỗi chuyển giá trị json sang object");
            }
        } catch (IOException e) {
            System.out.println("Lỗi khởi tạo phương thức đọc, ghi socket!");
            throw new RuntimeException(e);
        }
        return dataIn;
    }

    // Hàm gửi giá trị đến Client
    public void sendDataToClient(Socket socket, In dataObject){
        //1. Chuyển dữ liệu object sang chuỗi json
        String dataJson = new Gson().toJson(dataObject);
        //Khởi tạo phương thức đọc, ghi stream socket
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")); //gửi
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // đọc

            //2. Gửi dữ liệu cho Client
            writer.write(dataJson + "\n");
            writer.flush();
            System.out.println("Server đã gửi: "+ dataJson);

            //3. Đóng socket
            socket.close();
        } catch (IOException e) {
            System.out.println("Lỗi khởi tạo phương thức đọc, ghi socket!");
            throw new RuntimeException(e);
        }
    }
}
