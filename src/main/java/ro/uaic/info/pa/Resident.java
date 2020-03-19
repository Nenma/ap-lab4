package ro.uaic.info.pa;

import java.util.ArrayList;
import java.util.List;

public class Resident {
    private String name;
    private Hospital assignedHospital;
    private List<Hospital> preferredHospitals;

    public Resident(String name) {
        this.name = name;
        preferredHospitals = new ArrayList<>();
    }

    public Resident(String name, List<Hospital> preferredHospitals) {
        this.name = name;
        this.preferredHospitals = preferredHospitals;
    }

    public void addPreferredHospital(Hospital hospital) {
        preferredHospitals.add(hospital);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hospital getAssignedHospital() {
        return assignedHospital;
    }

    public void setAssignedHospital(Hospital assignedHospital) {
        this.assignedHospital = assignedHospital;
    }

    public List<Hospital> getPreferredHospitals() {
        return preferredHospitals;
    }

    public void setPreferredHospitals(List<Hospital> preferredHospitals) {
        this.preferredHospitals = preferredHospitals;
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
        if (!(obj instanceof Resident)) {
            return false;
        }
        Resident other = (Resident) obj;
        return name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }
}
