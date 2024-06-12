package com.example.quanlycongviec.Controller.ConnectSV;

import java.io.IOException;
import java.net.Socket;

public class ConnectServer {
    private static int PORT = 4556;
    private static String IP = "localhost";
    public static Socket getConnect() {
        try {
            Socket socket = new Socket(IP,PORT);
            System.out.println("Connect to " + IP + ':' + PORT);
            return socket;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
