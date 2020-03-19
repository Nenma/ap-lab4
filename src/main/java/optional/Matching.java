package optional;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class simulating a set of (resident, hospital) pairs, so a set of Element
 */
public class Matching {
    private Set<Element> elements;

    public Matching() {
        elements = new LinkedHashSet<>();
    }

    public Matching(Set<Element> pairs) {
        this.elements = pairs;
    }

    public void add(Element element) {
        elements.add(element);
    }

    public Set<Element> getElements() {
        return elements;
    }
}
