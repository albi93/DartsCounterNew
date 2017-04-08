package com.example.albiferenc.dartscounterold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Albi Ferenc on 2017.04.08..
 */

class CheckOut {

    private Map<Integer, String> doubleValues = new HashMap<Integer, String>();
    private Map<Integer, String> tripleValues = new HashMap<Integer, String>();

    public CheckOut() {
        initDoubleValues();
        initTripleValues();
    }

    public void initDoubleValues() {
        for(int i = 1; i <= 20; i++) {
            doubleValues.put(2 * i, "D" + String.valueOf(i));
        }
    }

    public void initTripleValues() {
        for(int i = 1; i <= 20; i++) {
            tripleValues.put(3 * i, "T" + String.valueOf(i));
        }
    }

// TODO 50

    public ArrayList<String> calculateCheckOut(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        if(score <= 160) {
            if(score <= 40) {
                tempList.addAll(calculate0To40(score));
            } else if(score <= 52) {
                tempList.addAll(calculate40To52(score));
            } else if(score <= 60) {
                tempList.addAll(calculate52To60(score));
            } else if(score <= 80) {
                tempList.addAll(calculate60To80(score));
            } else if(score <= 98) {
                tempList.addAll(calculate80To98(score));
            } else{
                tempList.add("Value over 100, could not find a match");
            }
            return tempList;
        } else {
            tempList.add("Over 160");
            return tempList;
        }
    }

    private String getDoubleValue(int score) {
        return doubleValues.get(score);
    }

    private String getTripleValue(int score) {
        return tripleValues.get(score);
    }

    private boolean isEven(int score) {
        return score % 2 == 0;
    }

    /*
     * Exponent of 2, plus round off value, when score is under 40 and an odd number
     */
    private ArrayList<String> calculateExponentPlusRoundOff(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        int roundOffValue = 0;
        for(int i = 5; i > 0; i--) {
            if(score > (Math.pow(2,i))) {
                roundOffValue = score - (int)Math.pow(2,i);
                tempList.add(String.valueOf(roundOffValue));
                tempList.add(getDoubleValue((int)(Math.pow(2,i))));
                return tempList;
            }
        }
        tempList.add("Could not find match in method calculateExponentPlusRoundOff");
        return tempList;
    }
    /*
     * Double value up to D20, plus round off value up to 20, when score is between 52 and 60
     */
    private ArrayList<String> calculateDoublePlusRoundOff(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        int roundOffValue = 0;
        for(int i = 20; i > 0; i--) {
            if(score > 2*i) {
                roundOffValue = score - 2*i;
                tempList.add(String.valueOf(roundOffValue));
                tempList.add(getDoubleValue(2*i));
                return tempList;
            }
        }
        tempList.add("Could not find match in method calculateDoublePlusRoundOff");
        return tempList;
    }

    private ArrayList<String> calculateTriplePlusRoundOff(int score, boolean needEven) {
        ArrayList<String> tempList = new ArrayList<String>();
        int roundOffValue = 0;
        for(int i = 20; i > 0; i--) {
            if(score > 3*i && !isEven(3*i) && !needEven) {
                roundOffValue = score - 3*i;
                tempList.add(getTripleValue(3*i));
                tempList.add(getDoubleValue(roundOffValue));
                return tempList;
            } else if(score > 3*i && isEven(3*i) && needEven) {
                roundOffValue = score - 3*i;
                tempList.add(getTripleValue(3*i));
                tempList.add(getDoubleValue(roundOffValue));
                return tempList;
            }
        }
        tempList.add("Could not find match in method calculateTriplePlusRoundOff");
        return tempList;
    }

    private ArrayList<String> calculate0To40(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        if(isEven(score)) {
            tempList.add(getDoubleValue(score));
        } else {
            tempList.addAll(calculateExponentPlusRoundOff(score));
        }
        return tempList;
    }

    private ArrayList<String> calculate40To52(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        tempList.addAll(calculateExponentPlusRoundOff(score));
        return tempList;
    }

    private ArrayList<String> calculate52To60(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        tempList.addAll(calculateDoublePlusRoundOff(score));
        return tempList;
    }

    private ArrayList<String> calculate60To80(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        if(isEven(score)) {
            tempList.add(getDoubleValue(40));
            tempList.add(getDoubleValue(score-40));
        } else {
            tempList.addAll(calculateTriplePlusRoundOff(score, false));
        }
        return tempList;
    }

    private ArrayList<String> calculate80To98(int score) {
        ArrayList<String> tempList = new ArrayList<String>();
        if(isEven(score)) {
            tempList.addAll(calculateTriplePlusRoundOff(score, true));
        } else {
            tempList.addAll(calculateTriplePlusRoundOff(score, false));
        }
        return tempList;
    }

}
