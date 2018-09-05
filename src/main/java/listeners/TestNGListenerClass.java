package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TestNGListenerClass implements ISuiteListener {
    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("Starting test Suite");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println("Finishing test Suite");
    }
}
