package model.office;

import model.appointment.Appointment;
import model.appointment.Template;
import model.generator.Supervisor;
import model.person.officeManager.OfficeManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class Office {
    //Each weekdays templates will be held in a Set, up to 7 Sets will be held in this array.
    private final Set<Template>[] templates;
    private final Set<Appointment> appointments;
    private OfficeManager officeManager;
    private ZoneId office_zoneId = ZoneId.of("Europe/Vienna");

    public Office() {
        //0..Sunday, 1 Monday, ... 6 Saturday
        //Why? To match Values of Enum java.time.DayOfWeek as close as possible.
        this.templates = new Set[]{
                new HashSet<Template>(), new HashSet<Template>(),
                new HashSet<Template>(), new HashSet<Template>(),
                new HashSet<Template>(), new HashSet<Template>(),
                new HashSet<Template>()};
        this.appointments = new HashSet<>();
    }

    public LocalDateTime lastUpdateToLDT(){
        return LocalDateTime.ofInstant(Supervisor.getInstance().getLastUpdate(), office_zoneId);
    }

    //GETTER, SETTER
    public Set[] getTemplates() {
        return templates;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public ZoneId getOffice_zoneId() {
        return office_zoneId;
    }

    public void setOffice_zoneId(ZoneId office_zoneId) {
        this.office_zoneId = office_zoneId;
    }
}//end class
