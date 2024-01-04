package com.dynalektric.view.workViews;

import com.dynalektric.constants.DisplayConstant;
import com.dynalektric.constants.StyleConstants;
import com.dynalektric.constants.ViewMessages;
import com.dynalektric.control.Control;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.project.InputData;
import com.dynalektric.view.View;
import com.dynalektric.view.ViewMessage;
import com.dynalektric.view.components.InputDropDown;
import com.dynalektric.view.components.InputTextFieldWithLabel;
import com.dynalektric.view.components.MenuBar;
import com.dynalektric.view.components.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


public class InputWorkView extends AbstractWorkView{
    private final JPanel logoPanel = new JPanel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final static Logger LOGGER = LogManager.getLogger(WelcomeWorkView.class);
    public final static String VIEW_NAME = "Input Work view";

    String[] typesOfMaterialInputs = {"COPPER","ALUMINIUM"};
    String[] typesOfWindingInputs = {"STRIP", "FOIL"};
    String[] typesOfConnectionInputs = {"STAR","DELTA"};
    String[] windingTemperatureInputs = {"90","115"};
    String[] steelGradeInputs = {"CRNO-35","M4-27","MOH-23"};
    String[] oilDuctsLv1Inputs = {"0","1","2","3","4","5"};
    String[] oilDuctsLv2Inputs = {"0","6","8","10","12"};
    String[] oilDuctsHv1Inputs = {"0","1","2"};
    String[] oilDuctsHv2Inputs = {"0","6","8","10","12"};
    String[] coreBldgInputs = {"1.5","2"};
    String[] connectionInputs = {"Dyn11","Dyn5","Dd0","Yyn0","Yd1","Yd11","Dz0","Yz1","Yz11"};
    String[] coolingInputs = {"AN-CL-F","AF-CL","ONAN-CL","ONAF-CL","ONWF-CL","OFWF-CL"};
    String[] frequencyInputs = {"50","60"};

    //Input fields
    InputTextFieldWithLabel kvaIn = new InputTextFieldWithLabel("KVA :");
    InputTextFieldWithLabel kIn = new InputTextFieldWithLabel("K :");
    InputTextFieldWithLabel LVIn = new InputTextFieldWithLabel("Low Voltage :");
    InputTextFieldWithLabel HVIn = new InputTextFieldWithLabel("High Voltage :");

    InputTextFieldWithLabel wireBareLv1In = new InputTextFieldWithLabel("Wire Bare LV1");
    InputTextFieldWithLabel wireBareLv2In = new InputTextFieldWithLabel("Wire Bare LV2");
    InputTextFieldWithLabel wireBareHv1In = new InputTextFieldWithLabel("Wire Bare HV1");
    InputTextFieldWithLabel wireBareHv2In = new InputTextFieldWithLabel("Wire Bare HV2");
    InputTextFieldWithLabel noInParallel_R_A_Lv1In = new InputTextFieldWithLabel("No in Parallel R-A LV1");
    InputTextFieldWithLabel noInParallel_R_A_Lv2In = new InputTextFieldWithLabel("No in Parallel R-A LV2");
    InputTextFieldWithLabel noInParallel_R_A_Hv1In = new InputTextFieldWithLabel("No in Parallel R-A HV1");
    InputTextFieldWithLabel noInParallel_R_A_Hv2In = new InputTextFieldWithLabel("No in Parallel R-A HV2");
    InputTextFieldWithLabel sectionIn = new InputTextFieldWithLabel("Sections");
    InputTextFieldWithLabel layersLvIn = new InputTextFieldWithLabel("Layers Lv");
    InputTextFieldWithLabel layersHvIn = new InputTextFieldWithLabel("Layers Hv");
    InputTextFieldWithLabel insulationBetweenLayersLvIn = new InputTextFieldWithLabel("Insulation B/W Layers Lv");
    InputTextFieldWithLabel insulationBetweenLayersHvIn = new InputTextFieldWithLabel("Insulation B/W Layers Hv");

