package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.DAO.CongViecDAO;
import com.example.qlnhanvien.Controller.Get.GetInfo;
import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.CongViec;
import com.example.qlnhanvien.Model.ObjectGson.CongViecNV;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GDCV_tatcaController  implements Initializable {

    @FXML
    private TableColumn<CongViec, String> colum_Hannop;

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_Trangthaithuchien;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<CongViec> listCongviec = new StreamSocket<CongViecNV,ObservableList<CongViec> >().SendRequestObser("/getListCV",new CongViecNV(GetInfo.getEmail(), null),ObservableList.class, CongViec.class);

//        ObservableList<CongViec> listCongviec = null;
//        try {
//            // lấy danh sách các công việc
//            listCongviec = CongViecDAO.getDanhSachCongViec(GetInfo.getEmail());
//        } catch (Exception e) {
//            System.out.println("LoadLayDuLieuLoi");
//            throw new RuntimeException(e);
//        }
        //Set giá trị chp từng colum
        try {
            colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
            colum_IDcongviec.setCellValueFactory(new  PropertyValueFactory<>("IDCongviec"));
            colum_Hannop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
            colum_Trangthaithuchien.setCellValueFactory(new PropertyValueFactory<>("Trangthaithuchien"));
            table_taca.setItems(listCongviec);
        } catch (Exception e) {
            System.out.println("lỗi load lên giao diện");
            throw new RuntimeException(e);
        }

    }
}
