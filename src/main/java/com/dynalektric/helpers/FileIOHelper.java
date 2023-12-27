package com.dynalektric.helpers;

import java.io.File;

public interface FileIOHelper {
    public void writeData(File file , Object obj);
    public Object readData(File file , Class<Object> target);
}
