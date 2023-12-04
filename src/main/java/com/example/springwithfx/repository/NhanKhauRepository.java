package com.example.springwithfx.repository;

import com.example.springwithfx.model.NhanKhau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanKhauRepository  extends JpaRepository<NhanKhau, Long> {

    public List<NhanKhau> findNhanKhausByHoTen(String hoTen);

}
