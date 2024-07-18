package com.example.demo.Service;

import com.example.demo.entity.PageContent;
import com.example.demo.entity.PageContentExt;

import java.util.List;

public interface PageContentService {
    List<PageContentExt> findById(int id);
}
