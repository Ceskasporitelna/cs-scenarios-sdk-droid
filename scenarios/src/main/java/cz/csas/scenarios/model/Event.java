package cz.csas.scenarios.model;

import java.util.Date;

import cz.csas.scenarios.utils.TimeUtils;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/08/2017
 */

public class Event {

    private int eventTypeId;
    private String eventType;
    private int applicationId;
    private String application;
    private String eventCreation;
    private String clientId;
    private Object values;

    public Event(int eventTypeId, EventType eventType, int applicationId, String application, Date eventCreation, String clientId, Object values) {
        this.eventTypeId = eventTypeId;
        this.eventType = eventType.getValue();
        this.applicationId = applicationId;
        this.application = application;
        this.eventCreation = TimeUtils.getISO8601String(eventCreation);
        this.clientId = clientId;
        this.values = values;
    }

    Event(Builder builder) {
        this.eventTypeId = builder.eventTypeId;
        this.eventType = builder.eventType.getValue();
        this.applicationId = builder.applicationId;
        this.application = builder.application;
        setEventCreation(builder.eventCreation);
        this.clientId = builder.clientId;
        this.values = builder.values;
    }

    /**
     * The event type identifier, eventType is preferred instead.
     *
     * @return event type id
     */
    public int getEventTypeId() {
        return eventTypeId;
    }

    /**
     * The event type, possible values: 'API', 'LoadURI', 'Form', 'Custom'.
     *
     * @return event type
     */
    public EventType getEventType() {
        return EventType.fromValue(eventType);
    }

    /**
     * Application identifier registering the event, application is preferred instead.
     *
     * @return application id
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     * Application registering the event.
     *
     * @return application
     */
    public String getApplication() {
        return application;
    }

    /**
     * Date and time when the event occurred.
     * required
     *
     * @return event creation
     */
    public Date getEventCreation() {
        return TimeUtils.getISO8601Date(eventCreation);
    }

    /**
     * 01-08-10.05.27.123456' (optional, string) - Client identifier if not authenticated in headers.
     *
     * @return client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Additional event information, JSON object or value without fixed structure.
     *
     * @return values
     */
    public Object getValues() {
        return values;
    }

    /**
     * The event type identifier, eventType is preferred instead.
     *
     * @param eventTypeId
     */
    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    /**
     * The event type, possible values: 'API', 'LoadURI', 'Form', 'Custom'.
     *
     * @param eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType.getValue();
    }

    /**
     * Application identifier registering the event, application is preferred instead.
     *
     * @param applicationId
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Application registering the event.
     *
     * @param application
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Date and time when the event occurred.
     * required
     *
     * @param eventCreation
     */
    public void setEventCreation(Date eventCreation) {
        this.eventCreation = TimeUtils.getISO8601String(eventCreation);
    }

    /**
     * 01-08-10.05.27.123456' (optional, string) - Client identifier if not authenticated in headers.
     *
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Additional event information, JSON object or value without fixed structure.
     *
     * @param values
     */
    public void setValues(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventTypeId=" + eventTypeId +
                ", eventType='" + eventType + '\'' +
                ", applicationId=" + applicationId +
                ", application='" + application + '\'' +
                ", eventCreation='" + eventCreation + '\'' +
                ", clientId='" + clientId + '\'' +
                ", values=" + values +
                '}';
    }

    public static class Builder {
        private int eventTypeId;
        private EventType eventType;
        private int applicationId;
        private String application;
        private Date eventCreation;
        private String clientId;
        private Object values;

        /**
         * The event type identifier, eventType is preferred instead.
         *
         * @param eventTypeId
         * @return builder
         */
        public Builder setEventTypeId(int eventTypeId) {
            this.eventTypeId = eventTypeId;
            return this;
        }

        /**
         * The event type, possible values: 'API', 'LoadURI', 'Form', 'Custom'.
         *
         * @param eventType
         * @return builder
         */
        public Builder setEventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * Application identifier registering the event, application is preferred instead.
         *
         * @param applicationId
         * @return builder
         */
        public Builder setApplicationId(int applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        /**
         * Application registering the event.
         *
         * @param application
         * @return builder
         */
        public Builder setApplication(String application) {
            this.application = application;
            return this;
        }

        /**
         * Date and time when the event occurred.
         * required
         *
         * @param eventCreation
         * @return builder
         */
        public Builder setEventCreation(Date eventCreation) {
            this.eventCreation = eventCreation;
            return this;
        }

        /**
         * 01-08-10.05.27.123456' (optional, string) - Client identifier if not authenticated in headers.
         *
         * @param clientId
         * @return builder
         */
        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        /**
         * Additional event information, JSON object or value without fixed structure.
         *
         * @param values
         * @return builder
         */
        public Builder setValues(Object values) {
            this.values = values;
            return this;
        }

        /**
         * Create event from builder
         *
         * @return event
         */
        public Event create() {
            return new Event(this);
        }
    }
}
