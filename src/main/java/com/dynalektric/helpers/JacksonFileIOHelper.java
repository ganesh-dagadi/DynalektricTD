package com.dynalektric.helpers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JacksonFileIOHelper implements FileIOHelper{
    private final static Logger LOGGER = LogManager.getLogger(JacksonFileIOHelper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void writeData(File file , Object obj){
        try{
            objectMapper.writeValue(file , obj);
        } catch (IOException e) {
            LOGGER.error(e.getMessage() , e);
        }
    }

    @Override
    public Object readData(File file , Class<?> target) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }
            return objectMapper.readValue(strBuilder.toString() , target);
        }catch(Exception e){
            LOGGER.error(e.getMessage() , e);
        }
        return null;
    }
}
