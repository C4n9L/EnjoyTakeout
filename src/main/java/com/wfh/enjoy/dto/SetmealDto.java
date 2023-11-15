package com.wfh.enjoy.dto;


import com.wfh.enjoy.entity.Setmeal;
import com.wfh.enjoy.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String shopName;

    private String categoryName;
}
