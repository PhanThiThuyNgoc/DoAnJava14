package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.Controller;
import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.CongViec;
import com.example.qlnhanvien.Model.ObjectGson.ID;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TienDoCvController implements Initializable {
    @FXML
    private AnchorPane acp_TienDo;

    @FXML
    private Label lb_PhanTramCV;

    @FXML
    private Label lb_ngaybatdau;

    @FXML
    private Label lb_ngayketthuc;

    @FXML
    private Label lb_nguoithuchien;

    @FXML
    private Label lb_tenCongViec;

    @FXML
    private TableView<CongViec> table_congviec;

    @FXML
    private TableColumn<CongViec, String> tableclum_congviec;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load thông tin
        loadDataToTable();

        // thay đổi giá trị tổng quan
        // số lượng công việc
        int congviec = Controller.getCoutCongViec();
        // tiến độ công vệc đã hoàn thành
        int congviechoanthanh = Controller.getCoutCongviechoanthanh();
        int phantram = (congviechoanthanh*100)/congviec;
        // tỉ lệ độ dài
        int tiledodai = phantram*4;
        acp_TienDo.setPrefWidth(tiledodai);
        lb_PhanTramCV.setText(phantram + "%");


        // lấy thông tin người dùng chọn
        table_congviec.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_congviec.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                CongViec selectedCongViec = table_congviec.getSelectionModel().getSelectedItem();
                String IdCongViec = selectedCongViec.getIDCongviec();
                // Lấy thông tin công việc tương ứng
                CongViec cv = new StreamSocket<ID, CongViec>().SendRequest("/GetInfoCongViecByID", new ID(IdCongViec), CongViec.class);
                //CongViec cv = CongViecDAO.getInfoCongViec(IdCongViec);
                // load thông tin lên giao diện

                lb_tenCongViec.setText(cv.getTencongviec());
                lb_ngaybatdau.setText(String.valueOf(cv.getNgayBatDau()));
                lb_ngayketthuc.setText(String.valueOf(cv.getHanNop()));
                lb_nguoithuchien.setText(cv.getNhanVien());
                lb_nguoithuchien.setText(cv.getTrangthaithuchien());
            }
        });

    }
    public void loadDataToTable(){
        table_congviec.setItems(null);
        //ObservableList<CongViec> listCV= CongViecDAO.getDanhSachCongViec();
        ObservableList<CongViec> listCV = new StreamSocket<String, CongViec>().SendRequestObser("/LayDanhSachCongViec", null, ObservableList.class, CongViec.class);
        tableclum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        table_congviec.setItems(listCV);
    }
}
