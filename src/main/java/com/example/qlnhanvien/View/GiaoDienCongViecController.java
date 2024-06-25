package com.example.qlnhanvien.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GiaoDienCongViecController implements Initializable {
    @FXML
    private AnchorPane Acp_nhanvien_ShowFile;

    @FXML
    private Button bt_bihuy;

    @FXML
    private Button bt_chuathuchien;

    @FXML
    private Button bt_dangthuchien;

    @FXML
    private Button bt_hoantat;

    @FXML
    private Button bt_quaylai;

    @FXML
    private Button bt_tatca;

    @FXML
    private TableColumn<?, ?> colum_Hannop;

    @FXML
    private TableColumn<?, ?> colum_IDcongviec;

    @FXML
    private TableColumn<?, ?> colum_Trangthaithuchien;

    @FXML
    private TableColumn<?, ?> colum_congviec;

    @FXML
    private TableView<?> table_taca;

    @FXML
    private TextField tf_timkiem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Acp_nhanvien_ShowFile.getChildren().clear();
        AnchorPane anchorPane= null;
        try{
            anchorPane = FXMLLoader.load(getClass().getResource("GDCV_tatca.fxml"));

        } catch(IOException e){
            throw  new RuntimeException(e);
        }
        Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
        //  Thiết lập sự kiện cho button tất cả
        bt_tatca.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane= null;
                try{
                    anchorPane= FXMLLoader.load(getClass().getResource("GDCV_tatca.fxml"));
                } catch(IOException e){
                    throw  new RuntimeException(e);
                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        //Thiết lập sự kiện cho button đang thuc hiện
        bt_dangthuchien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane = null;
                try {
                    anchorPane = FXMLLoader.load(getClass().getResource("GDCV_Dangthuchien.fxml"));
                } catch(IOException e){
                    throw  new RuntimeException(e);
                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        //Thiết lập sự kiện cho button chưa thực hiện
        bt_chuathuchien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane= null;
                try{
                    anchorPane = FXMLLoader.load(getClass().getResource("GDCV_Chuathuchien.fxml"));
                } catch (IOException e){
                    throw  new RuntimeException(e);

                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        // Thiết lập sự kiện cho button hoàn tất
        bt_hoantat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane= null;
                try{
                    anchorPane =FXMLLoader.load(getClass().getResource("GDCV_Hoantat.fxml"));
                } catch(IOException e){
                    throw  new RuntimeException(e);
                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        // thiết lập sự kiện cho button bị hủy
        bt_bihuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane= null;
                try{
                    anchorPane = FXMLLoader.load(getClass().getResource("GDCV_Bihuy.fxml"));
                } catch( IOException e){
                    throw  new RuntimeException(e);

                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        bt_quaylai.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // chuyển về trang giao diện nhân viên
                try {
                    DBUtils.changeScene(actionEvent, "GiaoDienNhanVien.fxml", "Trang nhân viên", 500, 800);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
