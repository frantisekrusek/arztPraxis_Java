package start;

import database.MySQL_repo;
import model.appointment.Template;
import model.generator.Supervisor;
import model.generator.updater.Clerk;
import model.office.Office;
import model.person.officeManager.OfficeManager;

import java.time.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Start {
    public static void main(String[] args) {

        Office office = new Office();
        //Simulation 1: erste Inbetriebnahme. Einige Templates werden erstellt. Ab (inkl) heute
        //sollen daraus für 3 Wochen im Voraus Termine erzeugt werden.
        Clerk clerk = (Clerk)office.getGenerator();
        Template activeTemplate_Mon_00_00, activeTemplate_Mon_08_00, activeTemplate_Mon_08_15,
                activeTemplate_Tue_11_00, activeTemplate_Tue_12_00,
                activeTemplate_Wed_11_00,
                activeTemplate_Tue_23_00,
                activeTemplate_Thu_11_00,
                activeTemplate_Fri_11_00,
                activeTemplate_Sat_11_00,
                activeTemplate_Sun_07_00, inactiveTemplate;
        activeTemplate_Mon_00_00 = new Template(DayOfWeek.MONDAY, LocalTime.MIDNIGHT); activeTemplate_Mon_00_00.setActive(true);
        activeTemplate_Mon_08_00 = new Template(DayOfWeek.MONDAY, LocalTime.of(8,00)); activeTemplate_Mon_08_00.setActive(true);
        activeTemplate_Mon_08_15 = new Template(DayOfWeek.MONDAY, LocalTime.of(8,15)); activeTemplate_Mon_08_15.setActive(true);
        activeTemplate_Tue_11_00 = new Template(DayOfWeek.TUESDAY, LocalTime.of(11,00)); activeTemplate_Tue_11_00.setActive(true);
        activeTemplate_Tue_12_00 = new Template(DayOfWeek.TUESDAY, LocalTime.of(12,00)); activeTemplate_Tue_12_00.setActive(true);
        activeTemplate_Tue_23_00 = new Template(DayOfWeek.TUESDAY, LocalTime.of(23,00)); activeTemplate_Tue_23_00.setActive(true);
        activeTemplate_Wed_11_00 = new Template(DayOfWeek.WEDNESDAY, LocalTime.of(11,00)); activeTemplate_Wed_11_00.setActive(true);
        activeTemplate_Thu_11_00 = new Template(DayOfWeek.THURSDAY, LocalTime.of(11,00)); activeTemplate_Thu_11_00.setActive(true);
        activeTemplate_Fri_11_00 = new Template(DayOfWeek.FRIDAY, LocalTime.of(11,00)); activeTemplate_Fri_11_00.setActive(true);
        activeTemplate_Sat_11_00 = new Template(DayOfWeek.SATURDAY, LocalTime.of(11,00)); activeTemplate_Sat_11_00.setActive(true);
        activeTemplate_Sun_07_00 = new Template(DayOfWeek.SUNDAY, LocalTime.of(7,00)); activeTemplate_Sun_07_00.setActive(true);
        inactiveTemplate = new Template(DayOfWeek.WEDNESDAY, LocalTime.NOON);
        Set[] newTemplates = new Set[]{
                new HashSet<Template>(Arrays.asList(activeTemplate_Sun_07_00)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Mon_00_00, activeTemplate_Mon_08_00, activeTemplate_Mon_08_15)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Tue_11_00, activeTemplate_Tue_12_00, activeTemplate_Tue_23_00)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Wed_11_00, inactiveTemplate)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Thu_11_00)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Fri_11_00)),
                new HashSet<Template>(Arrays.asList(activeTemplate_Sat_11_00))};

        office.mergeTemplates(newTemplates);
        clerk.setWeeks(3);
        clerk.unleashTemplates(office.getTemplates());
        //Ergebnisse
        System.out.println(clerk.sortAppointments());
        //Ende Sim 1

        //Simulation2: System wird nach einigen Wochen oder Tagen Auszeit neu gestartet:
        //clerk.catchUp(Instant.now(), Supervisor.getInstance().getLastUpdate(), )



//        //lastUpdate in die Datenbank
//        Office office = new Office();
//        OfficeManager officeManager = new OfficeManager(office);
//
//        //Bei Start: lastUpdate aus Datenbank holen
//        MySQL_repo mySQL_repo = new MySQL_repo(officeManager, office);
//        mySQL_repo.initialize_Or_GetLastUpdateFromRepo();
//        System.out.println(Supervisor.getInstance().getLastUpdate());
//
//        //mySQL_repo.getTemplatesFromRepo();
//
//
//        Template template1 = officeManager.createTemplate(DayOfWeek.SUNDAY, LocalTime.of(6, 0));
//        Template template2 = officeManager.createTemplate(DayOfWeek.WEDNESDAY, LocalTime.of(7, 0));
//        Template template3 = officeManager.createTemplate(DayOfWeek.WEDNESDAY, LocalTime.of(11, 40));
//        Template template4 = officeManager.createTemplate(DayOfWeek.WEDNESDAY, LocalTime.of(7, 42));
//        //richtig soll sein: temp2-temp4-temp3
//        Set<Template> templateSet = Set.of(template2, template3, template4, template1);
//        //(i, j) -> i.compareTo(j)
//        Optional<Template> templateOptional = templateSet.stream().min((t1, t2) -> {
//                    if (t1.getStartTime().isBefore(t2.getStartTime())) {
//                        return -1;
//                    } else if (t1.getStartTime().equals(t2.getStartTime())) {
//                        return 0;
//                    } else {
//                        return 1;
//                    }
//                });
//        Template result = templateOptional.get();
//        System.out.println(result.toString());
        //Wunschergebnis temp2 = wednesday 7:00h


        //Supervisor.getInstance().setLastUpdate(LocalDateTime.now());
//        Clerk clerk = new Clerk();
//        clerk.generateAppointmentsFromTemplate(template2, 1 ,Instant.from(ZonedDateTime.now()));
//
//        clerk.generateAppointment(template1);



        //Scanner scanner = new Scanner(System.in);

        //System.out.print("weiter mit einer Taste..\n");
        //scanner.nextLine();
        //new Generator().generateAppointmentsFromTemplate(template1, 4, LocalDate.now());
        //new Generator().generateAppointmentsFromTemplate(template2,4, LocalDate.now());

//        System.out.print("First name: ");
//        String first_name;
//        Scanner scanner = new Scanner(System.in);
//        first_name = scanner.nextLine();
//        System.out.print("Surname: ");
//        String surname = scanner.nextLine();
//        System.out.print("Phone number: ");
//        String phone_number = scanner.nextLine();
//        scanner.close();
//
//        Patient patient1 = new Patient(phone_number, first_name, surname);
//        patient1.makeAppointment(new Appointment());
    }//end main
}
