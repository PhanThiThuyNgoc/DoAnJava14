package com.example.qlnhanvien.View;


import com.example.qlnhanvien.Controller.DAO.MessDAO;
import com.example.qlnhanvien.Controller.DAO.NhanvienDAO;
import com.example.qlnhanvien.Controller.Get.GetInfo;
import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.NhanVien;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GiaoDienNVChatController implements Initializable {
    String TenNV = null;
    Socket socket = null;
    @FXML
    private Button bt_Send;

    @FXML
    private Label lb_TenNguoiNhan;

    @FXML
    private ListView<String> lv_DanhSachNguoiNhan;

    @FXML
    private ListView<String> lv_hienThiTinNhan;

    @FXML
    private TextArea tf_TinNhanMoi;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lấy thông tin nhân viên sử dụng
        //NhanVien nv = NhanvienDAO.getInfoUser(GetInfo.getEmail());
        NhanVien nv = new StreamSocket<String, NhanVien>().SendRequest("/getInfoUser", GetInfo.getEmail(), NhanVien.class);
        TenNV = nv.getHovaten();

        // Load thông tin nhân viên
        ObservableList<String> listNV = new StreamSocket<String, String>().SendRequestObser("/LayDanhSachNhanVien", null, ObservableList.class, String.class);

        try {
            lv_DanhSachNguoiNhan.getItems().addAll(listNV);
        } catch (Exception e) {
            System.out.println(listNV);
            System.out.println("Lỗi load dữ liệu");
            throw new RuntimeException(e);
        }

        //Khởi tạo kết nối
        try {
            socket = ConnectServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Tạo luồng đọc dữ liệu từ server với chuẩn UTF-8
        Thread readMess = new Thread(){
            @Override
            public void run(){
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    while (true){
                        String mess = reader.readLine();
                        Platform.runLater(() -> lv_hienThiTinNhan.getItems().add(mess));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        readMess.start();


        //Gửi dữ liệu lên server với chuẩn UTF-8
        try {
            BufferedWriter writerServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            // set Even cho button gửi
            bt_Send.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String mess = TenNV + ": " + tf_TinNhanMoi.getText();
                    try {
                        System.out.println("Client đã gửi :"+ mess);
                        writerServer.write(mess + "\n");
                        writerServer.flush();
                        tf_TinNhanMoi.clear();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // load tin nhắn lên giao diện
//        List<String> data = null;
////        try {
////            data = MessDAO.getMessenger("groupAdmin");
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////        lv_hienThiTinNhan.getItems().addAll(data);


    }

    public static Socket ConnectServer() throws IOException {
        String IP = "localhost";
        int Port = 7749;
        Socket socket = new Socket(IP, Port);
        return socket;
    }


}

