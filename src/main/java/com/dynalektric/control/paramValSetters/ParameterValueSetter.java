package com.dynalektric.control.paramValSetters;

import com.dynalektric.model.repositories.project.OutputData;

//Used to set the value of a property in the middle of a calculation.
// Eg : If one value is calculated and the user changes it with a Spinner, then we have prioritize the spinner's value
public interface ParameterValueSetter {
    public void setLatestValue(String str);

    void setLatestValue(Number number);

    void setRequiredValueIntoOutput(OutputData outputData);
}
