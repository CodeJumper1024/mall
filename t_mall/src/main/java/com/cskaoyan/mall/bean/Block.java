package com.cskaoyan.mall.bean;

import java.util.List;

public class Block {
    private String id;
    private String label;
    List<Action> children;

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

    public List<Action> getChildren() {
        return children;
    }

    public void setChildren(List<Action> children) {
        this.children = children;
    }
}
