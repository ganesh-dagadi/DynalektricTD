package com.dynalektric.control;

import com.dynalektric.constants.ParameterNameConstants;
import com.dynalektric.model.Model;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.lang.Math;
import java.util.Objects;

public class Calculations {
    Model model;

    private final HashMap<String, Double> inputData = new HashMap<>();

    private final HashMap<String, Double> outputData = new HashMap<>();

    public Calculations(){
        model = Model.getSingleton();
    }

    public void beginCalculations(){
        storeInputData();
    }

    //Write all the functions here
    public void storeInputData() {
        inputData.put(ParameterNameConstants.KVA, 277.2);
        // 1.0 = Dyn11
        inputData.put(ParameterNameConstants.CONNECTION, 1.0);
        inputData.put(ParameterNameConstants.K, 85.00);
        inputData.put(ParameterNameConstants.FREQUENCY,50.0);
        // 1.0 = AN-Cl-F
        inputData.put(ParameterNameConstants.COOLING,1.0);

        // 1.0 = Strip
        // 2.0 = Foil
        inputData.put(ParameterNameConstants.WINDINGTYPELV, 1.0);

        // 1.0 = Delta
        // 2.0 = Star
        inputData.put(ParameterNameConstants.CONNECTIONTYPELV, 1.0);

        // 1.0 = Strip
        // 2.0 = Foil
        inputData.put(ParameterNameConstants.WINDINGTYPEHV, 1.0);

        // 1.0 = Delta
        // 2.0 = Star
        inputData.put(ParameterNameConstants.CONNECTIONTYPEHV, 1.0);


        inputData.put(ParameterNameConstants.LINEVOLTSLV, 114.3);

        inputData.put(ParameterNameConstants.LINEVOLTSHV, 346.4);

        inputData.put(ParameterNameConstants.WIREBARELV1, 400.0);
        inputData.put(ParameterNameConstants.WIREBARELV2,2.30);
        inputData.put(ParameterNameConstants.WIREBAREHV1, 11.0);
        inputData.put(ParameterNameConstants.WIREBAREHV2,5.0);
    }

    public void vph_lv_star() {
        // Calculating VPH value for LV star Connection Type.
        double answer = inputData.get(ParameterNameConstants.LINEVOLTSLV) / Math.sqrt(3);
        outputData.put(ParameterNameConstants.VPH_LV, answer);
    }

    public void vph_hv_star() {
        // Calculating VPH value for HV star Connection Type.
        double answer = inputData.get(ParameterNameConstants.LINEVOLTSHV) / Math.sqrt(3);
        outputData.put(ParameterNameConstants.VPH_HV, answer);
    }

    public void iph_lv_star() {
        // Calculating IPH value for LV star Connection Type.
        double answer = (inputData.get(ParameterNameConstants.KVA) * 1000) / (Math.sqrt(3) * inputData.get(ParameterNameConstants.LINEVOLTSLV));
        outputData.put(ParameterNameConstants.IPH_LV,answer);
    }

    public void iph_hv_star() {
        // Calculating IPH value for HV star Connection Type.
        double answer = (inputData.get(ParameterNameConstants.KVA) * 1000) / (Math.sqrt(3) * inputData.get(ParameterNameConstants.LINEVOLTSHV));
        outputData.put(ParameterNameConstants.IPH_HV,answer);
    }

    public void vph_lv_delta() {
        // Calculating VPH value for LV delta Connection type.
        double answer= inputData.get(ParameterNameConstants.LINEVOLTSLV) / Math.sqrt(3);
        outputData.put(ParameterNameConstants.VPH_LV,answer);
    }

    public void vph_hv_delta() {
        //calculating VPH value for HV delta Connection type.
        double answer = inputData.get(ParameterNameConstants.LINEVOLTSHV) / Math.sqrt(3);
        outputData.put(ParameterNameConstants.VPH_HV,answer);

    }

    public void iph_lv_delta() {
        //calculating IPH value for LV delta Connection type.
        double answer = (inputData.get(ParameterNameConstants.KVA) * 1000) / (3 * outputData.get(ParameterNameConstants.VPH_LV));
        outputData.put(ParameterNameConstants.IPH_LV,answer);
    }

    public void iph_hv_delta() {
        // calculating  IPH value for HV delta Connection type.
        double answer= (inputData.get(ParameterNameConstants.KVA)*1000) / (3 * outputData.get(ParameterNameConstants.VPH_HV));
        outputData.put(ParameterNameConstants.IPH_HV,answer);

    }

    public void v_t() {
        // Calculating the value of v/t.
        double answer = 1.01 * Math.sqrt(inputData.get(ParameterNameConstants.KVA) / 3) * (inputData.get(ParameterNameConstants.K) / 100);
        outputData.put(ParameterNameConstants.V_T, answer);
    }
    public void rated_voltage_LV() {
        if(inputData.get(ParameterNameConstants.CONNECTIONTYPELV) == 1.0) {
            outputData.put(ParameterNameConstants.RATED_VOLTAGE_LV, inputData.get(ParameterNameConstants.LINEVOLTSLV) / Math.sqrt(3));
        }
        else {
            outputData.put(ParameterNameConstants.RATED_VOLTAGE_LV,inputData.get(ParameterNameConstants.LINEVOLTSLV));
        }
    }

