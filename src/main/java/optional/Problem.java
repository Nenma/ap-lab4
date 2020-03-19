package optional;

import ro.uaic.info.pa.Hospital;
import ro.uaic.info.pa.Resident;

import java.util.List;
import java.util.Map;

public class Problem {
    private Matching matching;
    private Map<Resident, List<Hospital>> residentPreferences;
    private Map<Hospital, List<Resident>> hospitalPreferences;

    public Problem(Map<Resident, List<Hospital>> residentPreferences, Map<Hospital, List<Resident>> hospitalPreferences) {
        this.residentPreferences = residentPreferences;
        this.hospitalPreferences = hospitalPreferences;
        matching = new Matching();
    }

    /**
     * Assigns a resident to a hospital as long as it does not exceed the capacity, and in the order they appear in the map
     */
    public void createMatching() {
        for (Resident r : residentPreferences.keySet()) {
            boolean alreadyAssigned = false;
            for (Hospital h : hospitalPreferences.keySet()) {
                if (h.getCapacity() > 0 && !alreadyAssigned) {
                    matching.add(new Element(r, h));
                    h.setCapacity(h.getCapacity() - 1);
                    alreadyAssigned = true;
                }
            }
        }
    }

    /**
     * Checks whether there is another hospital which a resident prefers over the one it is assigned to, and that hospital still has capacity
     * @return whether the matching created is SOLID
     */
    public boolean hasSolidMatching() {
        for (Element e : matching.getElements()) {
            for (Hospital h : hospitalPreferences.keySet()) {
                if (residentPreferences.get(e.getResident()).indexOf(h) < residentPreferences.get(e.getResident()).indexOf(e.getHospital())
                    && h.getCapacity() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printMatching() {
        int i = 1;
        for (Element e : matching.getElements()) {
            System.out.print((i++) + ". ");
            System.out.println(e);
        }
    }

    public Matching getMatching() {
        return matching;
    }

    public Map<Resident, List<Hospital>> getResidentPreferences() {
        return residentPreferences;
    }

    public Map<Hospital, List<Resident>> getHospitalPreferences() {
        return hospitalPreferences;
    }
}
