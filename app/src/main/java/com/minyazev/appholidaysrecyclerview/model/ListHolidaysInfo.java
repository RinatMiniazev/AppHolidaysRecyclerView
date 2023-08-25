package com.minyazev.appholidaysrecyclerview.model;

public class ListHolidaysInfo {
    public HolidayInfo[] listHolidaysInfo;

    public ListHolidaysInfo(int size) {
        listHolidaysInfo = new HolidayInfo[size];
    }

    public void addHoliday (String name, String date, String desc, int id){
        HolidayInfo holiday = new HolidayInfo(name, date, desc);
        listHolidaysInfo[id] = holiday;
    }
}
