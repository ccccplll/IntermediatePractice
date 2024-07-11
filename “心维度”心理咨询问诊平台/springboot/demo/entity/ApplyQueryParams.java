package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ApplyQueryParams {
    private String name;
    private String astate;
    private Integer pageNum;
    private Integer pageSize;
    private Integer id;
    private Integer cid;
    private Date date;

}
//		name: this.pageParam.name,
//                a_state: this.pageParam.state,
//                pageNum: this.pageParam.pageNum,
//                pageSize: this.pageParam.pageSize,
//                id: this.pageParam.id,
//                cid: this.pageParam.cid,