    InputTextFieldWithLabel sectionLengthIn = new InputTextFieldWithLabel("Section Length");
    InputTextFieldWithLabel oilDuctsAxialIn = new InputTextFieldWithLabel("Oil Ducts - Axial");
    InputTextFieldWithLabel transpositionLvIn = new InputTextFieldWithLabel("Transposition Lv");
    InputTextFieldWithLabel transpositionHvIn = new InputTextFieldWithLabel("Transposition Hv");
    InputTextFieldWithLabel compGapIn = new InputTextFieldWithLabel("Comp-Gap");
    InputTextFieldWithLabel endClearanceLvIn = new InputTextFieldWithLabel("End Clearances Lv");
    InputTextFieldWithLabel endClearanceHvIn = new InputTextFieldWithLabel("End Clearances Hv");
    InputTextFieldWithLabel limbPlateIn = new InputTextFieldWithLabel("Limb Plate");
    InputTextFieldWithLabel gapBobbinIn = new InputTextFieldWithLabel("Gap/Bobbin");
    InputTextFieldWithLabel deltaIn = new InputTextFieldWithLabel("δ");
    InputTextFieldWithLabel amIn = new InputTextFieldWithLabel("am");
    InputTextFieldWithLabel leadsIn = new InputTextFieldWithLabel("Leads");
    InputTextFieldWithLabel stackingFactorIn = new InputTextFieldWithLabel("Stacking Factor");
    InputTextFieldWithLabel fluxDensityIn = new InputTextFieldWithLabel("Flux Density");
    InputTextFieldWithLabel specLossIn = new InputTextFieldWithLabel("Spec Loses");
    InputTextFieldWithLabel coreWIn = new InputTextFieldWithLabel("Core W");
    InputTextFieldWithLabel ekPercentageGaurIn = new InputTextFieldWithLabel("ek % Gaur");
    InputTextFieldWithLabel ambienceAirTempIn = new InputTextFieldWithLabel("Ambience Air Temp");
    InputTextFieldWithLabel insulationLvIn = new InputTextFieldWithLabel("Insulation Lv");
    InputTextFieldWithLabel insulationHvIn = new InputTextFieldWithLabel("Insulation Hv");


    InputDropDown typesOfMaterialIn = new InputDropDown(typesOfMaterialInputs , "Type Of Material" , "COPPER");
    InputDropDown typesOfWindingLvIn = new InputDropDown(typesOfWindingInputs,"Type Of Winding Lv","STRIP");
    InputDropDown typesOfConnectionLvIn = new InputDropDown(typesOfConnectionInputs,"Types Of Connection Lv","STAR");
    InputDropDown typesOfWindingHvIn = new InputDropDown(typesOfWindingInputs,"Type Of Winding Hv","STRIP");
    InputDropDown typesOfConnectionHvIn = new InputDropDown(typesOfConnectionInputs,"Types Of Connection Hv","STAR");
    InputDropDown oilDuctsLv1In = new InputDropDown(oilDuctsLv1Inputs,"Oil Ducts Lv1","0");
    InputDropDown oilDuctsLv2In = new InputDropDown(oilDuctsLv2Inputs,"Oil Ducts Lv2","0");
    InputDropDown windingTemperatureIn = new InputDropDown(windingTemperatureInputs,"Winding Temperature","90");
    InputDropDown steelGradeIn =new InputDropDown(steelGradeInputs,"Steel Grade","CRNO-35");
    InputDropDown coreBldgIn = new InputDropDown(coreBldgInputs,"Core Bldg","1.5");
    InputDropDown oilDuctsHv1In = new InputDropDown(oilDuctsHv1Inputs,"Oil Ducts Hv1","0");
    InputDropDown oilDuctsHv2In = new InputDropDown(oilDuctsHv2Inputs,"Oil Ducts Hv1","0");
    InputDropDown coolingIn = new InputDropDown(coolingInputs,"Cooling Inputs","AN-CL-F");
    InputDropDown connectionIn = new InputDropDown(connectionInputs,"Connection","Dyn11");
    InputDropDown frequencyIn = new InputDropDown(frequencyInputs,"Frequency","50");

