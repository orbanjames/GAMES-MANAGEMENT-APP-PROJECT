package com.jamesorban.mygamestore.utils.converters;

import com.jamesorban.mygamestore.database.models.Category;
import com.jamesorban.mygamestore.modelFx.CategoryFx;

public class ConverterCategory {

    public static Category covertToCategory(CategoryFx categoryFx){
        Category category = new Category();
        category.setId(categoryFx.getId());
        category.setName(categoryFx.getName());
        return category;
    }

    public static CategoryFx covertToCategoryFx(Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }
}
