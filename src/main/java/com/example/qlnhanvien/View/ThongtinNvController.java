package com.example.qlnhanvien.View;


import com.example.qlnhanvien.Controller.StreamSocket;
import com.example.qlnhanvien.Model.NhanVien;
import com.example.qlnhanvien.Model.ObjectGson.ID;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongtinNvController implements Initializable {
    String IDNhanVien;

    @FXML
    private TextField chucvu;

    @FXML
    private TextField tf_emailnhanvienmoi;

    @FXML
    private TableColumn<NhanVien, String> cot_Hoten;

    @FXML
    private TableColumn<NhanVien, String> cot_ID;

    @FXML
    private TextField diachi;

    @FXML
    private TextField gioitinh;

    @FXML
    private TextField gmail;

    @FXML
    private TextField hoten;

    @FXML
    private TextField id;

    @FXML
    private TextField ngay_thamgia;

    @FXML
    private TextField ngaysinh;

    @FXML
    private TextField so_dienthoai;

    @FXML
    private TableView<NhanVien> table_Thongtin;

    @FXML
    private Button them;

    @FXML
    private Button xoa;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // load thông tin lên table
        loadDataToTable();
        // lấy thông tin người dùng chọn
        table_Thongtin.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_Thongtin.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                NhanVien selectedNhanVien = table_Thongtin.getSelectionModel().getSelectedItem();
                IDNhanVien = selectedNhanVien.getIDNhanvien();
                //NhanVien nv = NhanvienDAO.getInfoUserByID(IDNhanVien);
                NhanVien nv = new StreamSocket<ID,NhanVien >().SendRequest("/laythongtinnhanvienByID",new ID(IDNhanVien),NhanVien.class);
                // lấy thông tin của nhân viên
                id.setText(nv.getIDNhanvien());
                hoten.setText(nv.getHovaten());
                gioitinh.setText(nv.getGioitinh());
                ngaysinh.setText(String.valueOf(nv.getNgaysinh()));
                chucvu.setText(nv.getChucvu());
                diachi.setText(nv.getDiachi());
                so_dienthoai.setText(nv.getSodienthoai());
                gmail.setText(nv.getGmail());
                ngay_thamgia.setText(String.valueOf(nv.getNgaybatdaulamviec()));
            }
        });

        them.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tf_emailnhanvienmoi.getText().equals("")){
                    //NhanvienDAO.themnhanvien(tf_emailnhanvienmoi.getText());
                    new StreamSocket<String, Boolean>().SendRequest("/ThemNhanVienMoi", tf_emailnhanvienmoi.getText(), Boolean.class);
                    DBUtils.printAlertMsg("Thông Báo ", "Thêm nhân viên thành công ");
                    loadDataToTable();
                }
            }
        });

        xoa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(IDNhanVien != null){
                    //NhanvienDAO.xoanhanvien(IDNhanVien);
                    new StreamSocket<String, Boolean>().SendRequest("/XoaNhanVien", IDNhanVien, Boolean.class);
                    DBUtils.printAlertMsg("Thông Báo ", "Xoá nhân viên thành công ");
                    loadDataToTable();
                }else{
                    DBUtils.printAlertMsg("Thông Báo ", "Vui lòng chọn nhân viên");
                }


            }
        });


    }
    public void loadDataToTable(){
        table_Thongtin.setItems(null);
        //ObservableList<NhanVien> listNV= NhanvienDAO.getDanhSachNhanVien();
        ObservableList<NhanVien> listNV = new StreamSocket<String, NhanVien>().SendRequestObser("/LayDanhSachNhanVienFullInfo", null, ObservableList.class, NhanVien.class);
        cot_ID.setCellValueFactory(new PropertyValueFactory<>("IDNhanvien"));
        cot_Hoten.setCellValueFactory(new PropertyValueFactory<>("Hovaten"));
        table_Thongtin.setItems(listNV);
    }
}
