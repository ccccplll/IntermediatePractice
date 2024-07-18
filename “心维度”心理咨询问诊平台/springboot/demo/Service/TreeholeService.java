package com.example.demo.Service;

import com.example.demo.entity.Treehole;
import com.example.demo.entity.TreeholeQueryParams;
import com.example.demo.entity.UserLoginMessage;
import com.example.demo.entity.UserLoginMessageQueryParams;
import com.example.demo.mapper.TreeholeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeholeService {
    @Autowired
    private TreeholeMapper treeholeMapper;

    public List<Treehole> queryTreeholes(TreeholeQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return treeholeMapper.queryTreeholes(
                offset,
                params.getPageSize(),
                params.getState()
//                params.getUname(),
//                params.getCname()

        );
    }

    public int getTotalTreeholes() {
        return treeholeMapper.getTotalTreeholes();
    }
}
