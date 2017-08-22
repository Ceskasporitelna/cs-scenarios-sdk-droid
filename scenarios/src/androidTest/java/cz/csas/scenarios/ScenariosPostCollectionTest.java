package cz.csas.scenarios;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.scenarios.model.error.BaseScenariosError;
import cz.csas.scenarios.model.Account;
import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.EventType;
import cz.csas.scenarios.model.Values;
import cz.csas.scenarios.model.error.RestError;

import static junit.framework.Assert.assertEquals;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ScenariosPostCollectionTest extends ScenariosTest {

    private final String X_JUDGE_CASE_SCENARIOS_POST_SINGLE = "scenarios.events.post.collection";

    private CountDownLatch scenarioSignal;

    @Override
    public void setup() {
        mXJudgeCase = X_JUDGE_CASE_SCENARIOS_POST_SINGLE;
        super.setup();

        scenarioSignal = new CountDownLatch(1);

        // Make a Judge call and wait for it to finish
        final CountDownLatch judgeSignal = new CountDownLatch(1);
        mJudgeApiClient.callApi("/judge/nextCase", WebApiClient.POST, null, new ApiCallback() {
            @Override
            public void success() {
                judgeSignal.countDown();
            }

            @Override
            public void failure(BaseScenariosError error) {

            }
        });
        try {
            judgeSignal.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testScenarioPostCollection() {

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("csas"));
        final boolean[] success = {false};

        Event event = new Event(1, EventType.LoadURI, 1, "Penize na klik",  new Date(1393512305L * 1000), "2015", new Values("www.csas.cz/getAccounts", accounts));

        ArrayList<Event> events = new ArrayList<>();
        events.add(event);

        mScenariosClient.getEventsResource().postCollection(events, new ApiCallback() {
            @Override
            public void success() {
                success[0] = true;
                scenarioSignal.countDown();
            }

            @Override
            public void failure(BaseScenariosError error) {
                if (error instanceof RestError) {
                    RestError restError = (RestError) error;
                    if (restError.getResponse() != null) {
                        Log.d("YOUR_TAG", restError.getResponse().getBody());
                    }
                }

                scenarioSignal.countDown();
            }
        });

        try {
            scenarioSignal.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(true, success[0]);
    }
}
