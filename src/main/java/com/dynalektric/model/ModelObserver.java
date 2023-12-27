package com.dynalektric.model;

public interface ModelObserver {
    public void update(String msg);
    public void update(String msg , Object data);
}
