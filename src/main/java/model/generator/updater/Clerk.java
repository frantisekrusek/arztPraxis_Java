package model.generator.updater;

import model.appointment.Appointment;
import model.appointment.Template;
import model.generator.Generator;
import model.generator.Supervisor;
import model.office.Office;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Set;

/*Clerk (Updater ?) is responsible for creating the correct amount of future appointments. */
public class Clerk extends Generator {

    private int weeks;
    private LocalTime lastTemplateOfDay = LocalTime.MIDNIGHT;

    //ctr
    public Clerk(Office office, int weeks) {
        super(office);

        this.weeks = weeks;
    }

    /* "Startmethode"
    can be used to initialize whole schedule.
    @param templatesArr most likely has 7 Sets of templates, one for each weekday.
     */
    public void unleashTemplates(Set<Template>[] templatesArr){
        for (Set<Template> templateSet:templatesArr) {
            unleashTemplates(templateSet);
        }
    }//end unleashTemplates()

    /* Hilfsmethode
    to be used as helper-method to initialize whole schedule or to
    activate a group of templates.
    e.g. generate appointments for all Mondays of the next ... weeks.
    Algo:
    Iterates through templates.
    Checks if templates are active.
    Creates f(weeks) number of appointments from each template.
    Writes appointments into repository. todo
     */
    public void unleashTemplates(Set<Template> templates){
        Set<Appointment> appointments = super.getOffice().getAppointments();
        for (Template template:templates) {
            if (template.isActive()){
                appointments.addAll(super.generateAppsFromSingleTemplate(template, this.weeks, Instant.now()));
                //repo
            }
        }
    }//end unleashTemplates()


    /* "24h Method":
    creates one appointment per template for the following date:
     'lastUpdate' + 1 day + weeks x 7 days (depending on @param weeks, e.g. 1+2x7 ).
     @param templateArray should be Set<Template>[] templates from office.*/
    public Set<Appointment> generateAppsOfDay(Instant lastUpdate, Set<Template>[] templateArray){
        Set<Appointment> appointments = new LinkedHashSet<>(); //because it is ordered
        ZonedDateTime zonedLastUpdate = lastUpdate.atZone(super.getOffice().getOffice_zoneId());
        lastTemplateOfDay = LocalTime.MIDNIGHT; //entfernen?

        int weekday = this.findWeekdayFollowingLastUpdate(zonedLastUpdate);

        for(Template template : templateArray[weekday]){
            if (template.isActive()){
                LocalDate dateOfApp = zonedLastUpdate.toLocalDate().plusDays(1+(weeks*7));
                LocalTime timeOfApp = template.getStartTime();
                this.lastTemplateOfDay = this.findLastTemplate(timeOfApp);//entfernen?
                ZonedDateTime datetimeOfApp = ZonedDateTime.of(dateOfApp, timeOfApp, super.getOffice().getOffice_zoneId());
                String name = datetimeOfApp.format(DateTimeFormatter.ofPattern("HH:mm, EEEE dd.MM.uuuu"));
                appointments.add(new Appointment(name, datetimeOfApp, false));
                System.out.println("LOG: appointment created:\nname: " + name);
            }
        }

        this.moveCursorOfLastUpdatedTemplate(zonedLastUpdate);

        return appointments;
    }//end generateAppsofDay()

    //Helper method
    //entfernen?
    public LocalTime findLastTemplate(LocalTime timeOfApp){

        if (timeOfApp.isAfter(lastTemplateOfDay)){lastTemplateOfDay = timeOfApp;}
        return lastTemplateOfDay;
    }

    //Helper method
    //change lastUpdate to (lastUpdatedTemplate + 1 day).
    public void moveCursorOfLastUpdatedTemplate(ZonedDateTime zonedLastUpdate){
        ZonedDateTime newZDT = zonedLastUpdate.plus(1, ChronoUnit.DAYS).
                with(LocalTime.MIN);
        Supervisor.getInstance().setLastUpdate(newZDT.toInstant());
    }
    //Helper method
    public int findWeekdayFollowingLastUpdate(ZonedDateTime zonedLastUpdate){
        int weekday;
        weekday = zonedLastUpdate.plusDays(1).getDayOfWeek().getValue();
        if(weekday == 7){weekday = 0;}
        return weekday;
    }

    /* todo:
     Methode catchUp:
     generateAppsOfDay() wird so oft wiederholt bis das letzte Template
     des gestrigen Tages ausgelöst wurde.
     Aufruf der Methode täglich um 00:00h (LocalTime.MIN)
     Algo:

     Instant.now() muss Parameter sein, um Methode testbar zu machen.
     call generateAppsOfDay()
     wenn
     BEDINGUNG: lastUpdatedTemplate.getLocalDate() == Instant.now.toLocalDate().minusDays(1);
      dann stop.
     */
    public Set<Appointment> catchUp(Instant today, Instant lastUpdate, Set<Template>[] templateArr){
        //NEU:
        if(today.minus(24, ChronoUnit.DAYS).isAfter(lastUpdate)){
            ZonedDateTime newDay = getOffice().lastUpdateToZDT();
            generateAppsOfDay(lastUpdate, templateArr);
            moveCursorOfLastUpdatedTemplate(newDay);
        }
        //ALT:
        Instant now = today;
        LocalDate lDnow = ZonedDateTime.ofInstant(now, super.getOffice().getOffice_zoneId()).toLocalDate();
        LocalTime lTnow = ZonedDateTime.ofInstant(now, super.getOffice().getOffice_zoneId()).toLocalTime();
        Set<Appointment> setOfApps = new LinkedHashSet<>();

        if (lastUpdate == null){
            lastUpdate = Instant.MIN;
        }
        LocalDate lDLastUpdate = LocalDate.ofInstant(lastUpdate, super.getOffice().getOffice_zoneId());

        // ALLES NEU MACHEN:
        if (lastUpdate.isBefore(now)){
            if (lTnow.isBefore(LocalTime.of(23, 59, 59))){
                System.out.println("LOG: if-Zweig");
                while ( ! lDnow.minusDays(1).equals(lDLastUpdate)) {
                    //CORE
                    setOfApps.addAll(generateAppsOfDay(lastUpdate, templateArr));
                    lDLastUpdate = LocalDate.ofInstant(Supervisor.getInstance().getLastUpdate(), super.getOffice().getOffice_zoneId());
                }
            }else{
                System.out.println("LOG: else-Zweig");
                while ( ! lDnow.equals(lDLastUpdate)){
                    System.out.println("LOG: Anfang Schleife, lDlastUpdate: " + lDLastUpdate);
                    //CORE
                    System.out.println("LOG: Durchlauf while-Schleife");
                    setOfApps.addAll(generateAppsOfDay(lastUpdate, templateArr));
                    lastUpdate = Supervisor.getInstance().getLastUpdate();
                    lDLastUpdate = LocalDate.ofInstant(lastUpdate, super.getOffice().getOffice_zoneId());
                    System.out.println("LOG: lDlastUpdate: " + lastUpdate);
                    System.out.println("LOG: Ende Schleife, lDlastUpdate: " + lDLastUpdate);
                }
            }
        }
        return setOfApps;
    }
    //END catchUp



    //GETTER, SETTER
    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public LocalTime getLastTemplateOfDay() {
        return lastTemplateOfDay;
    }

    //public Office getOffice() {        return office;    }

}//end class
