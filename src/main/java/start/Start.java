package start;

import database.MySQL_repo;
import database.OfficeQueries;
import model.appointment.Template;
import model.generator.Generator;
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


        new OfficeQueries().getTemplates_ByWeekday(DayOfWeek.SUNDAY);
        //PROGRAMMSTARTER:
//        Office office = new Office(new Clerk(), new OfficeManager());
//        Clerk clerk = (Clerk)office.getGenerator();
//        clerk.catchUp(Instant.now(), office.getTemplates());


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
