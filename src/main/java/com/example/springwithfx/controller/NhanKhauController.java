package com.example.springwithfx.controller;

import com.example.springwithfx.config.StageManager;
import com.example.springwithfx.model.HoKhau;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.service.HoKhauService;
import com.example.springwithfx.service.NhanKhauService;
import com.example.springwithfx.views.SuccesDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NhanKhauController {

    private Stage stage;
    private Scene scene;

    private final StageManager stageManager;
    private final NhanKhauService nhanKhauService;
    private final HoKhauService hoKhauService;
    private NhanKhau nhanKhau = new NhanKhau();



    @FXML
    public TextField fullNameField;
    public DatePicker dobPicker;
    public TextField placeOfBirthField;
    public TextField hometownField;
    public TextField ethnicityField;
    public TextField jobField;
    public TextField idField;
    public RadioButton maleRadioButton;
    public TextField addressField;
    public ComboBox<String> relationshipBox;



    public String getGender(){
        if(maleRadioButton.isSelected()){
            return "Nam";
        }
        else{
            return "Nữ";
        }
    }

    public NhanKhauController(StageManager stageManager, NhanKhauService nhanKhauService, HoKhauService hoKhauService) {
        this.stageManager = stageManager;
        this.nhanKhauService = nhanKhauService;
        this.hoKhauService = hoKhauService;
    }

    public void getNhanKhau(){
        nhanKhau.setCmnd(idField.getText());
        nhanKhau.setHoTen(fullNameField.getText());
        nhanKhau.setNgaySinh(java.sql.Date.valueOf(dobPicker.getValue()));
        nhanKhau.setNoiSinh(placeOfBirthField.getText());
        nhanKhau.setNguyenQuan(hometownField.getText());
        nhanKhau.setNgheNghiep(jobField.getText());
        nhanKhau.setDanToc(ethnicityField.getText());
        nhanKhau.setGioiTinh(getGender());
        nhanKhau.setQuanHeVoiChuHo(relationshipBox.getValue());
        HoKhau hoKhau = hoKhauService.getHoKhau(Long.parseLong(addressField.getText()));
        nhanKhau.setHoKhau(hoKhau);
    }

    public void Them() throws IOException {
        getNhanKhau();
        nhanKhauService.saveNhanKhau(nhanKhau);
        SuccesDialog succesDialog = new SuccesDialog();
    }
}
