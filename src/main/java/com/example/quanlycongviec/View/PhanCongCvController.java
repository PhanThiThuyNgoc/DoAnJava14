package com.example.quanlycongviec.View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class PhanCongCvController implements Initializable {
    @FXML
    private Button sua;

    @FXML
    private TextArea ta_mota;

    @FXML
    private TableColumn<?, ?> table_Id;

    @FXML
    private TableColumn<?, ?> table_cv;

    @FXML
    private TextField tf_Ngaybd;

    @FXML
    private TextField tf_Ngayketthuc;

    @FXML
    private TextField tf_Nguoithuchien;

    @FXML
    private TextField tf_cv;

    @FXML
    private Button them;

    @FXML
    private Button xoa;

    @FXML
    private TableView<?> table_tong;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
