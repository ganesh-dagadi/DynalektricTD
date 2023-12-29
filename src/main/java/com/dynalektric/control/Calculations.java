package com.dynalektric.control;

import com.dynalektric.constants.ParameterNameConstants;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.InputData;
import com.dynalektric.model.repositories.project.OutputData;
import jdk.internal.org.jline.utils.AnsiWriter;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import jdk.javadoc.internal.doclets.toolkit.util.PreviewAPIListBuilder;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.lang.Math;
import java.util.Objects;

public class Calculations {
    Model model;

//    private final HashMap<String, Double> outputData = new HashMap<>();
    private final InputData inputData;
    private final OutputData outputData;

    public Calculations() {
        model = Model.getSingleton();
        this.inputData = model.getLoadedProjectInput();
        this.outputData = model.getOutputData();
    }

    public void beginCalculations() {
    }

    //Write all the functions here

    public void vph_lv_star() {
        // Calculating VPH value for LV star Connection Type.
        outputData.VPH_LV = inputData.LINEVOLTSLV / Math.sqrt(3);
    }

    public void vph_hv_star() {
        // Calculating VPH value for HV star Connection Type.
        outputData.VPH_HV = inputData.LINEVOLTSHV / Math.sqrt(3);
    }

    public void iph_lv_star() {
        // Calculating IPH value for LV star Connection Type.
        outputData.IPH_LV = (inputData.KVA * 1000) / (Math.sqrt(3) * inputData.LINEVOLTSLV);
    }

    public void iph_hv_star() {
        // Calculating IPH value for HV star Connection Type.
        outputData.IPH_HV = (inputData.KVA * 1000) / (Math.sqrt(3) * inputData.LINEVOLTSHV);
    }

    public void vph_lv_delta() {
        // Calculating VPH value for LV delta Connection type.
        outputData.VPH_LV = inputData.LINEVOLTSLV / Math.sqrt(3);
    }

    public void vph_hv_delta() {
        //calculating VPH value for HV delta Connection type.
        outputData.VPH_HV = inputData.LINEVOLTSHV / Math.sqrt(3);

    }

    public void iph_lv_delta() {
        //calculating IPH value for LV delta Connection type.
        outputData.IPH_LV = (inputData.KVA * 1000) / (3 * outputData.VPH_LV);
    }

    public void iph_hv_delta() {
        // calculating  IPH value for HV delta Connection type.
        outputData.IPH_HV = (inputData.KVA * 1000) / (3 * outputData.VPH_HV);

    }

    public void v_t() {
        // Calculating the value of v/t.
        outputData.V_T = 1.01 * Math.sqrt(inputData.KVA / 3) * (inputData.K / 100);
    }

    public void rated_voltage_LV() {
        if (Objects.equals(inputData.CONNECTIONTYPELV, "DELTA")) {
            outputData.RATED_VOLTAGE_LV = inputData.LINEVOLTSLV / Math.sqrt(3);
        } else {
            outputData.RATED_VOLTAGE_LV = inputData.LINEVOLTSLV;
        }
    }

    public void rated_voltage_HV() {
        if (Objects.equals(inputData.CONNECTIONTYPEHV, "DELTA")) {
            outputData.RATED_VOLTAGE_HV = inputData.LINEVOLTSHV / Math.sqrt(3);
        } else {
            outputData.RATED_VOLTAGE_HV = inputData.LINEVOLTSHV;
        }
    }

    public void rated_current_LV() {
        if (Objects.equals(inputData.CONNECTIONTYPELV, "DELTA")) {
            outputData.RATED_CURRENT_LV = (inputData.KVA * 1000) / (3 * outputData.RATED_CURRENT_LV);
        } else {
            outputData.RATED_CURRENT_LV = inputData.KVA * 1000 / Math.sqrt(3) * inputData.LINEVOLTSLV;
        }
    }

    public void rated_current_HV() {
        if (Objects.equals(inputData.CONNECTIONTYPEHV, "DELTA")) {
            outputData.RATED_CURRENT_HV = (inputData.KVA * 1000) / (3 * outputData.RATED_VOLTAGE_HV);
        } else {
            outputData.RATED_CURRENT_HV = (inputData.KVA * 1000) / (Math.sqrt(3) * inputData.LINEVOLTSHV);
        }
    }

