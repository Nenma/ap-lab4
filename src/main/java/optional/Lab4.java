package optional;

import com.github.javafaker.Faker;
import ro.uaic.info.pa.Hospital;
import ro.uaic.info.pa.Resident;

import java.util.*;
import java.util.stream.Collectors;

public class Lab4 {
    public static void main(String[] args) {
        List<Resident> residents = generateResidents();
        Set<Hospital> hospitals = generateHospitals();

        System.out.println(residents + " " + residents.size());
        System.out.println(hospitals + " " + hospitals.size());
        System.out.println();

        Map<Resident, List<Hospital>> residentPreferences = createResidentsMap(residents, hospitals);
        printResidentsMap(residentPreferences);

        System.out.println();

        Map<Hospital, List<Resident>> hospitalPreferences = createHospitalMap(hospitals, residents);
        printHospitalsMap(hospitalPreferences);

        System.out.println();

        Problem hr = new Problem(residentPreferences, hospitalPreferences);
        hr.createMatching();
        hr.printMatching();
        System.out.println("Is the matching solid? " + (hr.hasSolidMatching() ? "Yes" : "No"));
    }

    private static void printResidentsMap(Map<Resident, List<Hospital>> map) {
        int i = 0;
        for (Map.Entry<Resident, List<Hospital>> entry : map.entrySet()) {
            System.out.println("[R" + (i++) + "] " + entry.getKey() + " prefers: ");
            for (Hospital h : entry.getValue()) {
                System.out.println("\t- " + h.getName());
            }
        }
    }

    private static void printHospitalsMap(Map<Hospital, List<Resident>> map) {
        int i = 0;
        for (Map.Entry<Hospital, List<Resident>> entry : map.entrySet()) {
            System.out.println("[H" + (i++) + "](" + entry.getKey().getCapacity() + ") " + entry.getKey() + " is looking for: ");
            for (Resident r : entry.getValue()) {
                System.out.println("\t- " + r.getName());
            }
        }
    }

    /**
     * Generate a random list of between 5 and 40 residents
     * @return the list of random residents
     */
    private static List<Resident> generateResidents() {
        Random rand = new Random();
        Faker faker = new Faker();

        List<String> names = new ArrayList<>();
        int numberOfResidents = rand.nextInt(36) + 5;
        for (int i = 0; i < numberOfResidents; ++i) {
            names.add(faker.name().fullName());
        }
        return names.stream().map(Resident::new).sorted(Comparator.comparing(Resident::getName)).collect(Collectors.toList());
    }

    /**
     * Generate a random set of between 5 and 20 hospitals, each with a random capacity between 1 and 10
     * @return the set of random hospitals
     */
    private static Set<Hospital> generateHospitals() {
        Random rand = new Random();
        Faker faker = new Faker();

        Set<Hospital> hospitals = new TreeSet<>();
        int numberOfHospitals = rand.nextInt(16) + 5;
        for (int i = 0; i < numberOfHospitals; ++i) {
            hospitals.add(new Hospital(faker.medical().hospitalName(), rand.nextInt(10) + 1));
        }
        return hospitals;
    }

    /**
     * Create a random map of residents' hospital preferences using o coin toss to decide whether a resident prefers a certain hospital
     * @param residents the list of residents for which the hospital preferences are generated
     * @param hospitals the set of hospitals to choose from
     * @return a random map of hospital preferences
     */
    private static Map<Resident, List<Hospital>> createResidentsMap(List<Resident> residents, Set<Hospital> hospitals) {
        Random rand = new Random();

        Map<Resident, List<Hospital>> map = new LinkedHashMap<>();
        for (Resident r : residents) {
            List<Hospital> list = new ArrayList<>();
            for (Hospital h : hospitals) {
                if (rand.nextDouble() <= 0.5) {
                    list.add(h);
                }
            }
            map.put(r, list);
        }

        return map;
    }

    /**
     * Create a random map of hospitals' residents preferences using a coin toss to decide whether a hospital prefers a certain resident
     * @param hospitals the set of hospitals for which the resident preferences are generated
     * @param residents the list of residents to choose from
     * @return a random map of resident preferences
     */
    private static Map<Hospital, List<Resident>> createHospitalMap(Set<Hospital> hospitals, List<Resident> residents) {
        Random rand = new Random();

        Map<Hospital, List<Resident>> map = new LinkedHashMap<>();
        for (Hospital h : hospitals) {
            List<Resident> list = new ArrayList<>();
            for (Resident r : residents) {
                if (rand.nextDouble() <= 0.5) {
                    list.add(r);
                }
            }
            map.put(h, list);
        }

        return map;
    }
}
