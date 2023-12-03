package com.example.springwithfx.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoKhauDto {


    private long id;
    private String soNha;
    private String duong;
    private String phuong;
    private String quan;
    private String chuHo;

    public HoKhauDto(HoKhau hoKhau) {
        this.id = hoKhau.getId();
        this.soNha = hoKhau.getSoNha();
        this.duong = hoKhau.getDuong();
        this.phuong = hoKhau.getPhuong();
        this.quan = hoKhau.getQuan();
        this.chuHo = hoKhau.getChuHo().getCmnd();
    }
}
