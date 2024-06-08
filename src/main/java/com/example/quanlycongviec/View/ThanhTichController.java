package com.example.quanlycongviec.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ThanhTichController implements Initializable {

    @FXML
    private TableColumn<?, ?> colum_nhanvien;

    @FXML
    private TableColumn<?, ?> colum_thanhtich;

    @FXML
    private TableView<?> table_thanhtich;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
