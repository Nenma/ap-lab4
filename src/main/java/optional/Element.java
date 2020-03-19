package optional;

import ro.uaic.info.pa.Hospital;
import ro.uaic.info.pa.Resident;

/**
 * Class simulating a (resident, hospital) pair
 */
public class Element {
    private Resident resident;
    private Hospital hospital;

    public Element(Resident resident, Hospital hospital) {
        this.resident = resident;
        this.hospital = hospital;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "(" + resident.getName() + ", " + hospital.getName() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        Element other = (Element) obj;
        return resident.equals(other.getResident()) && hospital.equals(other.getHospital());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + resident.getName().hashCode();
        result = prime * result + hospital.getName().hashCode();
        return result;
    }
}
