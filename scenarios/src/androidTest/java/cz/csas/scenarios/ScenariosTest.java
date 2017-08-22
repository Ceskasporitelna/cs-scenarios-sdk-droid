package cz.csas.scenarios;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.UUID;

import cz.csas.scenarios.model.Environment;
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
    protected WebApiClient mJudgeApiClient;

    protected String mXJudgeCase;
    protected String mXJudgeSessionHeader;

    @Before
    public void setup() {

        mXJudgeSessionHeader = UUID.randomUUID().toString();

        HashMap<String, String> judgeHeaders = new HashMap<>();
        judgeHeaders.put("x-judge-session", mXJudgeSessionHeader);
        judgeHeaders.put("x-judge-case", mXJudgeCase);

        mJudgeApiClient = new WebApiClient(new WebApiConfiguration(JUDGE_BASE_URL, judgeHeaders));

        mWebApiConfiguration = new WebApiConfiguration(TEST_API_KEY, Environment.other(TEST_BASE_URL), TEST_API_TOKEN);
        mWebApiConfiguration.getHeaders().put("x-judge-session", mXJudgeSessionHeader);
        mScenariosClient = Scenarios.getInstance().init(mWebApiConfiguration).getScenariosClient();
    }

}