    Control controller = new Control();
    public InputWorkView(Model model) {
        super(model);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeUI();
            }
        });
    }
    private void initializeUI(){
        this.setLayout(new BorderLayout());
        this.initializeLogoPanel();
        mainPanel.add(new MenuBar(this) , BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);

        JPanel inputWorkViewPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel mainLeftPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel dropDownPanel = new JPanel();
        JPanel inputLeftPanel = new JPanel();
        JPanel dropDownLeftPanel = new JPanel();
        JPanel dropDownRightPanel = new JPanel();
        JPanel inputRightPanel = new JPanel();
        JPanel defaultPanel = new JPanel();
        JPanel defaultLeftPanel = new JPanel();
        JPanel defaultRightPanel = new JPanel();

        inputWorkViewPanel.setLayout(new GridLayout(0, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel , BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel , BoxLayout.Y_AXIS));
        mainLeftPanel.setLayout(new GridLayout(2,0));
        inputPanel.setLayout(new GridLayout(0,2));
        dropDownPanel.setLayout(new GridLayout(0,2));
        dropDownLeftPanel.setLayout(new BoxLayout(dropDownLeftPanel,BoxLayout.Y_AXIS));
        dropDownRightPanel.setLayout(new BoxLayout(dropDownRightPanel,BoxLayout.Y_AXIS));
        inputLeftPanel.setLayout(new BoxLayout(inputLeftPanel,BoxLayout.Y_AXIS));
        inputRightPanel.setLayout(new BoxLayout(inputRightPanel,BoxLayout.Y_AXIS));
        defaultPanel.setLayout(new GridLayout(0,2));
        defaultLeftPanel.setLayout(new BoxLayout(defaultLeftPanel,BoxLayout.Y_AXIS));
        defaultRightPanel.setLayout(new BoxLayout(defaultRightPanel,BoxLayout.Y_AXIS));

        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(kvaIn);
        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(kIn);
        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(LVIn);
        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(HVIn);
        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(typesOfMaterialIn);
        inputLeftPanel.add(Box.createVerticalStrut(15));
        inputLeftPanel.add(coreBldgIn);

        inputRightPanel.add(Box.createVerticalStrut(10));
        inputRightPanel.add(connectionIn);
        inputRightPanel.add(Box.createVerticalStrut(10));
        inputRightPanel.add(coolingIn);
        inputRightPanel.add(Box.createVerticalStrut(10));
        inputRightPanel.add(frequencyIn);
        inputRightPanel.add(Box.createVerticalStrut(10));
        inputRightPanel.add(windingTemperatureIn);
        inputRightPanel.add(Box.createVerticalStrut(10));
        inputRightPanel.add(steelGradeIn);



        dropDownLeftPanel.add(Box.createVerticalStrut(10));
        dropDownLeftPanel.add(typesOfWindingLvIn);
        dropDownLeftPanel.add(Box.createVerticalStrut(10));
        dropDownLeftPanel.add(typesOfConnectionLvIn);
        dropDownLeftPanel.add(Box.createVerticalStrut(10));
        dropDownLeftPanel.add(oilDuctsLv1In);
        dropDownLeftPanel.add(Box.createVerticalStrut(10));
        dropDownLeftPanel.add(oilDuctsLv2In);
        dropDownLeftPanel.add(Box.createVerticalStrut(10));

        dropDownRightPanel.add(Box.createVerticalStrut(10));
        dropDownRightPanel.add(typesOfWindingHvIn);
        dropDownRightPanel.add(Box.createVerticalStrut(10));
        dropDownRightPanel.add(typesOfConnectionHvIn);
        dropDownRightPanel.add(Box.createVerticalStrut(10));
        dropDownRightPanel.add(oilDuctsHv1In);
        dropDownRightPanel.add(Box.createVerticalStrut(10));
        dropDownRightPanel.add(oilDuctsHv2In);
        dropDownRightPanel.add(Box.createVerticalStrut(10));

        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(fluxDensityIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(wireBareLv1In);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(wireBareLv2In);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(noInParallel_R_A_Lv1In);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(noInParallel_R_A_Lv2In);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(insulationLvIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(layersLvIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(insulationBetweenLayersLvIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(transpositionLvIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(endClearanceLvIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(oilDuctsAxialIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(compGapIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(limbPlateIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(deltaIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));
        defaultLeftPanel.add(leadsIn);
        defaultLeftPanel.add(Box.createVerticalStrut(10));


        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(coreWIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(wireBareHv1In);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(wireBareHv2In);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(noInParallel_R_A_Hv1In);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(noInParallel_R_A_Hv2In);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(insulationHvIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(layersHvIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(insulationBetweenLayersHvIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(transpositionHvIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(endClearanceHvIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(gapBobbinIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(amIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(stackingFactorIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
//        defaultRightPanel.add(specLossIn);
//        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(ekPercentageGaurIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));
        defaultRightPanel.add(ambienceAirTempIn);
        defaultRightPanel.add(Box.createVerticalStrut(10));



        inputPanel.add(inputLeftPanel);
        inputPanel.add(inputRightPanel);
        inputPanel.setSize(inputPanel.getPreferredSize().width , inputPanel.getPreferredSize().height);

        dropDownPanel.add(dropDownLeftPanel);
        dropDownPanel.add(dropDownRightPanel);

        rightPanel.add(defaultPanel);
        JPanel navigationPanel = initializeNavigationPanel();
        navigationPanel.setAlignmentX(CENTER_ALIGNMENT);
        dropDownRightPanel.add(navigationPanel);

        defaultPanel.add(defaultLeftPanel);
        defaultPanel.add(defaultRightPanel);

        mainLeftPanel.add(inputPanel);
        mainLeftPanel.add(dropDownPanel);

        leftPanel.add(mainLeftPanel);
        JScrollPane defaultScrollPane = new JScrollPane(rightPanel);
        inputWorkViewPanel.add(leftPanel);
        inputWorkViewPanel.add(defaultScrollPane);

        mainPanel.add(inputWorkViewPanel);
        this.refreshInputValues();
    }

    private void initializeLogoPanel(){

        logoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        logoPanel.setPreferredSize(new Dimension(
                (int) View.SCREEN_DIMENSION.getWidth(),
                (int)(View.SCREEN_DIMENSION.getHeight()*0.1)));
        this.add(logoPanel,BorderLayout.NORTH);
        try{
            BufferedImage nhceLogoImage = ImageIO.read(this.getClass().getResource("NHCE.jpg"));
            ImageIcon nhceLogoIcon = new ImageIcon(nhceLogoImage);
            Image nhceScaledLogoImage = nhceLogoIcon.getImage().getScaledInstance((int)(this.getPreferredSize().getWidth()*0.15), -1, Image.SCALE_SMOOTH);
            ImageIcon nhceScaledLogoIcon = new ImageIcon(nhceScaledLogoImage);
            JLabel nhceIconLabel = new JLabel(nhceScaledLogoIcon);

            BufferedImage dynaLogoImage = ImageIO.read(this.getClass().getResource("DYNA.jpg"));
            ImageIcon dynaLogoIcon = new ImageIcon(dynaLogoImage);
            Image dynaScaledLogoImage = dynaLogoIcon.getImage().getScaledInstance((int)(this.getPreferredSize().getWidth()*0.04), -1, Image.SCALE_SMOOTH);
            ImageIcon dynaScaledLogoIcon = new ImageIcon(dynaScaledLogoImage);
            JLabel dynaIconLabel = new JLabel(dynaScaledLogoIcon);

            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.1)));
            logoPanel.add(nhceIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.6)));
            logoPanel.add(dynaIconLabel);
            logoPanel.add(Box.createHorizontalStrut((int)(this.getPreferredSize().getWidth()*0.02)));
        }
        catch(IOException e){
            LOGGER.error(e.getMessage() ,e);
        }

    }
    @Override
    public void captureEventFromChildSubFrame(ViewMessage message) {
        switch (message.getMsgType()) {
            case ViewMessages.CLOSE_OPENED_PROJECT:
                controller.closeOpenedProject();
                break;
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public Integer getViewId() {
        return 2;
    }

    private JPanel initializeNavigationPanel(){
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton calculateBtn = new JButton("Calculate");
        JButton nextBtn = new JButton("Next");
        calculateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getEnteredValueAndCalculate();
            }
        });
        navigationPanel.add(calculateBtn);
        nextBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                View.getSingleton().setView(OutputOneWorkView.VIEW_NAME);
            }
        });
        navigationPanel.add(nextBtn);
        return navigationPanel;
    }

    private void refreshInputValues(){
        InputData inputData = model.getLoadedProject().inputs;
        this.kvaIn.setValueEntered(inputData.KVA.toString());
        this.kIn.setValueEntered(inputData.K.toString());
        this.LVIn.setValueEntered(inputData.LINEVOLTSLV.toString());
        this.HVIn.setValueEntered(inputData.LINEVOLTSHV.toString());
        this.insulationLvIn.setValueEntered(inputData.INSULATION_LV.toString());
        this.insulationHvIn.setValueEntered(inputData.INSULATION_HV.toString());
        this.layersLvIn.setValueEntered(inputData.LAYER_LV.toString());
        this.layersHvIn.setValueEntered(inputData.LAYER_HV.toString());
        this.insulationBetweenLayersLvIn.setValueEntered(inputData.INSULATION_BETWEEN_LAYERS_LV.toString());
        this.insulationBetweenLayersHvIn.setValueEntered(inputData.INSULATION_BETWEEN_LAYERS_HV.toString());
        this.oilDuctsAxialIn.setValueEntered(inputData.OIL_DUCTS_AXIAL_LV.toString());
        this.transpositionLvIn.setValueEntered(inputData.TRANSPOSITION_LV.toString());
        this.transpositionHvIn.setValueEntered(inputData.TRANSPOSITION_HV.toString());
        this.compGapIn.setValueEntered(inputData.COMP_GAP_LV.toString());
        this.endClearanceLvIn.setValueEntered(inputData.END_CLEARANCES_LV.toString());
        this.endClearanceHvIn.setValueEntered(inputData.END_CLEARANCES_HV.toString());
        this.limbPlateIn.setValueEntered(inputData.LIMB_PLATE_W.toString());
        this.wireBareLv1In.setValueEntered(inputData.WIREBARELV1.toString());
        this.wireBareLv2In.setValueEntered(inputData.WIREBARELV2.toString());
        this.wireBareHv1In.setValueEntered(inputData.WIREBAREHV1.toString());
        this.wireBareHv2In.setValueEntered(inputData.WIREBAREHV2.toString());
        this.noInParallel_R_A_Lv1In.setValueEntered(inputData.NO_IN_PARALLEL_RA_LV1.toString());
        this.noInParallel_R_A_Lv2In.setValueEntered(inputData.NO_IN_PARALLEL_RA_LV2.toString());
        this.noInParallel_R_A_Hv1In.setValueEntered(inputData.NO_IN_PARALLEL_RA_HV1.toString());
        this.noInParallel_R_A_Hv2In.setValueEntered(inputData.NO_IN_PARALLEL_RA_HV2.toString());

        //
        this.gapBobbinIn.setValueEntered(inputData.GAP_W.toString());
        this.deltaIn.setValueEntered(inputData.DELTA_W.toString());
        this.amIn.setValueEntered(inputData.AM_W.toString());
        this.leadsIn.setValueEntered("6");
        this.stackingFactorIn.setValueEntered(inputData.STACKING_FACTOR.toString());
        this.fluxDensityIn.setValueEntered(inputData.FLUX_DENSITY.toString());
        //this.specLossIn.setValueEntered(inputData.);
        this.coreWIn.setValueEntered(inputData.CORE_W.toString());
        this.ambienceAirTempIn.setValueEntered("50");
        this.ekPercentageGaurIn.setValueEntered("5");
        //this.ambienceAirTempIn.setValueEntered(inputData.AMB);

        this.typesOfMaterialIn.setValueSelected(inputData.CONDUCTOR);
        this.coreBldgIn.setValueSelected(inputData.CORE_BLDG_FACTOR.toString());
        this.connectionIn.setValueSelected(inputData.CONNECTION);
        this.coolingIn.setValueSelected(inputData.COOLING);
        this.frequencyIn.setValueSelected(inputData.FREQUENCY.toString());
        this.windingTemperatureIn.setValueSelected(inputData.WIND_TEMP.toString());
        this.steelGradeIn.setValueSelected(inputData.STEEL_GRADE);
        this.typesOfWindingLvIn.setValueSelected(inputData.WINDINGTYPELV);
        this.typesOfWindingHvIn.setValueSelected(inputData.WINDINGTYPEHV);
        this.typesOfConnectionLvIn.setValueSelected(inputData.CONNECTIONTYPELV);
        this.typesOfConnectionHvIn.setValueSelected(inputData.CONNECTIONTYPEHV);
        this.oilDuctsLv1In.setValueSelected(inputData.OIL_DUCTS_RADIAL_LV1.toString());
        this.oilDuctsLv2In.setValueSelected(inputData.OIL_DUCTS_RADIAL_LV2.toString());
        this.oilDuctsHv1In.setValueSelected(inputData.OIL_DUCTS_RADIAL_HV1.toString());
        this.oilDuctsHv2In.setValueSelected(inputData.OIL_DUCTS_RADIAL_HV2.toString());
    }

    private void getEnteredValueAndCalculate(){
        InputData input = model.getLoadedProjectInput();
        input.KVA = Double.parseDouble(this.kvaIn.getValueEntered());
        input.K = Double.parseDouble(this.kIn.getValueEntered());
        input.LINEVOLTSLV = Double.parseDouble(this.LVIn.getValueEntered());
        input.LINEVOLTSHV = Double.parseDouble(this.HVIn.getValueEntered());
        input.CONNECTION = this.connectionIn.getValueSelected();
        input.COOLING = this.coolingIn.getValueSelected();
        input.FREQUENCY = Double.parseDouble(this.frequencyIn.getValueSelected());
        input.WIND_TEMP = Double.parseDouble(this.windingTemperatureIn.getValueSelected());
        input.CONDUCTOR = this.typesOfMaterialIn.getValueSelected();
        input.CORE_BLDG_FACTOR = Double.parseDouble(this.coreBldgIn.getValueSelected());
        input.STEEL_GRADE = this.steelGradeIn.getValueSelected();

        input.WINDINGTYPELV = this.typesOfWindingLvIn.getValueSelected();
        input.WINDINGTYPEHV = this.typesOfWindingHvIn.getValueSelected();
        input.CONNECTIONTYPELV = this.typesOfConnectionLvIn.getValueSelected();
        input.CONNECTIONTYPEHV = this.typesOfWindingHvIn.getValueSelected();
        input.OIL_DUCTS_RADIAL_LV1 = Integer.parseInt(this.oilDuctsLv1In.getValueSelected());
        input.OIL_DUCTS_RADIAL_HV1 = Integer.parseInt(this.oilDuctsHv1In.getValueSelected());
        input.OIL_DUCTS_RADIAL_LV2 = Integer.parseInt(this.oilDuctsLv2In.getValueSelected());
        input.OIL_DUCTS_RADIAL_HV2 = Integer.parseInt(this.oilDuctsHv2In.getValueSelected());

        input.FLUX_DENSITY = Double.parseDouble(this.fluxDensityIn.getValueEntered());
        input.CORE_W = Double.parseDouble(this.coreWIn.getValueEntered());
        input.WIREBARELV1 = Double.parseDouble(this.wireBareLv1In.getValueEntered());
        input.WIREBARELV2 = Double.parseDouble(this.wireBareLv2In.getValueEntered());
        input.WIREBAREHV1 = Double.parseDouble(this.wireBareHv1In.getValueEntered());
        input.WIREBAREHV2 = Double.parseDouble(this.wireBareHv2In.getValueEntered());
        input.NO_IN_PARALLEL_RA_LV1 = Double.parseDouble(this.noInParallel_R_A_Lv1In.getValueEntered());
        input.NO_IN_PARALLEL_RA_LV2 = Double.parseDouble(this.noInParallel_R_A_Lv2In.getValueEntered());
        input.NO_IN_PARALLEL_RA_HV1 = Double.parseDouble(this.noInParallel_R_A_Hv1In.getValueEntered());
        input.NO_IN_PARALLEL_RA_HV2 = Double.parseDouble(this.noInParallel_R_A_Hv2In.getValueEntered());
        input.INSULATION_LV = Double.parseDouble(this.insulationLvIn.getValueEntered());
        input.INSULATION_HV = Double.parseDouble(this.insulationHvIn.getValueEntered());
        input.LAYER_LV = Integer.parseInt(this.layersLvIn.getValueEntered());
        input.LAYER_HV = Integer.parseInt(this.layersHvIn.getValueEntered());
        input.INSULATION_BETWEEN_LAYERS_LV = Double.parseDouble(this.insulationBetweenLayersLvIn.getValueEntered());
        input.INSULATION_BETWEEN_LAYERS_HV = Double.parseDouble(this.insulationBetweenLayersHvIn.getValueEntered());
        input.TRANSPOSITION_LV = Integer.parseInt(this.transpositionLvIn.getValueEntered());
        input.TRANSPOSITION_HV = Integer.parseInt(this.transpositionHvIn.getValueEntered());
        input.END_CLEARANCES_LV = Double.parseDouble(this.endClearanceLvIn.getValueEntered());
        input.END_CLEARANCES_HV = Double.parseDouble(this.endClearanceHvIn.getValueEntered());
        input.OIL_DUCTS_AXIAL_LV = Integer.parseInt(this.oilDuctsAxialIn.getValueEntered());
        input.GAP_W = Integer.parseInt(this.gapBobbinIn.getValueEntered());
        input.COMP_GAP_LV = Integer.parseInt(this.compGapIn.getValueEntered());
        input.AM_W = Integer.parseInt(this.amIn.getValueEntered());
        input.LIMB_PLATE_W = Integer.parseInt(this.limbPlateIn.getValueEntered());
        input.STACKING_FACTOR = Double.parseDouble(this.stackingFactorIn.getValueEntered());
        input.DELTA_W = Integer.parseInt(this.deltaIn.getValueEntered());

        model.setLoadedProjectInput(input);
    }
}
