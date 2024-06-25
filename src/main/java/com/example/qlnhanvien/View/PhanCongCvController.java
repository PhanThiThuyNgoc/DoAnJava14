package com.example.qlnhanvien.View;

import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.CongViec;
import com.example.qlnhanvien.Model.ObjectGson.ID;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhanCongCvController implements Initializable {
    public String IdCongViec;

    @FXML
    private ComboBox<String> cb_NhanVien;

    @FXML
    private Button sua;

    @FXML
    private TextArea ta_mota;

    @FXML
    private TableColumn<?, ?> table_Id;

    @FXML
    private TableColumn<?, ?> table_cv;

    @FXML
    private TableView<CongViec> table_tong;

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
    private TextArea tf_baoCao;

    @FXML
    private TextField tf_SanPham;

    @FXML
    private Label lb_ClearGiaoDien;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load thông tin
        loadDataToTable();
        // lấy danh sách nhân viên
        //ObservableList<String> listNV = NhanvienDAO.getDanhSachTenNhanVien();
        ObservableList<String> listNV = new StreamSocket<String, String>().SendRequestObser("/LayDanhSachNhanVien", null,ObservableList.class, String.class);
        cb_NhanVien.setItems(listNV);



        // lấy thông tin người dùng chọn
        table_tong.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_tong.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                CongViec selectedCongViec = table_tong.getSelectionModel().getSelectedItem();
                IdCongViec = selectedCongViec.getIDCongviec();
                // Lấy thông tin công việc tương ứng
                CongViec cv = new StreamSocket<ID,CongViec>().SendRequest("/GetInfoCongViecByID", new ID(IdCongViec), CongViec.class);
                //CongViec cv = CongViecDAO.getInfoCongViec(IdCongViec);
                // load thông tin lên giao diện
                tf_baoCao.setText(cv.getBaocaocongviec());
                tf_cv.setText(cv.getTencongviec());
                tf_Ngaybd.setText(String.valueOf(cv.getNgayBatDau()));
                tf_Ngayketthuc.setText(String.valueOf(cv.getHanNop()));
                tf_SanPham.setText(cv.getLinkNopsanpham());
                tf_Nguoithuchien.setText(cv.getNhanVien());

            }
        });

        lb_ClearGiaoDien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearGiaoDien();
            }
        });

        them.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //String tenNV, String CongViec, LocalDate HanNop
                CongViec cv = new CongViec();
                cv.setTencongviec(tf_cv.getText());
                cv.setNhanVien(cb_NhanVien.getValue());
                cv.setHanNop(tf_Ngayketthuc.getText());
                new StreamSocket<CongViec, Boolean>().SendRequest("/ThemCongViecByQuanLy",cv,Boolean.class );
                //CongViecDAO.addCongViec(cb_NhanVien.getValue(), tf_cv.getText(), LocalDate.parse(tf_Ngayketthuc.getText()));
                // thông báo
                DBUtils.printAlertMsg("Thông báo", "Phân công công việc thành công!");
                // làm sạch giao diện
                clearGiaoDien();
            }
        });

        sua.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tf_cv.getText().equals("")){
                    DBUtils.printAlertMsg("Thông báo", "Chưa chọn công việc!");
                }else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn có muốn lưu sự thay đổi dữ liệu không?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        String nhanVien = cb_NhanVien.getValue();
                        // Người dùng chọn OK (true)
                        //String IdCongViec,String tenNV, String CongViec, LocalDate HanNop, LocalDate NgayBatDau
                        if(cb_NhanVien.getValue() == null){
                            nhanVien = tf_Nguoithuchien.getText();
                        }
                        CongViec cv = new CongViec();
                        cv.setIDCongviec(IdCongViec);
                        cv.setNhanVien(nhanVien);
                        cv.setTencongviec(tf_cv.getText());
                        cv.setHanNop(tf_Ngayketthuc.getText());
                        cv.setNgayBatDau(tf_Ngaybd.getText());
                        new StreamSocket<CongViec, Boolean>().SendRequest("/UpdateCongViecByQuanLy", cv, Boolean.class);
                        //CongViecDAO.UpdateCongViec(IdCongViec,cb_NhanVien.getValue(), tf_cv.getText(), LocalDate.parse(tf_Ngayketthuc.getText()),  LocalDate.parse(tf_Ngaybd.getText()));
                        // thông báo
                        DBUtils.printAlertMsg("Thông báo", "Lưu sự thay đổi thành công!");
                    }
                    // làm sạch giao diện
                    clearGiaoDien();
                }

            }
        });


        xoa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có muốn lưu sự thay đổi dữ liệu không?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String nhanVien = cb_NhanVien.getValue();
                    // Người dùng chọn OK (true)
                    //String IdCongViec,String tenNV, String CongViec, LocalDate HanNop, LocalDate NgayBatDau
                    new StreamSocket<ID, Boolean>().SendRequest("/DeleteCongViecByQuanLy", new ID(IdCongViec), Boolean.class);
                    //CongViecDAO.DeleteCongViec(IdCongViec);
                    DBUtils.printAlertMsg("Thông báo", "Xoá công việc thành công!");
                    loadDataToTable();
                }

            }
        });
    }

    public void loadDataToTable(){
        table_tong.setItems(null);
        //ObservableList<CongViec> listCVtk= CongViecDAO.getDanhSachCongViec();
        ObservableList<CongViec> listCV = new StreamSocket<String, CongViec>().SendRequestObser("/LayDanhSachCongViec", null,ObservableList.class, CongViec.class);
        table_Id.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        table_cv.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        table_tong.setItems(listCV);
    }
    public void clearGiaoDien(){
        tf_baoCao.clear();
        tf_cv.clear();
        tf_Ngaybd.clear();
        tf_Ngayketthuc.clear();
        tf_SanPham.clear();
        tf_Nguoithuchien.clear();
        cb_NhanVien.setValue(null);
    }

}
