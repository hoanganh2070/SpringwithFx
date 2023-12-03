package com.example.springwithfx.model;


import jakarta.persistence.*;
import javafx.scene.control.Button;
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
    private String soNha;
    private String duong;
    private String phuong;
    private String quan;

    @OneToMany(mappedBy = "hoKhau",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<NhanKhau> thanhVien;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "chuHo_id")
    private NhanKhau chuHo;
}
