package com.dynalektric.model;

import com.dynalektric.constants.SystemConstants;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ModelUnitTest {
    @Test
    public void shouldCreateANewFileGeneralJSONOnModelLoad(){
        File generalFile = new File(SystemConstants.GENERAL_FILE);
        generalFile.delete();
        Model model = Model.getSingleton();
        model.initModel();
        model.dissolveModel();
        assertTrue(generalFile.exists());
    }
}
