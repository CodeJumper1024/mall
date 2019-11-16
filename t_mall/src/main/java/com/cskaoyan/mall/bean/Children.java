package com.cskaoyan.mall.bean;

import lombok.Data;

import java.util.List;

@Data
public class Children {
    int id;
    String name;
    int type;
    int code;
    List<Children> children;

    @Override
    public String toString() {
        return "Children{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code=" + code +
                ", children=" + children +
                '}';
    }
}
