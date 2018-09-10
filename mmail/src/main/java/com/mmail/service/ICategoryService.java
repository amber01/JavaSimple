package com.mmail.service;

import com.mmail.common.ServerResponse;
import com.mmail.pojo.Category;

import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 13:02 2018/9/10
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(String categoryName, Integer categoryId);
    ServerResponse <List<Category>>getChildrenParalleCategory (Integer categoryId);
    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
