package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class WxCart {
    Boolean isChecked;
    Integer[] productIds;
}
