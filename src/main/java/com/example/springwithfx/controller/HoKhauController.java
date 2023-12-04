package com.example.springwithfx.controller;

import com.example.springwithfx.config.StageManager;
import com.example.springwithfx.model.HoKhau;
import com.example.springwithfx.model.HoKhauDto;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.model.NhanKhauHolder;
import com.example.springwithfx.service.HoKhauService;
import com.example.springwithfx.views.SuccesDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope("prototype")

@Slf4j
public class HoKhauController {


    private Stage stage;
    private Scene scene;
    private NhanKhauHolder nhanKhauHolder = NhanKhauHolder.getInstance();
    private final HoKhauService hoKhauService;
    private final StageManager stageManager;

    public HoKhauController(HoKhauService hoKhauService, StageManager stageManager) {
        this.hoKhauService = hoKhauService;
        this.stageManager = stageManager;
    }



    @FXML
    public TextField fullNameField;
    public DatePicker dobPicker;
    public TextField placeOfBirthField;
    public TextField hometownField;
    public TextField ethnicityField;
    public TextField jobField;
    public TextField idField;
    public RadioButton maleButton;
    public TextField houseNumberField;
    public TextField streetNameField;
    public TextField wardField;
    public TextField districtField;
    public TableView<HoKhauDto> addressTable;



    public void commonSwitch(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        stageManager.switchScene(stage, scene, fxmlLoader, event);
    }



    public void switchHoKhau(ActionEvent event){
        try {
            this.getNhanKhau();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/form_hokhau.fxml"));


            commonSwitch(fxmlLoader, event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGender(){
        if(maleButton.isSelected()){
            return "Nam";
        }
        else{
            return "Nữ";
        }
    }
    public void getNhanKhau(){
        NhanKhau nhanKhau = new NhanKhau();
        nhanKhau.setCmnd(idField.getText());
        nhanKhau.setHoTen(fullNameField.getText());
        nhanKhau.setNgaySinh(java.sql.Date.valueOf(dobPicker.getValue()));
        nhanKhau.setNoiSinh(placeOfBirthField.getText());
        nhanKhau.setNguyenQuan(hometownField.getText());
        nhanKhau.setNgheNghiep(jobField.getText());
        nhanKhau.setDanToc(ethnicityField.getText());
        nhanKhau.setGioiTinh(getGender());
        nhanKhau.setQuanHeVoiChuHo("Chủ hộ");

        nhanKhauHolder.setNhanKhau(nhanKhau);
    }

    public HoKhau getHoKhau(){
        HoKhau hoKhau = new HoKhau();
        hoKhau.setSoNha(houseNumberField.getText());
        hoKhau.setDuong(streetNameField.getText());
        hoKhau.setPhuong(wardField.getText());
        hoKhau.setQuan(districtField.getText());

        return hoKhau;
    }

    public void Them() throws IOException {
        NhanKhau nhanKhau = nhanKhauHolder.getNhanKhau();
        HoKhau hoKhau = getHoKhau();
        nhanKhau.setHoKhau(hoKhau);
        hoKhau.setChuHo(nhanKhau);
        hoKhauService.saveHoKhau(hoKhau);
        log.info("Them thanh cong");
        SuccesDialog succesDialog = new SuccesDialog();

   }




}
