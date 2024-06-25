package com.example.qlnhanvien.Controller.ConnectSV;

import java.io.IOException;
import java.net.Socket;

public class ConnectServer {
    public static Socket  getConnect(){
        try {
            Socket socket = new Socket("localhost", 4556);
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
