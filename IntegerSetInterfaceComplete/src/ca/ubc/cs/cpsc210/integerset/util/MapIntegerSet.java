package ca.ubc.cs.cpsc210.integerset.util;

import java.util.HashMap;

// Represents a set of integers
public class MapIntegerSet implements IntegerSet {
    private HashMap<Integer, Boolean> data;

    // EFFECTS: constructs an empty set of integers
    public MapIntegerSet() {
        data = new HashMap<Integer, Boolean>();
    }

    @Override
    public void insert(Integer i) {
        if (!data.containsKey(i)) {
            data.put(i, false);
        }
    }

    @Override
    public void remove(Integer i) {
        data.remove(i);
    }

    @Override
    public boolean contains(Integer i) {
        return data.containsKey(i);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public IntegerSet intersection(IntegerSet other) {
        IntegerSet intersection = new MapIntegerSet();

        for (Integer i : data.keySet()) {
            if (other.contains(i)) {
                intersection.insert(i);
            }
        }

        return intersection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;

        IntegerSet that = (IntegerSet) o;

        if (size() != that.size()) return false;

        for (Integer i : data.keySet()) {
            if (!that.contains(i))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
