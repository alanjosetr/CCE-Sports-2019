package org.codecse.ccesports2019;

public class ResultData {
    String EName;
    String First;
    String Second;
    String Third;

    public ResultData() {
    }

    public ResultData(String name, String first, String second, String third) {
        EName = name;
        First = first;
        Second = second;
        Third = third;
    }

    public String getEName() {
        return EName;
    }

    public void setName(String name) {
        EName = name;
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getSecond() {
        return Second;
    }

    public void setSecond(String second) {
        Second = second;
    }

    public String getThird() {
        return Third;
    }

    public void setThird(String third) {
        Third = third;
    }
}