    public void cross_section_LV() {
        if (Objects.equals(inputData.WIREBARELV1, inputData.WIREBARELV2)) {
            outputData.CROSS_SECTION_LV = inputData.WIREBARELV1 * inputData.WIREBARELV2 * 0.7854 * inputData.NO_IN_PARALLEL_RA_LV1 * inputData.NO_IN_PARALLEL_RA_LV2;
        } else {
            if (Objects.equals(inputData.WINDINGTYPELV, "FOIL")) {
                outputData.CROSS_SECTION_LV = inputData.WIREBARELV1 * inputData.WIREBARELV2 * inputData.NO_IN_PARALLEL_RA_LV1 * inputData.NO_IN_PARALLEL_RA_LV2;
            } else {
                double answer = inputData.WIREBARELV1 * inputData.WIREBARELV2;

                if (Objects.equals(inputData.WINDINGTYPELV, "STRIP")) {
                    answer -= 0.55;
                }
                // Foil = 2.0
                else {
                    answer -= 0.88;
                }
                answer = answer * inputData.NO_IN_PARALLEL_RA_LV1 * inputData.NO_IN_PARALLEL_RA_LV2;
                outputData.CROSS_SECTION_LV = answer ;
            }
        }
    }

    public void cross_section_HV() {
        if (Objects.equals(inputData.WIREBAREHV1, inputData.WIREBAREHV2)) {
            outputData.CROSS_SECTION_HV = inputData.WIREBAREHV1 * inputData.WIREBAREHV2 * 0.7854 * inputData.NO_IN_PARALLEL_RA_HV1 * inputData.NO_IN_PARALLEL_RA_HV2;
        } else {
            if (Objects.equals(inputData.WINDINGTYPEHV, "FOIL")) {
                outputData.CROSS_SECTION_HV = inputData.WIREBAREHV1 * inputData.WIREBAREHV2 * inputData.NO_IN_PARALLEL_RA_HV1 * inputData.NO_IN_PARALLEL_RA_HV2;
            } else {
                double answer = inputData.WIREBAREHV1 * inputData.WIREBAREHV2;
                // Strip = 1.0
                if (Objects.equals(inputData.WINDINGTYPEHV, "STRIP")) {
                    answer -= 0.55;
                }
                // Foil = 2.0
                else {
                    answer -= 0.88;
                }
                answer = answer * inputData.NO_IN_PARALLEL_RA_HV1 * inputData.NO_IN_PARALLEL_RA_HV2;
                outputData.CROSS_SECTION_HV = answer;
            }
        }
    }

    public void current_density_LV() {
        //calculating current density value for LV connection type.
        outputData.CURRENT_DENSITY_LV = outputData.RATED_CURRENT_LV / outputData.CROSS_SECTION_LV;
    }

    public void current_density_HV() {
        //calculating current density value for HV connection type.
        outputData.CURRENT_DENSITY_HV = outputData.RATED_CURRENT_HV / outputData.CROSS_SECTION_HV;
    }

    public void turn_limb_LV() {
        //calculating  turn/limb for LV connection type.
        outputData.TURN_LIMB_LV = (double) Math.round(outputData.RATED_CURRENT_LV / outputData.V_T);
    }

    public void turn_limb_HV() {
        //calculating  turn/limb value for HV connection type.
        outputData.TURN_LIMB_HV = (double) Math.round(outputData.RATED_VOLTAGE_HV / outputData.V_T);
    }

    public void turn_layer_LV() {
        //calculating  turn/layer for LV connection type.
        outputData.TURN_LAYER_LV = Math.ceil(outputData.TURN_LIMB_LV / inputData.LAYER_LV);
    }

    public void turn_layer_HV() {
        //calculating  turn/layer value for HV connection type.
        outputData.TURN_LAYER_HV = Math.ceil(outputData.TURN_LIMB_HV / inputData.LAYER_HV);
    }

    public void wire_insulated_LV1() {
        double answer = inputData.WIREBARELV1;
        if (Objects.equals(inputData.WINDINGTYPELV, "STRIP")) {
            answer += inputData.INSULATION_LV;
        }

        outputData.WIRE_INSULATED_LV1 = answer;
    }

    public void wire_insulated_LV2() {
        double answer = inputData.WIREBARELV2;
        if (Objects.equals(inputData.WINDINGTYPELV, "STRIP")) {
            answer += inputData.INSULATION_LV;
        }

        outputData.WIRE_INSULATED_LV2 = answer;
    }

