package com.wfh.enjoy.dto;

import com.wfh.enjoy.entity.Dish;
import com.wfh.enjoy.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wfh
 * @create 2023/4/5 - 20:12
 */
@Data
public class DishDto extends Dish {
    //菜品对应的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String shopName;

    private String categoryName;

    private Integer copies;
}
