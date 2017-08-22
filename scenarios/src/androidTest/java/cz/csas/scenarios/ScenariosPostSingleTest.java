package cz.csas.scenarios;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.scenarios.error.ScenariosSDKError;
import cz.csas.scenarios.model.Account;
import cz.csas.scenarios.model.Event;
import cz.csas.scenarios.model.EventType;
import cz.csas.scenarios.model.Values;

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

        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("csas"));

        Event event = new Event(1, EventType.LoadURI, 1, "Penize na klik", new Date(1393512305L * 1000), "2015", new Values("www.csas.cz/getAccounts", accounts));

        scenarioSignal = new CountDownLatch(1);
        mScenariosClient.getEventsResource().postSingle(event, new Callback() {
            @Override
            public void success() {
                success = true;
                scenarioSignal.countDown();
            }

            @Override
            public void failure(ScenariosSDKError error) {
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
