package com.dynalektric.model;

public interface ModelListener {
    public void update(String msg);
    public void update(String msg , Object data);
}
