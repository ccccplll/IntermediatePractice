package com.example.demo.Service;

import com.example.demo.entity.Consultant;
import com.example.demo.entity.ConsultantQueryParams;


import java.util.List;

public interface ConsultantService {
    List<Consultant> queryConsultants(ConsultantQueryParams params);
    int getTotalConsultants();

    void deleteConsultantById(Integer id);
}
