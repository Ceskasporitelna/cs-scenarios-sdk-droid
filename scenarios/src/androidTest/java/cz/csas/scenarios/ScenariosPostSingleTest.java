package cz.csas.scenarios;

import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.scenarios.error.CsScenariosSDKError;
import cz.csas.scenarios.model.Callback;
import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.EventType;
import cz.csas.scenarios.utils.TimeUtils;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class ScenariosPostSingleTest extends ScenariosTest {

    private final String X_JUDGE_CASE_SCENARIOS_POST_SINGLE = "scenarios.events.post.single";
    private CountDownLatch scenarioSignal;
    private boolean success;

    @Override
    public void setup() {
        mXJudgeCase = X_JUDGE_CASE_SCENARIOS_POST_SINGLE;
        super.setup();
        success = false;
    }

    @Test
    public void testScenarioPostSingle() {

        Event event = new Event.Builder().setEventTypeId(1)
                .setEventType(EventType.LOAD_URI)
                .setApplicationId(1)
                .setApplication("Penize na klik")
                .setEventCreation(TimeUtils.getISO8601Date("2014-02-27T15:45:05+01:00"))
                .setClientId("2015")
                .setValues(new Values("www.csas.cz/getAccounts", Collections.singletonList(new Account("csas"))))
                .create();

        scenarioSignal = new CountDownLatch(1);
        mScenariosClient.getEventsResource().postSingle(event, new Callback() {
            @Override
            public void success() {
                success = true;
                scenarioSignal.countDown();
            }

            @Override
            public void failure(CsScenariosSDKError error) {
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
