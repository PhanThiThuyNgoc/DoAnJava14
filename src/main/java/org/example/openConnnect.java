package org.example;

import com.google.gson.Gson;
import com.sun.net.httpserver.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class openConnnect {
    public  static  Gson gson = new Gson();
    public openConnnect(){
        try {
            ServerSocket serverSocket = new ServerSocket(4556);
            Socket socket = serverSocket.accept();// đợi kết nối từ clien
            Thread thread = new Thread(){
                public  void run (){


                }
            };

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
    public  void Controller( Socket socket){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                String data = reader.readLine(); // chứa giá trị ở dạng json
                 Request  request = gson.fromJson(data,Request.class); // chuyển từ từ json sang object của class
                switch ()
            } catch ( IOException e){
                throw  new RuntimeException(e);

            }

    }
}
