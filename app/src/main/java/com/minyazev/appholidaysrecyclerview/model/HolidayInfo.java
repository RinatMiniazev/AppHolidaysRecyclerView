package com.minyazev.appholidaysrecyclerview.model;

public class HolidayInfo {
    int id;
    String holiday_name;
    String holiday_date;
    String holiday_desc;

    public HolidayInfo(String name, String date, String desc){
        this.holiday_date = date;
        this.holiday_desc = desc;
        this.holiday_name = name;
    }
    public String getHoliday_date() {
        return holiday_date;
    }

    public String getHoliday_desc() {
        return holiday_desc;
    }

    public String getHoliday_name() {
        return holiday_name;
    }
}
