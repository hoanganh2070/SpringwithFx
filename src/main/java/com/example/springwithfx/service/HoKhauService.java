package com.example.springwithfx.service;

import com.example.springwithfx.model.HoKhau;
import com.example.springwithfx.model.HoKhauDto;
import com.example.springwithfx.model.NhanKhau;
import com.example.springwithfx.repository.HoKhauRepository;
import com.example.springwithfx.repository.NhanKhauRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HoKhauService {


    private final HoKhauRepository hoKhauRepository ;
    private final NhanKhauRepository nhanKhauRepository;


    public HoKhauService(HoKhauRepository hoKhauRepository, NhanKhauRepository nhanKhauRepository) {
        this.hoKhauRepository = hoKhauRepository;
        this.nhanKhauRepository = nhanKhauRepository;
    }

    public void saveHoKhau(HoKhau hoKhau) {
        hoKhauRepository.save(hoKhau);
    }

    public HoKhau getHoKhau(long id){
        Optional<HoKhau> hoKhau = hoKhauRepository.findById(id);
        return hoKhau.orElse(null);
    }

    public List<HoKhauDto> findAll(){
        List<HoKhauDto> hoKhaus = new ArrayList<>();
        hoKhauRepository.findAll().forEach(hoKhau -> {
            HoKhauDto hoKhauDto = new HoKhauDto(hoKhau);
            hoKhaus.add(hoKhauDto);
        });
        return  hoKhaus;
    }

    public void deleteHoKhau(long id) {
        hoKhauRepository.deleteById(id);
    }

    public void UpdateHoKhau(long id, NhanKhau nhanKhau) {
        Optional<HoKhau> hoKhau = hoKhauRepository.findById(id);
           if(hoKhau.isPresent()){
                HoKhau hoKhau1 = hoKhau.get();
                nhanKhauRepository.delete(hoKhau1.getChuHo());
                hoKhau1.setChuHo(nhanKhau);
                nhanKhau.setHoKhau(hoKhau1);
                hoKhauRepository.save(hoKhau1);

            }
    }


}
