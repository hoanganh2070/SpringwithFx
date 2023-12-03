package com.example.springwithfx.repository;

import com.example.springwithfx.model.HoKhau;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoKhauRepository extends CrudRepository<HoKhau, Long> {

}
