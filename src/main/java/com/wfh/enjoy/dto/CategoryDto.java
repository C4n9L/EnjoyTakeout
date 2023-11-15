package com.wfh.enjoy.dto;

import com.wfh.enjoy.entity.Category;
import lombok.Data;

/**
 * @author wfh
 * @create 2023/4/17 - 15:57
 */
@Data
public class CategoryDto extends Category {

    private String shopName;

}
