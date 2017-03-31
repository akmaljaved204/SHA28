package model;

/**
 * Created by AkmalJaved on 3/31/2017.
 */

public class EventDay {
    private String day;
    private String date;
    private boolean isSelected=false;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
