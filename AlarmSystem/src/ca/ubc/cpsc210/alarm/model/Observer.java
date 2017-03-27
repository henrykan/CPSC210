package ca.ubc.cpsc210.alarm.model;

/**
 * Represents an observer in the Observer Design Pattern\
 */
public interface Observer {
    void update(boolean isRinging);
}
