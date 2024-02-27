package com.dynalektric.model;

public class MidCalculationUpdatedCache {
    private static MidCalculationUpdatedCache updatedCache;
    private MidCalculationUpdatedCache(){};
    public static MidCalculationUpdatedCache getSingleton(){
        if(updatedCache == null){
            updatedCache = new MidCalculationUpdatedCache();
        }
        return updatedCache;
    }
    Double coreD;
    public void setCoreD(Double coreD){
        this.coreD = coreD;
    }
    public Double getCoreD(){
        // Clear cache once read
        Double toReturn = this.coreD;
//        this.coreD = null;
        return toReturn;
    }
}
