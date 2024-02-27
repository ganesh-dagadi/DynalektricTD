package com.dynalektric.control.paramValSetters;

import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.OutputData;

public class CoreDValueSetter implements ParameterValueSetter{
    Model model = Model.getSingleton();

    @Override
    public void setLatestValue(String str) {
        throw new RuntimeException("Core D cant be a string");
    }

    @Override
    public void setLatestValue(Number number){
        model.getMidCalculationUpdatedCache().setCoreD((Double) number);

    }

    @Override
    public void setRequiredValueIntoOutput(OutputData outputData) {
        Double returnedCoreD = model.getMidCalculationUpdatedCache().getCoreD();
        if(returnedCoreD != null){
            outputData.CORE_D = returnedCoreD;
        }
    }
}
