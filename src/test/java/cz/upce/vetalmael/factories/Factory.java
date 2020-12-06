package cz.upce.vetalmael.factories;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private static int idAnimal = 1;
    private static int idReport = 1;
    private static int idUser = 1;
    private static int idMessage = 1;
    private static int idClinic = 1;
    private static int idClinicMedicine = 1;
    private static int idCreateClinicConsumable = 1;


    public static Animal createAnimal() {
        return new Animal(idAnimal++,"Micka" + idAnimal, null,null);
    }

    public static List<Animal> createAnimals(){
        return new ArrayList<>(){
            {
                add(Factory.createAnimal());
                add(Factory.createAnimal());
            }
        };
    }

    public static AnimalDto createAnimalDto() {
        return new AnimalDto("Micka" + idAnimal);
    }

    public static Report createReport() {
        return new Report(idReport++,"textDescription","textDiagnostisis","textRecommendation",null,null,null,null,null,null,null,null);
    }

    public static List<Report> createReports(){
        return new ArrayList<>(){
            {
                add(Factory.createReport());
                add(Factory.createReport());
            }
        };
    }

    public static User createUser() {
        return new User(idUser++,"user" + idUser + "@email.com", "username","fullName","password","role",null,null,null,null,null);
    }

    public static Message createMessage() {
        return new Message(idMessage++,"message text",null,null,null);
    }

    public static List<Message> createMessages(){
        return new ArrayList<>(){
            {
                add(Factory.createMessage());
                add(Factory.createMessage());
            }
        };
    }

    public static Clinic createClinic() {
        return new Clinic(idClinic++,"name"+idClinic,"adress", null,null,null,null);
    }

    public static List<Clinic> createClinics(){
        return new ArrayList<>(){
            {
                add(Factory.createClinic());
                add(Factory.createClinic());
            }
        };
    }

    public static ClinicDto createClinicDto() {
        return new ClinicDto("name", null);
    }

    public static ClinicMedicine createClinicMedicine(){
        return new ClinicMedicine(idClinicMedicine++, (int)(Math.random() * 11),null,null);
    }

    public static ClinicMedicineDto createClinicMedicineDto(){
        return new ClinicMedicineDto((int)(Math.random() * 11));
    }

    public static ClinicConsumable createClinicConsumable(){
        return new ClinicConsumable(idCreateClinicConsumable++, (int)(Math.random() * 11),null,null);
    }

    public static List<ClinicConsumable> createClinicConsumables(){
        return new ArrayList<>(){
            {
                add(Factory.createClinicConsumable());
                add(Factory.createClinicConsumable());
            }
        };
    }

    public static List<ClinicMedicine> createClinicMedicines(){
        return new ArrayList<>(){
            {
                add(Factory.createClinicMedicine());
                add(Factory.createClinicMedicine());
            }
        };
    }




}
