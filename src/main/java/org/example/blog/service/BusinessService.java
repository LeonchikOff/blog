package org.example.blog.service;

import org.example.blog.entity.Category;

import java.util.Map;

public interface BusinessService {
    Map<Integer, Category> getMapWithCategories();
}
