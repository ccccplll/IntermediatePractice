package com.example.demo.Service;

import com.example.demo.entity.*;

import java.util.List;

public interface CommentService {
    List<CommentExt> queryComments(CommentQueryParams params);

    int getTotalComments();
}
