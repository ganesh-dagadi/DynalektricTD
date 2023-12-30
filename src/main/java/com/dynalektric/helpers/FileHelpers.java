package com.dynalektric.helpers;

import java.io.File;

public class FileHelpers {
    public static boolean deleteFile(File file){
        return file.delete();
    }

}
