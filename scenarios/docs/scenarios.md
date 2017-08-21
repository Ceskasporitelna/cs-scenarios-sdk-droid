# Using ScenariosSDL
Scenarios allow you to post an event or collection of events .

## Usage
Here is a guide on how to use the SDK

### 1) The initialization
First, you need to create a WebApiConfiguration object and specify WebApiKey, Environment and AuthorizationToken
``` Java
WebApiConfiguration webApiConfiguration = new WebApiConfiguration("YOUR_API_KEY", Environment.SYSTEM_TEST, "YOUR_AUTHORIZATION_TOKEN");
```
There are two predefined environments - PRODUCTION and SYSTEM_TEST. If you want to use custom environment, use `Environment.other(YOU_CUSTOM_URL)`.

### 2) Posting events
To post an Event, you need to get an instance of Scenarios, pass it the WebApiConfiguration on initialization, get eventsResource a either postSingle or postCollection.

Posting a single event:
``` Java
WebApiConfiguration webApiConfiguration = new WebApiConfiguration("YOUR_API_KEY", Environment.SYSTEM_TEST, "YOUR_AUTHORIZATION_TOKEN");

ArrayList<Account> accounts = new ArrayList<>();
accounts.add(new Account("uzivatel"));
Event event = new Event(1, EventType.Api, 1, "application", new Date(), "5", new Values("www.csas.cz/getAccounts", accounts));

Scenarios.getInstance().init(webApiConfiguration).getScenariosClient().getEventsResource().postSingle(event, new ApiCallback() {
    @Override
    public void success() {

    }

    @Override
    public void failure(BaseScenariosError error) {


    }
});
```

If you want to post multiple events, simply create an ArrayList of events and call `postCollection()` instead of `postSingle` in previous example:
```
ArrayList<Event> events = new ArrayList<>();

events.add(YourEvent1);
events.add(YourEvent2);
events.add(YourEvent3);

Scenarios.getInstance().init(webApiConfiguration).getScenariosClient().getEventsResource().postCollection(events, new ApiCallback() {
    @Override
    public void success() {

    }

    @Override
    public void failure(BaseScenariosError error) {


    }
});

``` 
### Handling errors
There are two kinds of errors - `RestError` and `ScenariosError`, both of which extend `BaseScenariosError`. If you want to get details on your failed API call, cast `BaseScenariosError` to `RestError` and you will be able to get all necessary information.

```
Scenarios.getInstance().init(webApiConfiguration).getScenariosClient().getEventsResource().postCollection(event, new ApiCallback() {
    @Override
    public void success() {

    }

    @Override
    public void failure(BaseScenariosError error) {
        
        if (error instanceof RestError) {
            RestError restError = (RestError) error;
            if (restError.getResponse() != null) {
                Log.d("YOUR_TAG", restError.getResponse().getBody());
            }
        }

    }
});
```
