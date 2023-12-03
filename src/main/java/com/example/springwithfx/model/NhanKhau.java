package com.example.springwithfx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Getter
@Setter
public class NhanKhau {

    @Id
    private String cmnd;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String ngheNghiep;
    @UpdateTimestamp
    private Date ngayCap;
    private String noiCap;
    private String quanHeVoiChuHo;

    @ManyToOne
    @JoinTable(
            name = "nhankhau_hokhau",
            joinColumns = @JoinColumn(name = "nhankhau_id"),
            inverseJoinColumns = @JoinColumn(name = "hokhau_id"))
    private HoKhau hoKhau;

    @OneToOne (mappedBy = "chuHo")
    private HoKhau chuHoKhau;


}
