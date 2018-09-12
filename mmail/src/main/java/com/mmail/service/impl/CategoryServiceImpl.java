package com.mmail.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmail.common.ServerResponse;
import com.mmail.dao.CategoryMapper;
import com.mmail.pojo.Category;
import com.mmail.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 13:02 2018/9/10
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName,Integer parentId){
        if (parentId == null || StringUtils.isBlank(categoryName)){
            ServerResponse.createByErrorMessage("添加分类的参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);

        if (rowCount > 0){
            return ServerResponse.createBySuccess("添加品类成功");
        }

        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse updateCategoryName(String categoryName, Integer categoryId){

        if (categoryId == null || StringUtils.isBlank(categoryName)){
            ServerResponse.createByErrorMessage("更新分类的参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setId(categoryId);

        //updateByPrimaryKeySelective 选择性更新，为什么要shetId?因为选择性更新是根据主键id去更新的。这样的话就会更新这个id对应的name
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0){
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParalleCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){ //categoryList是空的情况下
            //如果是空集合就打log
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    //拿到这个categoryId之后要查找它的子节点，然后再判断子节点下面还有子节点，最后把他们再放在一起
    //递归查询本节点的id及子节点的id
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null){
            for (Category categoryItem:categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        //拿到子节点的所有id
        return ServerResponse.createBySuccess(categoryIdList);
    }

    //递归算法，算出子节点。
    //把这个参数再当做返回值返回给这个方法本身，然后拿这个方法本身的返回值再调用这个方法，当成这个方法的一个参数
    private Set<Category>findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null){
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出的条件
        //退出条件，查找它的子节点是不是空的。如果是空的话就退出递归算法
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        //注意：mybatis集合，如果没有查到为空的情况下，不会返回一个null对象。所以需要for循环遍历一下
        for (Category categoryItem:categoryList){
            //这里传传子节点categoryItem下的id
            findChildCategory(categorySet,categoryItem.getId()); //自己调用自己
        }
        return categorySet;
    }
}
