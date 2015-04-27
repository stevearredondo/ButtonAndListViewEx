package com.example.steve;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/* Created by Steve on 4/27/2015. */
public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*If this is the first time the Activity was created, use a new instance of the MainFragment.
        * This removes the problem of re-adding the Fragment on screen-rotation, or any other actions
        * that result in a call to onStop().*/
        if (savedInstanceState==null)
        {
            MainFragment f = new MainFragment();

            //get String ArrayList arguments for data
            ArrayList<String> data1 = setData(1);
            ArrayList<String> data2 = setData(2);

            Bundle args = new Bundle();
            args.putStringArrayList(MainFragment.DATA_1, data1);
            args.putStringArrayList(MainFragment.DATA_2, data2);
            f.setArguments(args);

            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, f)
                    .commit();
        }

    }

    public ArrayList<String> setData(ArrayList<String> data, int index)
    {
        //Do something
        return new ArrayList<>();
    }

    private ArrayList<String> setData(int index)
    {
        return new ArrayList<>();
    }
}