    public void rated_voltage_HV() {
        if(inputData.get(ParameterNameConstants.CONNECTIONTYPEHV) == 1.0) {
            outputData.put(ParameterNameConstants.RATED_VOLTAGE_HV,inputData.get(ParameterNameConstants.LINEVOLTSHV) / Math.sqrt(3));
        }
        else {
           outputData.put(ParameterNameConstants.RATED_VOLTAGE_HV,inputData.get(ParameterNameConstants.LINEVOLTSHV));
        }
    }

    public void rated_current_LV() {
        if(inputData.get(ParameterNameConstants.CONNECTIONTYPELV) == 1.0) {
            outputData.put(ParameterNameConstants.RATED_CURRENT_LV, (inputData.get(ParameterNameConstants.KVA) * 1000) / 3 * outputData.get((ParameterNameConstants.RATED_VOLTAGE_LV)));
        }
        else {
            outputData.put((ParameterNameConstants.RATED_CURRENT_LV), inputData.get(ParameterNameConstants.KVA) * 1000 / Math.sqrt(3) * inputData.get(ParameterNameConstants.LINEVOLTSLV));
        }
    }

    public  void rated_current_HV() {
        if(inputData.get(ParameterNameConstants.CONNECTIONTYPEHV) == 1.0) {
            outputData.put(ParameterNameConstants.RATED_CURRENT_HV, (inputData.get(ParameterNameConstants.KVA) * 1000) / 3 * outputData.get((ParameterNameConstants.RATED_VOLTAGE_HV)));
        }
        else {
            outputData.put(ParameterNameConstants.RATED_CURRENT_HV, (inputData.get(ParameterNameConstants.KVA) * 1000) / (Math.sqrt(3) * inputData.get(ParameterNameConstants.LINEVOLTSHV)));
        }
    }

    public void cross_section_lv() {
        if(Objects.equals(inputData.get(ParameterNameConstants.WIREBARELV1), inputData.get(ParameterNameConstants.WIREBARELV2))) {
            double answer = inputData.get(ParameterNameConstants.WIREBARELV1) * inputData.get(ParameterNameConstants.WIREBARELV2) * 0.7854 * ParameterNameConstants.NO_IN_PARALLEL_RA_LV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_LV2;
            outputData.put(ParameterNameConstants.CROSS_SECTION_LV, answer);
        }
        else {
            if(inputData.get(ParameterNameConstants.WINDINGTYPELV) == 2.0) {
                double answer = inputData.get(ParameterNameConstants.WIREBARELV1) * inputData.get(ParameterNameConstants.WIREBARELV2) * ParameterNameConstants.NO_IN_PARALLEL_RA_LV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_LV2;
                outputData.put(ParameterNameConstants.CROSS_SECTION_LV, answer);
            }
            else {
                double answer = inputData.get(ParameterNameConstants.WIREBARELV1) * inputData.get(ParameterNameConstants.WIREBARELV2);
                // Strip = 1.0
                if(inputData.get(ParameterNameConstants.WINDINGTYPELV) == 1.0) {
                    answer -= 0.55;
                }
                // Foil = 2.0
                else {
                    answer -= 0.88;
                }
                answer = answer * ParameterNameConstants.NO_IN_PARALLEL_RA_LV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_LV2;
                outputData.put(ParameterNameConstants.CROSS_SECTION_LV, answer);
            }
        }
    }

    public void cross_section_hv() {
        if(Objects.equals(inputData.get(ParameterNameConstants.WIREBAREHV1), inputData.get(ParameterNameConstants.WIREBAREHV2))) {
            double answer = inputData.get(ParameterNameConstants.WIREBAREHV1) * inputData.get(ParameterNameConstants.WIREBAREHV2) * 0.7854 * ParameterNameConstants.NO_IN_PARALLEL_RA_HV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_HV2;
            outputData.put(ParameterNameConstants.CROSS_SECTION_HV, answer);
        }
        else {
            if(inputData.get(ParameterNameConstants.WINDINGTYPEHV) == 2.0) {
                double answer = inputData.get(ParameterNameConstants.WIREBAREHV1) * inputData.get(ParameterNameConstants.WIREBAREHV2) * ParameterNameConstants.NO_IN_PARALLEL_RA_HV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_HV2;
                outputData.put(ParameterNameConstants.CROSS_SECTION_HV, answer);
            }
            else {
                double answer = inputData.get(ParameterNameConstants.WIREBAREHV1) * inputData.get(ParameterNameConstants.WIREBAREHV2);
                // Strip = 1.0
                if(inputData.get(ParameterNameConstants.WINDINGTYPEHV) == 1.0) {
                    answer -= 0.55;
                }
                // Foil = 2.0
                else {
                    answer -= 0.88;
                }
                answer = answer * ParameterNameConstants.NO_IN_PARALLEL_RA_HV1 * ParameterNameConstants.NO_IN_PARALLEL_RA_HV2;
                outputData.put(ParameterNameConstants.CROSS_SECTION_HV, answer);
            }
        }
    }
}
