package cz.csas.scenarios;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.scenarios.error.RestError;
import cz.csas.scenarios.error.ScenariosSDKError;
import cz.csas.scenarios.model.Environment;
import cz.csas.scenarios.model.Method;
import cz.csas.scenarios.model.WebApiConfiguration;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public abstract class ScenariosTest {

    private final String TEST_BASE_URL = "http://csas-judge.herokuapp.com/webapi";
    private final String JUDGE_BASE_URL = "http://csas-judge.herokuapp.com";
    private final String TEST_API_KEY = "TEST_API_KEY";
    private final String TEST_API_TOKEN = "Bearer token";

    protected WebApiConfiguration mWebApiConfiguration;
    protected ScenariosClient mScenariosClient;
    protected RestClient mJudgeApiClient;

    protected String mXJudgeCase;
    protected String mXJudgeSessionHeader;

    protected HashMap<String, String> judgeHeaders;


    @Before
    public void setup() {

        mXJudgeSessionHeader = UUID.randomUUID().toString();

        mWebApiConfiguration = new WebApiConfiguration(TEST_API_KEY, Environment.other(TEST_BASE_URL), TEST_API_TOKEN);

        mScenariosClient = Scenarios.getInstance().init(mWebApiConfiguration).getScenariosClient();
        mScenariosClient.addHeader("x-judge-session", mXJudgeSessionHeader);


        // Judge
        judgeHeaders = new HashMap<>();
        judgeHeaders.put("x-judge-session", mXJudgeSessionHeader);
        judgeHeaders.put("x-judge-case", mXJudgeCase);

        mJudgeApiClient = new RestClient(new WebApiConfiguration(JUDGE_BASE_URL));
        mJudgeApiClient.setHeaders(judgeHeaders);

        // Make Judge a call and wait for it to finish
        final CountDownLatch judgeSignal = new CountDownLatch(1);
        mJudgeApiClient.makeWebRequest("/judge/nextCase", Method.POST, null, new Callback() {
            @Override
            public void success() {
                Log.d("asdf", "judge success");
                judgeSignal.countDown();
            }

            @Override
            public void failure(ScenariosSDKError error) {
                Log.d("asdf", ((RestError) error).getResponse().getBody());
                judgeSignal.countDown();
            }
        });

        try {
            judgeSignal.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
