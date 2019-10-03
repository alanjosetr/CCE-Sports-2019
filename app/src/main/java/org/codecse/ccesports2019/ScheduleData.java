package org.codecse.ccesports2019;

public class ScheduleData {
    private String Name;
    private String Status;
    private String Time;

    public ScheduleData() {
    }

    public ScheduleData(String name, String status, String time) {
        Name = name;
        Status = status;
        Time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
