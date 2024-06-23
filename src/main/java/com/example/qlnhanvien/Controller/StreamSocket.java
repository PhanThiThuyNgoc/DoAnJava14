package com.example.qlnhanvien.Controller;

import com.example.qlnhanvien.Controller.ConnectSV.ConnectServer;
import com.example.qlnhanvien.Model.ObjectGson.Request;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StreamSocket <In, Out>{
    public Out SendRequest(String request, In dataIn, Class<Out> OutClass){
        // Khởi tạo các object
        Out dataOut = null;
        Gson gson = new Gson();
        //1.Tạo kết nối socket đến Server
        Socket socket = ConnectServer.getConnect();
        //2. Gửi Request lên Server
        //2.1. Khởi tạo kết nối Stream
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // đọc
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")); //gửi
            //2.2. Chuyển request ở dạng String sang json
            String Json = gson.toJson(new Request(request));
            //2.3. Gửi Request
            writer.write(Json + "\n");
            writer.flush();
            System.out.println("Client đã gửi: " + Json);

            //3. Xác nhận Server đã nhận được request ( đọc giá trị xác nhận từ Server, không quan tâm giá trị gì)
            String status = reader.readLine();

            //4.Gửi dữ liệu lần 2 lên Server
            //4.1. Chuyển object sang json
            String jsonData = gson.toJson(dataIn);
            //4.2. Gửi json lên Server
            writer.write(jsonData + "\n");
            writer.flush();
            System.out.println("Dữ liệu đã gửi lên Server (2): "+ jsonData);

            //5. Nhận dữ liệu từ Server
            //5.1. Đọc dữ liệu ( đang ở dạng chuỗi json)
            String jsonIn = reader.readLine();
            //5.2. Chuyển về kiểu object
            //Type collectionType = new TypeToken<StreamSocket<In, Out>>(){}.getType();
            try{
                dataOut = gson.fromJson(jsonIn, OutClass);
                System.out.println("Dữ liệu nhận được từ Server: "+ jsonIn);
            }catch (Exception e){
                System.out.println("Lỗi chuyển giá trị json sang object");
            }
        } catch (IOException e) {
            System.out.println("Khởi tạo đọc, ghi socket thất bại!");
            throw new RuntimeException(e);
        }
        //6. Trả về dữ liệu theo kiểu object của class truyền vào
        return dataOut;
    }

    public static void XacNhanThongTinServer(Socket sc){
        BufferedReader reader = null; // doc du lieu tu server gửi về
        try {
            reader = new BufferedReader(new InputStreamReader(sc.getInputStream(), "UTF-8"));
            String  check = reader.readLine(); // đọc và lưu trữ dữ liệu luu về
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public <T> ObservableList SendRequestObser(String request, In dataIn, Class<ObservableList> outClass, Class<T> elementClass){
        // Khởi tạo các object
        ObservableList<T> dataOut = FXCollections.observableArrayList();
        Gson gson = new Gson();
        //1.Tạo kết nối socket đến Server
        Socket socket = ConnectServer.getConnect();
        //2. Gửi Request lên Server
        //2.1. Khởi tạo kết nối Stream
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); // đọc
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")); //gửi
            //2.2. Chuyển request ở dạng String sang json
            String Json = gson.toJson(new Request(request));
            //2.3. Gửi Request
            writer.write(Json + "\n");
            writer.flush();
            System.out.println("Client đã gửi: " + Json);

            //3. Xác nhận Server đã nhận được request ( đọc giá trị xác nhận từ Server, không quan tâm giá trị gì)
            String status = reader.readLine();

            //4.Gửi dữ liệu lần 2 lên Server
            //4.1. Chuyển object sang json
            String jsonData = gson.toJson(dataIn);
            //4.2. Gửi json lên Server
            writer.write(jsonData + "\n");
            writer.flush();
            System.out.println("Dữ liệu đã gửi lên Server (2): "+ jsonData);

            //5. Nhận dữ liệu từ Server
            //5.1. Đọc dữ liệu ( đang ở dạng chuỗi json)
            String jsonIn = reader.readLine();
            //5.2. Chuyển về kiểu object
            try{
                Type listType = TypeToken.getParameterized(ArrayList.class, elementClass).getType();
                List<T> list = gson.fromJson(jsonIn, listType);
                dataOut = FXCollections.observableArrayList(list);
                System.out.println("Dữ liệu nhận được từ Server: "+ jsonIn);
            }catch (Exception e){
                System.out.println("Lỗi chuyển giá trị json sang object");
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Khởi tạo đọc, ghi socket thất bại!");
            throw new RuntimeException(e);
        }
        //6. Trả về dữ liệu theo kiểu object của class truyền vào
        System.out.println(dataOut);
        return dataOut;
    }
}
