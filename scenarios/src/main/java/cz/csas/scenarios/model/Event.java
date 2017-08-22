package cz.csas.scenarios.model;

import java.util.Date;

import cz.csas.scenarios.Utils;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class Event {
    private int eventTypeId;
    private EventType eventType;
    private int applicationId;
    private String application;
    private String eventCreation;
    private String clientId;
    private Values values;

    public Event(int eventTypeId, EventType eventType, int applicationId, String application, Date eventCreation, String clientId, Values values) {
        this.eventTypeId = eventTypeId;
        this.eventType = eventType;
        this.applicationId = applicationId;
        this.application = application;
        this.eventCreation = Utils.getISO8601String(eventCreation);
        this.clientId = clientId;
        this.values = values;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Date getEventCreation() {
        return Utils.getISO8601Date(eventCreation);
    }

    public void setEventCreation(Date eventCreation) {
        this.eventCreation = Utils.getISO8601String(eventCreation);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }
}
