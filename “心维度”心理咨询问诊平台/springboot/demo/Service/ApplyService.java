package com.example.demo.Service;

import com.example.demo.entity.*;

import java.util.List;

public interface ApplyService {
    List<ApplyExt> queryApplys(ApplyQueryParams params);
    int getTotalApplys();

    void passApplyById(Integer id);
    void unpassApplyById(Integer id);

}
