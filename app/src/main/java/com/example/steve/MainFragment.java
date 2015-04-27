package com.example.steve;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/* Created by Steve on 4/27/2015. */

/**
 * The goal of this exercise was to create an application with a simple layout of two buttons
 * (indexed and labeled "1" and "2") and a ListView. The ListView displays an ArrayList
 * of string data corresponding to the index of each button. This data is retrieved by the method
 * {@link #getData(int)}. The data itself can be set and retrieved as fragment arguments, or set
 * separately via {@link #setData(java.util.ArrayList, int)}.
 */
public class MainFragment extends Fragment implements View.OnClickListener
{
    public static final String DATA_1 = "data1";
    public static final String DATA_2 = "data2";

    private static final String INDEX_KEY = "indexKey";

    private ArrayList<String> data1;
    private ArrayList<String> data2;
    private int currentIndex;
    private MyListViewAdapter myListViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Control for creating new instances of this Fragment that reset the displayed ListView as
        * a result of onStop() being called.*/
        if(savedInstanceState!=null)
        {
            currentIndex = savedInstanceState.getInt(INDEX_KEY, -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**Initialize UI components*/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Retrieve data from getArguments()
        if (data1==null || data2==null)
        {
            Bundle args = getArguments();
            data1 = args.getStringArrayList(DATA_1);
            data2 = args.getStringArrayList(DATA_2);
        }

        //Set ListView adapter
        ListView listView = (ListView) view.findViewById(R.id.listView);
        myListViewAdapter = new MyListViewAdapter(getActivity(), new ArrayList<String>());
        listView.setAdapter(myListViewAdapter);

        setButtonOnClickListeners(view);

        //If returning from a previous state with a set index, update the ListView adapter data
        if(currentIndex >= 0)
        {
            updateStringListDisplay(currentIndex);
        }
    }

    /**
     * Ensures that repeated lifecycles of this Fragment retain the current index information
     * whenever the cycle reaches onStop() so that it can be restored later.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(INDEX_KEY, currentIndex);
    }

    /**
     * {@link android.view.View.OnClickListener} implementation handling clicks for Buttons 1 and 2.
     * @see #updateStringListDisplay(int)
     */
    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id==R.id.button1)
        {
            updateStringListDisplay(1);
        }
        else if (id==R.id.button2)
        {
            updateStringListDisplay(2);
        }
    }

    /**
     * Public setter for data arrays
     * @param data     The data to set
     * @param index    The index corresponding to the data array to set (1 or 2).
     *                 An {@code java.lang.IllegalArgumentException} is thrown for any other values.
     */
    public void setData(ArrayList<String> data, int index)
    {
        if (index==1) data1 = data;
        else if (index==2) data2 = data;
        else throw invalidIndex(index);
    }

    /**
     * When either button is clicked, the ListView will display a list of strings, based on a given
     * index. This index parameter will be the button number.
     */
    private void setButtonOnClickListeners(View view)
    {
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    /**
     * Get user data and update our ListView adapter's display values.
     * @param index index value to pass to get(data)
     * @see #getData(int)
     */
    private void updateStringListDisplay(int index)
    {
        //Set adapter data and notify the ListView of the change
        myListViewAdapter.setStringList(getData(index));
        currentIndex = index;
    }

    /**
     * Placeholder method for getting List of String data
     *
     * Instructions state "Assume there is an internal method with the signature
     * private List<String> getData(int index) ..."
     *
     * @param index An index which determines a List of Strings to return
     * @return A list of strings
     */
    private List<String> getData(int index)
    {
        if (index==1) return data1;
        if (index==2) return data2;
        throw invalidIndex(index);
    }

    private static IllegalArgumentException invalidIndex(int index)
    {
        return new IllegalArgumentException("Invalid index: (" + index + "). Must be 1 or 2.");
    }
}
