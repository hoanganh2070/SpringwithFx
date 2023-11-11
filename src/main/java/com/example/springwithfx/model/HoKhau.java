package com.example.springwithfx.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class HoKhau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String diaChi;
    private int soThanhVien;

    @OneToMany(mappedBy = "hoKhau")
    private Set<NhanKhau> thanhVien;

    @OneToOne
    @JoinColumn(name = "chuHo_id")
    private NhanKhau chuHo;
}
