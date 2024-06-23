package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.ConnectDB;
import com.example.qlnhanvien.Controller.DAO.CongViecDAO;
import com.example.qlnhanvien.Controller.DBUtils;
import com.example.qlnhanvien.Controller.Get.GetInfo;
import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.CongViec;
import com.example.qlnhanvien.Model.ObjectGson.CongViecNV;
import com.example.qlnhanvien.Model.ObjectGson.ID;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GDCV_DangthuchienController implements Initializable {
    CongViec selectedCongViec = null;
    @FXML
    private Label lb_idCongViec;

    @FXML
    private TextArea tf_baoCao;

    @FXML
    private TextField tf_linkSP;

    @FXML
    private TableColumn<CongViec, String> colum_Hannop;

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;

    @FXML
    private Button bt_nopcongviec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // load dữ liệu lên table
        loadDataToTable();
        // lấy thông tin người dùng chọn

        table_taca.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_taca.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                selectedCongViec = table_taca.getSelectionModel().getSelectedItem();
                lb_idCongViec.setText(selectedCongViec.getIDCongviec());
            }
        });

        bt_nopcongviec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CongViec cv = new CongViec();
                cv.setIDCongviec(lb_idCongViec.getText());
                cv.setBaocaocongviec(tf_baoCao.getText());
                cv.setLinkNopsanpham(tf_linkSP.getText());
                // gửi yêu cầu cập nhật dữ liệu lên server
                Boolean check = new StreamSocket<CongViec, Boolean>().SendRequest("/HoanThanhCongViec", cv, Boolean.class);
                if(check){
                    DBUtils.printAlertMsg("Thông báo", "Nộp công việc thành công!");
                    loadDataToTable();
                    lb_idCongViec.setText(null);
                    tf_baoCao.clear();
                    tf_linkSP.clear();
                }else {
                    DBUtils.printAlertMsg("Thông báo", "Vui lòng nhập đầy đủ thông tin!");
                }
            }
        });


    }

    public void loadDataToTable(){
        //ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "2");
        ObservableList<CongViec> listCV = new StreamSocket<CongViecNV,ObservableList<CongViec> >().SendRequestObser("/getListCV",new CongViecNV(GetInfo.getEmail(), "2"),ObservableList.class, CongViec.class);
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Hannop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
        table_taca.setItems(listCV);
    }
}
