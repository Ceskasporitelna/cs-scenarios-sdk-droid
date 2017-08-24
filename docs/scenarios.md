# Using ScenariosSDK
Scenarios allow you to post an event or collection of events .

## Before You Begin

Before using ScenariosSDK in your application, you need to initialize it by providing it your WebApiKey.

```java

    // Initialize scenarios
    WebApiConfiguration webApiConfiguration = new WebApiConfiguration.Builder().setWebApiKey("your-api-key")
                    .setEnvironment("your-environment") // default is Environment.SYSTEM_TEST
                    .setAuthorizationToken("your-auth-token")
                    .setLanguage("your-language") // default is "cs-CZ"
                    .create();
    Scenarios.getInstance().init(webApiConfiguration);
    
    // Obtain your ScenariosClient
    ScenariosClient client = Scenarios.getInstance().getScenariosClient();

```

## Resources
These resources are available on the `ScenariosClient`.

- [`EventsResource`](../scenarios/src/main/java/cz/csas/scenarios/EventsResource.java) - Post single event or collection of events.

To get resource instances see the following code snippet.

```java

    // Get EventsResource Instance
    Scenarios.getInstance().getScenariosClient().getEventsResource()...

```

## Usage
This usage guide walks you through a process of posting events.

### 1. Post single event

You can post a single event using `EventsResource` method `postSingle`. See also [`Events`](../scenarios/src/main/java/cz/csas/scenarios/model/Event.java) for creation the single event (note `Builder` is available).

``` java

    // Create event
    Event event = new Event.Builder().setEventTypeId("your-event-type-id")
            .setEventType("your-event-type")
            .setApplicationId("your-application-id")
            .setApplication("your-application")
            .setEventCreation("your-event-creation-date")
            .setClientId("your-client-id")
            .setValues("your-values")
            .create();
                
       
    // Post your single event
    Scenarios.getInstance().getScenariosClient()
            .getEventsResource()
            .postSingle(Event event, Callback callback);
```

### 2. Post collection of events

You can post a collection of events using `EventsResource` method `postCollection`. See also [`Events`](../scenarios/src/main/java/cz/csas/scenarios/model/Event.java) for creation the single event (note `Builder` is available).

``` java

    // Post collection of events
    Scenarios.getInstance().getScenariosClient()
            .getEventsResource()
            .postCollection(List<Event> events, Callback callback);
            
``` 

## Further documentation
Please see the documented [public API of the ScenariosSDK](../scenarios/src/main/java/cz/csas/scenarios/) for detailed description of the functionalities provided by this SDK.

This SDK communicates with Scenarios API. You can have a look at its [documentation](http://docs.ext0csasscenarioseventsv1.apiary.io/#).
