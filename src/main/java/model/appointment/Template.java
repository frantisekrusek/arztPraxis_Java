package model.appointment;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;

public class Template {
    private DayOfWeek weekday;
    private LocalTime startTime;
    //current appointments are to be generated from it?
    private boolean active;
    private int id;
    //are multiple appointments for the same time slot possible?
    //public enum Slot{One, Two}

    //ctr
    public Template(){}

    public Template(DayOfWeek weekday, LocalTime startTime){
        this.weekday = weekday;
        this.startTime = startTime;
        active = false;
    }

    public Template(DayOfWeek weekday, LocalTime startTime, int id){
        this.weekday = weekday;
        this.startTime = startTime;
        this.id = id;
        active = false;
    }

    public Template(DayOfWeek weekday, LocalTime startTime, int id, boolean active){
        this.weekday = weekday;
        this.startTime = startTime;
        this.id = id;
        this.active = active;
    }
    public Template(DayOfWeek weekday, LocalTime startTime, boolean active){
        this.weekday = weekday;
        this.startTime = startTime;
        this.active = active;
    }
    @Override
    public String toString(){
        return weekday.toString() + " " + startTime.toString() +
                " " + id + " active: " + active;
    }

//    @Override
//    public int compare(Template o1, Template o2) {
//        if (o1.getStartTime().isBefore(o2.getStartTime())){
//            return -1;
//        }else if (o1.getStartTime().equals(o2.getStartTime())){
//            return 0;
//        }else{
//            return 1;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (active != template.active) return false;
        if (id != template.id) return false;
        if (weekday != template.weekday) return false;
        return startTime != null ? startTime.equals(template.startTime) : template.startTime == null;
    }

    @Override
    public int hashCode() {
        int result = weekday != null ? weekday.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + id;
        return result;
    }

    //GETTER, SETTER
    public DayOfWeek getWeekday() {
        return weekday;
    }

    public void setWeekday(DayOfWeek weekday) {
        this.weekday = weekday;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}