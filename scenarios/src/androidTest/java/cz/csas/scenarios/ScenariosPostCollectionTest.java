package cz.csas.scenarios;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.scenarios.error.CsRestError;
import cz.csas.scenarios.error.CsScenariosSDKError;
import cz.csas.scenarios.model.Callback;
import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.EventType;

import static junit.framework.Assert.assertEquals;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ScenariosPostCollectionTest extends ScenariosTest {

    private final String X_JUDGE_CASE_SCENARIOS_POST_SINGLE = "scenarios.events.post.collection";
    private CountDownLatch scenarioSignal;
    private boolean success;

    @Override
    public void setup() {
        mXJudgeCase = X_JUDGE_CASE_SCENARIOS_POST_SINGLE;
        super.setup();

        success = false;
    }

    @Test
    public void testScenarioPostCollection() {

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("csas"));

        Event event = new Event(1, EventType.LOAD_URI, 1, "Penize na klik",  new Date(1393512305L * 1000), "2015", new Values("www.csas.cz/getAccounts", accounts));

        ArrayList<Event> events = new ArrayList<>();
        events.add(event);

        scenarioSignal = new CountDownLatch(1);
        mScenariosClient.getEventsResource().postCollection(events, new Callback() {
            @Override
            public void success() {
                success = true;
                scenarioSignal.countDown();
            }

            @Override
            public void failure(CsScenariosSDKError error) {
                if (error instanceof CsRestError) {
                    CsRestError restError = (CsRestError) error;
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

        assertEquals(true, success);
    }
}
