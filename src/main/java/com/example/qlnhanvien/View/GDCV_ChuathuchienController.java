package com.example.qlnhanvien.View;
import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Controller.ConnectDB;
import com.example.qlnhanvien.Controller.DBUtils;
import com.example.qlnhanvien.Controller.Get.GetInfo;
import com.example.qlnhanvien.Model.CongViec;
import com.example.qlnhanvien.Model.ObjectGson.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GDCV_ChuathuchienController implements Initializable {

    CongViec selectedCongViec = null;
    private String IdCongViec = null;

    @FXML
    private Button bt_xacnhan;
    @FXML
    private Label lb_idCV;
    @FXML
    private TextField tf_CongViec;

    @FXML
    private TableColumn<CongViec, String> colum_Hannop;

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load giao diện dữ liệu
        loadDataToTable();

        // lấy thông tin người dùng chọn
        table_taca.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_taca.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                selectedCongViec = table_taca.getSelectionModel().getSelectedItem();
                IdCongViec = selectedCongViec.getIDCongviec();
                lb_idCV.setText(IdCongViec);
                tf_CongViec.setText(selectedCongViec.getTencongviec());
            }
        });

        bt_xacnhan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // gửi yêu cầu cập nhật dữ liệu lên server
                Boolean check = new StreamSocket<ID, Boolean>().SendRequest("/ChuyenTrangThaiCongViec", new ID(IdCongViec), Boolean.class);
                if (check) {
                    DBUtils.printAlertMsg("Thông báo", "Chuyển trạng thái công việc thành công!");
                    loadDataToTable();
                } else {
                    DBUtils.printAlertMsg("Thông báo", "Chuyển trạng thái công việc thất bại!");
                }
            }
        });

    }
    public void loadDataToTable(){
        //ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "0");
        ObservableList<CongViec> listCV = new StreamSocket<CongViecNV,ObservableList<CongViec> >().SendRequestObser("/getListCV",new CongViecNV(GetInfo.getEmail(), "0"),ObservableList.class, CongViec.class);
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Hannop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
        table_taca.setItems(listCV);
    }
}
