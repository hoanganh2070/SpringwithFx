package com.example.springwithfx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
public class NhanKhau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cmnd;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String soDienThoai;

    @ManyToOne
    @JoinColumn(name = "hoKhau_id")
    private HoKhau hoKhau;

    @OneToOne(mappedBy = "chuHo")
    private HoKhau chuHoKhau;


}
