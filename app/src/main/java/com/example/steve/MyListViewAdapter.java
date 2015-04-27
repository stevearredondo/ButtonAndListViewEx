package com.example.steve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/* Created by Steve on 4/27/2015. */

/**
 * Simple adapter that takes a list of string data and places each entry in a customized row view.
 * This class has a convenience method {@link #setStringList(java.util.List)} for updating its
 * data and notifying watchers of the update.
 */
public class MyListViewAdapter extends BaseAdapter
{
    private Context c;
    private List<String> stringList;

    /**
     * Constructs a new instance of this adapter with the list of string data
     * @param c The context for this application
     * @param stringList The data for this adapter to display
     */
    public MyListViewAdapter(Context c, List<String> stringList)
    {
        this.c = c;
        this.stringList=stringList;
    }

    /**
     * Setter method for new data. Notifies components observing this data of change, tells
     * views reflecting this data to refresh.
     * @param stringList New data for the adapter to display
     * @see #notifyDataSetChanged()
     */
    public void setStringList(List<String> stringList)
    {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return stringList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Recycle view if possible
        if (convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            /*parent is only used to create the correct subclass of LayoutParams for the root view in the XML*/
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }

        TextView rowText = (TextView) convertView.findViewById(R.id.listItemTextView);

        /*put the text from the stringList at the given position into the textView*/
        rowText.setText(stringList.get(position));
        return convertView;
    }
}
