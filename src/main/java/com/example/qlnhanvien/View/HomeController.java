package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label congviec;

    @FXML
    private Label new_1;

    @FXML
    private Label new_2;

    @FXML
    private Label nhanvien;

    @FXML
    private Label nv_ngiviec;

    @FXML
    private Label tiendo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // cập nhập số lương nhân viên ddang làm việc
        String soluongnhanvien = Controller.getCoutNhanvien("1");
        System.out.println(soluongnhanvien);

        nhanvien.setText(soluongnhanvien);

        // cập nhập số lương nhân viên  đã nghĩ  việc
        String nhanviennghiviec = Controller.getCoutNhanvien("0");
        nv_ngiviec.setText(nhanviennghiviec);

        // cập nhập ố lượng công việc
        int congViec = Controller.getCoutCongViec();
        congviec.setText(String.valueOf(congViec));

        // cập nhập tiến độ công vệc
        int congviechoanthanh = Controller.getCoutCongviechoanthanh();
        int phantram = (congviechoanthanh*100)/congViec;
        tiendo.setText(phantram+"%");
    }
}