    public void wire_insulated_HV1() {
        double answer = inputData.WIREBAREHV1;
        if (Objects.equals(inputData.WINDINGTYPEHV, "STRIP")) {
            answer += inputData.INSULATION_HV;
        }

        outputData.WIRE_INSULATED_HV1 = answer;
    }

    public void wire_insulated_HV2() {
        double answer = inputData.WIREBAREHV1;
        if (Objects.equals(inputData.WINDINGTYPEHV, "STRIP")) {
            answer += inputData.INSULATION_HV;
        }

        outputData.WIRE_INSULATED_HV2 = answer;
    }

    public void wind_length_LV() {
        double answer = outputData.WIRE_INSULATED_LV1 * outputData.WIRE_INSULATED_LV2;
        double value = outputData.TURN_LAYER_LV;
        if (Objects.equals(inputData.WINDINGTYPELV, "STRIP")) {
            value += 1;
        }
        answer = answer * value;
        answer = answer + inputData.TRANSPOSITION_LV + inputData.COMP_GAP_LV;
        answer = Math.round(answer);

        outputData.WIND_LENGTH_LV = answer;
    }

    public void wind_length_HV() {
        double answer = outputData.WIRE_INSULATED_HV1 * outputData.WIRE_INSULATED_HV2;
        double value = outputData.TURN_LAYER_HV;
        if (Objects.equals(inputData.WINDINGTYPEHV, "STRIP")) {
            value += 1;
        }
        answer = answer * value;
        answer = answer + inputData.TRANSPOSITION_HV + inputData.COMP_GAP_HV;
        answer = Math.round(answer);

        outputData.WIND_LENGTH_HV = answer;
    }

    public void wdg_lg_imp_calcu_LV() {
        double answer = outputData.WIND_LENGTH_LV;
        if (Objects.equals(inputData.WINDINGTYPELV, "STRIP")) {
            answer = answer - (outputData.WIRE_INSULATED_LV1 * outputData.WIRE_INSULATED_LV2);
        }
        outputData.WDG_LG_IMP_CALCU_LV = answer;
    }

    public void wdg_lg_imp_calcu_HV() {
        double answer = outputData.WIND_LENGTH_HV;
        if (ParameterNameConstants.WINDINGTYPEHV == 1.0) {
            answer = answer - (outputData.WIRE_INSULATED_HV1 * outputData.WIRE_INSULATED_HV2);

        }
        outputData.WDG_LG_IMP_CALCU_HV = answer;
    }

    public void limb_length_LV() {
        outputData.LIMB_LENGTH_LV = outputData.WIND_LENGTH_LV * inputData.END_CLEARANCES_LV;
    }

    public void limb_length_HV() {
        outputData.LIMB_LENGTH_HV = outputData.WIND_LENGTH_HV * inputData.END_CLEARANCES_HV;
    }

    public void wind_radial_depth_lv() {
        double answer = outputData.WIRE_INSULATED_LV1 * inputData.NO_IN_PARALLEL_RA_LV2 * inputData.LAYER_LV;
        answer = answer + inputData.OIL_DUCTS_RADIAL_LV1 * inputData.OIL_DUCTS_RADIAL_LV2;
        answer = answer + (inputData.INSULATION_BETWEEN_LAYERS_LV * (inputData.LAYER_LV - 1) * 1.1);
        answer = answer / 5.0;
        answer = Math.round(answer) * 5;
        outputData.WIND_RADIAL_DEPTH_LV = answer;
    }

    public void wind_radial_depth_hv() {
        double answer = outputData.WIRE_INSULATED_HV1 * inputData.NO_IN_PARALLEL_RA_HV2 * inputData.LAYER_HV;
        answer = answer + inputData.OIL_DUCTS_RADIAL_HV1 * inputData.OIL_DUCTS_RADIAL_HV2;
        answer = answer + (inputData.INSULATION_BETWEEN_LAYERS_HV * (inputData.LAYER_HV - 1) * 1.1);
        answer = answer / 5.0;
        answer = Math.round(answer) * 5;
        outputData.WIND_RADIAL_DEPTH_HV = answer;
    }

    public void net_cross_section() {
        outputData.NET_CROSS_SECTION = outputData.V_T / ((4.44 * inputData.FREQUENCY * inputData.FLUX_DENSITY) / 10000);
    }

    public void total_core_mass() {

    }
}
