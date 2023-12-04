package com.example.springwithfx.service;

import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.repository.NhanKhauRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NhanKhauService {
    private NhanKhauRepository nhanKhauRepository;

    public NhanKhauService(NhanKhauRepository nhanKhauRepository) {
        this.nhanKhauRepository = nhanKhauRepository;
    }

    public void saveNhanKhau(NhanKhau nhanKhau) {
        nhanKhauRepository.save(nhanKhau);
    }

    public List<NhanKhau> getAllNhanKhau(){
        return nhanKhauRepository.findAll();
    }

    public void deleteNhanKhau(long id) {
        nhanKhauRepository.deleteById(id);
    }

    public NhanKhau getNhanKhau(long id){
        return nhanKhauRepository.findById(id).get();
    }

    public List<NhanKhau> searchHoTen(String text) {
        return nhanKhauRepository.findNhanKhausByHoTen(text);
    }
}
