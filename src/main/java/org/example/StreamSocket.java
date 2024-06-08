package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class StreamSocket {
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
}
