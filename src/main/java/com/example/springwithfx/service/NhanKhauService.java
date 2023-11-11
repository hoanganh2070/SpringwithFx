package com.example.springwithfx.service;

import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.repository.NhanKhauRepository;
import org.springframework.stereotype.Service;


@Service
public class NhanKhauService {
    private NhanKhauRepository nhanKhauRepository;

    public NhanKhauService(NhanKhauRepository nhanKhauRepository) {
        this.nhanKhauRepository = nhanKhauRepository;
    }

    public void saveNhauKhau(NhanKhau nhanKhau){
        nhanKhauRepository.save(nhanKhau);
    }

}
