package ro.uaic.info.pa;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital> {
    private String name;
    private int capacity;
    private List<Resident> preferredResidents;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        preferredResidents = new ArrayList<>();
    }

    public Hospital(String name, int capacity, List<Resident> preferredResidents) {
        this.name = name;
        this.capacity = capacity;
        this.preferredResidents = preferredResidents;
    }

    public void addPreferredResident(Resident resident) {
        preferredResidents.add(resident);
    }

    @Override
    public int compareTo(Hospital o) {
        return name.compareTo(o.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Resident> getPreferredResidents() {
        return preferredResidents;
    }

    public void setPreferredResidents(List<Resident> preferredResidents) {
        this.preferredResidents = preferredResidents;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) obj;
        return name.equals(((Hospital) obj).getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }
}
