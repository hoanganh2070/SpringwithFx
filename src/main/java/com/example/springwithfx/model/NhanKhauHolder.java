package com.example.springwithfx.model;

import lombok.Data;

@Data
public class NhanKhauHolder {

    private NhanKhau nhanKhau;

    private static final NhanKhauHolder INSTANCE = new NhanKhauHolder();

    public static NhanKhauHolder getInstance() {
        return INSTANCE;
    }

}
