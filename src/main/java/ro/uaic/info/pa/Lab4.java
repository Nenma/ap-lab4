package ro.uaic.info.pa;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Lab4 {
    public static void main(String[] args) {
        Faker faker = new Faker();

        List<String> residentNames = Arrays.asList(faker.name().fullName(), faker.name().fullName(), faker.name().fullName(), faker.name().fullName());
        List<Resident> residents = residentNames.stream().map(Resident::new).sorted(Comparator.comparing(Resident::getName)).collect(Collectors.toList());

        Hospital h0 = new Hospital(faker.medical().hospitalName(), 1);
        Hospital h1 = new Hospital(faker.medical().hospitalName(), 2);
        Hospital h2 = new Hospital(faker.medical().hospitalName(), 2);

        Set<Hospital> hospitals = new TreeSet<>(Arrays.asList(h0, h1, h2));

        Map<Resident, List<Hospital>> residentPreferences = new LinkedHashMap<>();
        residentPreferences.put(residents.get(0), Arrays.asList(h0, h1, h2));
        residentPreferences.put(residents.get(1), Arrays.asList(h0, h1, h2));
        residentPreferences.put(residents.get(2), Arrays.asList(h0, h1));
        residentPreferences.put(residents.get(3), Arrays.asList(h0, h2));

        printResidentsMap(residentPreferences);

        System.out.println();

        Map<Hospital, List<Resident>> hospitalPreferences = new TreeMap<>();
        hospitalPreferences.put(h0, Arrays.asList(residents.get(3), residents.get(0), residents.get(1), residents.get(2)));
        hospitalPreferences.put(h1, Arrays.asList(residents.get(0), residents.get(2), residents.get(1)));
        hospitalPreferences.put(h2, Arrays.asList(residents.get(0), residents.get(1), residents.get(3)));

        printHospitalsMap(hospitalPreferences);

        System.out.println();

        List<Resident> residentsThatPreferH0AndH2 = residents.stream()
                .filter(resident -> residentPreferences.get(resident).containsAll(Arrays.asList(h0, h2)))
                .collect(Collectors.toList());
        System.out.println("Residents that prefer H0 and H2: " + residentsThatPreferH0AndH2);

        List<Hospital> hospitalsThatHaveR0Top = hospitals.stream()
                .filter(hospital -> hospitalPreferences.get(hospital).get(0).equals(residents.get(0)))
                .collect(Collectors.toList());
        System.out.println("Hospitals that have R0 as top resident: " + hospitalsThatHaveR0Top);
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
            System.out.println("[H" + (i++) + "] " + entry.getKey() + " is looking for: ");
            for (Resident r : entry.getValue()) {
                System.out.println("\t- " + r.getName());
            }
        }
    }
}
