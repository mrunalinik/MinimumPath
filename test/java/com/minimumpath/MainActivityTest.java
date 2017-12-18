package com.minimumpath;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.View;

import com.minimumpath.ui.MainActivity;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final String TAG = "MainActivityTest";
    private MainActivity mActivity = null;

    public static Test suite() {
        TestSuite suite = new TestSuite(MainActivityTest.class.getName());
        suite.addTest(TestSuite.createTest(MainActivityTest.class, "testExample1"));
        suite.addTest(TestSuite.createTest(MainActivityTest.class, "testExample2"));
        suite.addTest(TestSuite.createTest(MainActivityTest.class, "testExample3"));
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        Log.d(TAG, "setUp");
        super.setUp();

        setActivityInitialTouchMode(false);

        Intent intent = new Intent();
        intent.putExtra("test_project", true);
        setActivityIntent(intent);
        mActivity = getActivity();

    }

    @SuppressWarnings("deprecation")
    public MainActivityTest() {
        super("com.minimumpath", MainActivity.class);
    }


    public void testExample1(){
        clearData();
        setExample1();
        findResult();
    }

    public void testExample2(){
        clearData();
        setExample2();
        findResult();
    }

    public void testExample3(){
        clearData();
        setExample3();
        findResult();
    }

    @UiThreadTest
    public void clearData(){
        View clearData = mActivity.findViewById(R.id.clear_data);
        assertTrue(clearData != null);
        clickView(clearData);
    }

    @UiThreadTest
    public void setExample1(){
        View eng1 = mActivity.findViewById(R.id.example_matrix_1);
        assertTrue(eng1 != null);
        clickView(eng1);
    }

    @UiThreadTest
    public void setExample2(){
        View eng1 = mActivity.findViewById(R.id.example_matrix_2);
        assertTrue(eng1 != null);
        clickView(eng1);
    }

    @UiThreadTest
    public void setExample3(){
        View eng1 = mActivity.findViewById(R.id.example_matrix_3);
        assertTrue(eng1 != null);
        clickView(eng1);
    }

    @UiThreadTest
    public void findResult(){
        View findResult = mActivity.findViewById(R.id.find_button);
        assertTrue(findResult != null);
        clickView(findResult);
    }


    private void clickView(final View view) {
        Log.d(TAG, "clickView: "+ view);
        assertTrue(view.getVisibility() == View.VISIBLE);
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                assertTrue(view.performClick());
                if(view.getId() == R.id.find_button){

                }
            }
        });
    }

}
