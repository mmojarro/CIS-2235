// Programmer: Michael Mojarro
// Email: mmojarro@cnm.edu
// Program Name: Enigma Program
// File Name: EnigmaController.java

package com.cis2235.mojarrop8.mojarrop8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class EnigmaController {

    private String btnText;
    Enigma enigma = new Enigma();

    @FXML
    private ToggleGroup radioButtonGroup;

    @FXML
    private RadioButton rbEnigmaGeneratedKey;

    @FXML
    private RadioButton rbEnterKey;

    @FXML
    private TextField tfEnigmaKey;

    @FXML
    private Button btEncode;

    @FXML
    private Button btDecode;

    @FXML
    private Button btClear;

    @FXML
    private TextField tfMessage;

    @FXML
    private TextField tfCodedMessage;

    @FXML
    private TextField tfKey;

    // onAction ********************************************************************************************************
    @FXML
    void onActionSaveFile(ActionEvent event) {
        System.out.println("Save file clicked");
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Save File Dialog
        File file = fileChooser.showSaveDialog(null);

        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(filename);
                outputFile.println(enigma.getCodedMessage());
                if (tfEnigmaKey.getText().isEmpty()){
                    outputFile.println(tfKey.getText());
                }else {
                    outputFile.println(tfEnigmaKey.getText());
                }

                outputFile.close();

            } catch (Exception e) {
                //System.Logger.getLogger(ModuleLayer.Controller.class.getName()).log(System.Logger.Level.SEVERE, null, ex);
                System.out.println(e.getClass());
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void onActionOpenFile(ActionEvent event) {
        System.out.println("Open file clicked");
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);

        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
                //enigma.setCodedMessage(inputFile.nextLine());
                tfCodedMessage.setText(inputFile.nextLine());
                //enigma.setKeyValue(inputFile.nextInt());
                tfKey.setText(String.valueOf(inputFile.nextInt()));
                inputFile.close();
            } catch (Exception e) {
                System.out.println(e.getClass());
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void onActionClose(ActionEvent event) {
        System.out.println("Close clicked");
        //TODO: Close Program when clicked
    }

    @FXML
    void onActionRadioButtonEnigmaGeneratedKey(ActionEvent event) {
        RadioButton selectedRB = (RadioButton) radioButtonGroup.getSelectedToggle();
        btnText = selectedRB.getText();

    }

    @FXML
    void onActionRadioButtonEnterKey(ActionEvent event) {
        RadioButton selectedRB = (RadioButton) radioButtonGroup.getSelectedToggle();
        btnText = selectedRB.getText();
    }

    @FXML
    void onActionClearButton(ActionEvent event) {
        clear();
    }

    @FXML
    void onActionDecodeButton(ActionEvent event) {
        try {
            enigma.setCodedMessage(tfCodedMessage.getText());
            enigma.setKeyValue(Integer.parseInt(tfKey.getText()));
            enigma.decode();
            tfMessage.setText(enigma.getOriginalMessage());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("'Coded Message' filed must have message and 'Key' filed must contain an integer.");
            alert.setHeaderText("Error!\n" +e.getMessage());
            String s = "Must be an integer.";
            alert.setContentText(s + e.getMessage());
            alert.show();

            if (tfCodedMessage.getText().isEmpty() && tfKey.getText().isEmpty()){
                tfCodedMessage.setText("Error: Please enter coded message.");
                tfKey.setText("Error: Please Enter Key.");
            }else if (tfCodedMessage.getText().isEmpty()){
                tfCodedMessage.setText("Error: Please enter coded message.");
            }else if (tfKey.getText().isEmpty()){
                tfKey.setText("Error: Please Enter Key.");
            }
        }
    }

    @FXML
    void onActionEncodeButton(ActionEvent event) {
        enigma.setOriginalMessage(tfMessage.getText());
        System.out.println("Encode Text String: " + tfMessage.getText());

        if (Objects.equals(btnText, "Use an Enigma-Generated Key")) {
            enigma.setKeyValue(enigma.generateKey());
            enigma.encode();
            tfCodedMessage.setText(enigma.getCodedMessage());
            tfKey.setText(String.valueOf(enigma.getKeyValue()));
        }
        else {
            try {
                enigma.setKeyValue(Integer.parseInt(tfEnigmaKey.getText()));
                enigma.encode();
                tfCodedMessage.setText(enigma.getCodedMessage());
                tfKey.setText(String.valueOf(enigma.getKeyValue()));

            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("'Key' filed must contain an integer.");
                alert.setHeaderText("Error!\n" +e.getMessage());
                String s = "Must be an integer.";
                alert.setContentText(s + e.getMessage());
                alert.show();
            }
        }
    }

    @FXML
    private void initialize(){
        //enigma();
    }

    // Methods* ********************************************************************************************************

    /**
     * Clear text fields
     */
    public void clear(){
        tfEnigmaKey.clear();
        tfMessage.clear();
        tfCodedMessage.clear();
        tfKey.clear();
        Enigma enigma = new Enigma();
    }

}