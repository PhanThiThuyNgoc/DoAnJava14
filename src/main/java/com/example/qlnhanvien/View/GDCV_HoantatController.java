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
import java.util.ResourceBundle;

public class GDCV_HoantatController implements Initializable {
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
        table_taca.setItems(null);
        //ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "1");
        ObservableList<CongViec> listCV = new StreamSocket<CongViecNV,ObservableList<CongViec> >().SendRequestObser("/getListCV",new CongViecNV(GetInfo.getEmail(), "1"),ObservableList.class, CongViec.class);
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Trangthaithuchien.setCellValueFactory(new PropertyValueFactory<>("Trangthaithuchien"));
        table_taca.setItems(listCV);
    }
}
