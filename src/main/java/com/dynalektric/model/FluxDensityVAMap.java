package com.dynalektric.model;

import java.util.HashMap;
import java.util.Map;

public class FluxDensityVAMap {
    Map<Double , Double> VA_CM2Map = new HashMap<>();
    Map<Double , Double> VAPerKGMap = new HashMap<>();
    public FluxDensityVAMap(){
        VA_CM2Map.put(0.600, 0.639);
        VA_CM2Map.put(0.625, 0.639);
        VA_CM2Map.put(0.650, 0.639);
        VA_CM2Map.put(0.675, 0.639);
        VA_CM2Map.put(0.700, 0.639);
        VA_CM2Map.put(0.725, 0.639);
        VA_CM2Map.put(0.750, 0.639);
        VA_CM2Map.put(0.775, 0.639);
        VA_CM2Map.put(0.800, 0.639);
        VA_CM2Map.put(0.825, 0.639);
        VA_CM2Map.put(0.850, 0.639);
        VA_CM2Map.put(0.875, 0.639);
        VA_CM2Map.put(0.900, 0.639);
        VA_CM2Map.put(0.925, 0.639);
        VA_CM2Map.put(0.950, 0.639);
        VA_CM2Map.put(0.975, 0.639);
        VA_CM2Map.put(1.000, 0.639);
        VA_CM2Map.put(1.025, 0.639);
        VA_CM2Map.put(1.050, 0.639);
        VA_CM2Map.put(1.075, 0.639);
        VA_CM2Map.put(1.100, 0.639);
        VA_CM2Map.put(1.125, 0.639);
        VA_CM2Map.put(1.150, 0.639);
        VA_CM2Map.put(1.175, 0.639);
        VA_CM2Map.put(1.200, 0.639);
        VA_CM2Map.put(1.225, 0.639);
        VA_CM2Map.put(1.250, 0.639);
        VA_CM2Map.put(1.275, 0.853);
        VA_CM2Map.put(1.300, 0.853);
        VA_CM2Map.put(1.325, 1.095);
        VA_CM2Map.put(1.350, 1.095);
        VA_CM2Map.put(1.375, 1.095);
        VA_CM2Map.put(1.400, 1.370);
        VA_CM2Map.put(1.425, 1.370);
        VA_CM2Map.put(1.450, 1.660);
        VA_CM2Map.put(1.475, 1.660);
        VA_CM2Map.put(1.500, 1.980);
        VA_CM2Map.put(1.525, 1.980);
        VA_CM2Map.put(1.550, 2.340);
        VA_CM2Map.put(1.575, 2.340);
        VA_CM2Map.put(1.600, 2.740);
        VA_CM2Map.put(1.625, 2.740);
        VA_CM2Map.put(1.650, 3.190);
        VA_CM2Map.put(1.675, 3.190);
        VA_CM2Map.put(1.700, 3.720);
        VA_CM2Map.put(1.725, 3.720);
        VA_CM2Map.put(1.750, 4.37);

        VAPerKGMap.put(0.600, 0.650);
        VAPerKGMap.put(0.625, 0.650);
        VAPerKGMap.put(0.650, 0.650);
        VAPerKGMap.put(0.675, 0.650);
        VAPerKGMap.put(0.700, 0.650);
        VAPerKGMap.put(0.725, 0.650);
        VAPerKGMap.put(0.750, 0.650);
        VAPerKGMap.put(0.775, 0.650);
        VAPerKGMap.put(0.800, 0.650);
        VAPerKGMap.put(0.825, 0.650);
        VAPerKGMap.put(0.850, 0.650);
        VAPerKGMap.put(0.875, 0.650);
        VAPerKGMap.put(0.900, 0.650);
        VAPerKGMap.put(0.925, 0.650);
        VAPerKGMap.put(0.950, 0.650);
        VAPerKGMap.put(0.975, 0.650);
        VAPerKGMap.put(1.000, 0.650);
        VAPerKGMap.put(1.025, 0.650);
        VAPerKGMap.put(1.050, 0.650);
        VAPerKGMap.put(1.075, 0.650);
        VAPerKGMap.put(1.100, 0.650);
        VAPerKGMap.put(1.125, 0.650);
        VAPerKGMap.put(1.150, 0.650);
        VAPerKGMap.put(1.175, 0.650);
        VAPerKGMap.put(1.200, 0.650);
        VAPerKGMap.put(1.225, 0.650);
        VAPerKGMap.put(1.250, 0.650);
        VAPerKGMap.put(1.275, 0.650);
        VAPerKGMap.put(1.300, 0.700);
        VAPerKGMap.put(1.325, 0.700);
        VAPerKGMap.put(1.350, 0.760);
        VAPerKGMap.put(1.375, 0.760);
        VAPerKGMap.put(1.400, 0.820);
        VAPerKGMap.put(1.425, 0.820);
        VAPerKGMap.put(1.450, 0.900);
        VAPerKGMap.put(1.475, 0.900);
        VAPerKGMap.put(1.500, 1.030);
        VAPerKGMap.put(1.525, 1.030);
        VAPerKGMap.put(1.550, 1.200);
        VAPerKGMap.put(1.575, 1.200);
        VAPerKGMap.put(1.600, 1.450);
        VAPerKGMap.put(1.625, 1.450);
        VAPerKGMap.put(1.650, 1.850);
        VAPerKGMap.put(1.675, 1.850);
        VAPerKGMap.put(1.700, 2.500);
        VAPerKGMap.put(1.725, 2.500);
        VAPerKGMap.put(1.750, 4.000);
    }

    public Double getVA_CM2Value(Double fluxDensity){
        return VA_CM2Map.get(fluxDensity);
    }

    public Double getVAPerKgValue(Double fluxDensity){
        return VAPerKGMap.get(fluxDensity);
    }
}
