package com.cskaoyan.mall.bean;

import java.util.List;

public class Module {
    private String id;
    private String label;
    List<Block> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Block> getChildren() {
        return children;
    }

    public void setChildren(List<Block> children) {
        this.children = children;
    }
}
