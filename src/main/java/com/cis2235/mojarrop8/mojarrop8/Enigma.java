// Programmer: Michael Mojarro
// Email: mmojarro@cnm.edu
// Program Name: Enigma Program
// File Name: Enigma.java

package com.cis2235.mojarrop8.mojarrop8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class Enigma {
    private int keyValue;
    private String originalMessage;
    private String codedMessage;
    private int fillAscii = 32 ;
    private ArrayList<Integer> ascii = new ArrayList<>();
    private ArrayList<Character> asciiSymbol = new ArrayList<>();
    private ArrayList<Character> origMessageList = new ArrayList<>();
    private ArrayList<Character> codedMessageList = new ArrayList<>();

    Enigma(){
        asciiSymbol = asciiSymbol();
        // Fill ascii ArrayList with numeric value 32-126 and print to console for test confirmation
        for (int i = 0; i < 95; i++) {
            ascii.add(fillAscii);
            System.out.println("ASCII Value: " + ascii.get(i) + "\tASCII Symbol: " + asciiSymbol.get(i));
            fillAscii ++;
        }


    }

    // Accessors / Mutators*********************************************************************************************
    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }
    public int getKeyValue() {
        return keyValue;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getOriginalMessage() {
        Character origMessage[] = origMessageList.toArray(new Character[0]);
        String originalMessage = "";
        // Build string with Characters from codedMessageList
        for (int i = 0; i < origMessageList.size(); i++) {
            originalMessage = originalMessage + String.valueOf(origMessage[i]);
        }
        return originalMessage;
    }

    public void setCodedMessage(String codedMessage) {
        this.codedMessage = codedMessage;
    }

    public String getCodedMessage() {

        Character codedMessage[] = codedMessageList.toArray(new Character[0]);
        String codedMSG = "";
        // Build string with Characters from codedMessageList
        for (int i = 0; i < codedMessageList.size(); i++) {
            codedMSG = codedMSG + String.valueOf(codedMessage[i]);
        }
        return codedMSG;
    }

    //Methods **********************************************************************************************************
    /**
     * Encodes message
     */
    public void encode(){
        int indexWithKey = keyValue;
        int wrapKey;
        // Splits text input into Chars for origMessageList array
        for (int i = 0; i < originalMessage.length(); i++) {
            origMessageList.add(originalMessage.charAt(i));
            System.out.println("Chars from Text String: " + origMessageList.get(i));
        }

        for (int i = 0; i < origMessageList.size(); i++) {
            for (int j = 0; j < asciiSymbol.size(); j++) {
                if (origMessageList.get(i).equals(asciiSymbol().get(j))){
                    if ((ascii.get(j)+keyValue) > 126){
                        wrapKey = (ascii.get(j)+keyValue) - 126;
                        //indexWithKey = 32 + wrapKey;.
                        indexWithKey = wrapKey;
                    }
                    else {
                        indexWithKey = ascii.indexOf((ascii.get(j) + keyValue));
                    }

                    codedMessageList.add(asciiSymbol.get((indexWithKey)));
                    System.out.println("Chars from Text String: " + origMessageList.get(i) + "\tASCII Value: " + ascii.get(j)
                    + "\tNew ASCII Value with key added: " + (ascii.get(j)+keyValue) + "\tCoded message Char: " + codedMessageList.get(i));
                    //codedMessageList.add(asciiSymbol.get((ascii.get(j)+keyValue)));
                }
            }
        }
    }

    /**
     * Decodes message
     */
    public void decode(){
        int indexWithKey = keyValue;
        int wrapKey;
        // Splits text input into Chars for origMessageList array
        for (int i = 0; i < codedMessage.length(); i++) {
            codedMessageList.add(codedMessage.charAt(i));
            System.out.println("Chars from Coded text String: " + codedMessageList.get(i));
        }
        //TODO: Fix decoding messages that wrap
        for (int i = 0; i < codedMessageList.size(); i++) {
            for (int j = 0; j < asciiSymbol.size(); j++) {
                if (codedMessageList.get(i).equals(asciiSymbol().get(j))){
                    if ((ascii.get(j) - keyValue) < 32){
                        //wrapKey = (ascii.get(j) - keyValue) + 126;
                        //indexWithKey = 32 + wrapKey;
                        //indexWithKey = ((ascii.get(j) - keyValue) -32) + 126;
                        indexWithKey = ascii.indexOf(((ascii.get(j) - keyValue) -32) + 126);
                    }
                    else {
                        indexWithKey = ascii.indexOf((ascii.get(j) - keyValue));
                    }

                    origMessageList.add(asciiSymbol.get((indexWithKey)));
                    System.out.println("Chars from Coded Text String: " + codedMessageList.get(i) + "\tASCII Value: " + ascii.get(j)
                            + "\tNew ASCII Value with key subtracted: " + (ascii.get(j)-keyValue) + "\tOriginal message Char: " + origMessageList.get(i));
                }
            }
        }
    }

    /**
     * Adds ascii symbols for ascii value range 32-126 to ArrayList
     * @return ArrayList of Characters
     */
    public ArrayList<Character> asciiSymbol(){
        ArrayList<Character> asciiChar = new ArrayList<>(Arrays.asList(' ','!','\"','#','$','%','&','\'','(',')','*','+'
                ,',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E'
                ,'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Q','Y','Z','[','\\',']','^','_'
                ,'`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','q','y'
                ,'z','{','|','}','~'));

        return asciiChar;
    }

    /**
     * Generate a random key
     * @return returns a random integer
     */
    public int generateKey(){
        Random rand = new Random();
        int randomNum = rand.nextInt((49 - 0) + 1) + 0;
        return randomNum;
    }

}
