package com.magicsmsboat.finanzapp.finanzapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtData;
import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtList;

import java.util.zip.Inflater;

/**
 * Created by rol on 30.07.2015.
 */
public class ListAdapter extends BaseAdapter
{
    private final FinanzamtList mListData;

    ListAdapter(FinanzamtList listData)
    {
        mListData = listData;
    }

    @Override
    public int getCount()
    {
        return mListData.getCount();
    }

    @Override
    public Object getItem(int position)
    {
        return mListData.getItem(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View itemView;
        if (null != convertView) itemView = convertView;
        else
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            itemView = inflater.inflate(R.layout.list_item, null, false);
        }

        FinanzamtData data = mListData.getItem(position);

        TextView tv = (TextView) itemView.findViewById(R.id.item_title);
        tv.setText(data.getDataItem(FinanzamtData.DisNameLang));


        tv = (TextView) itemView.findViewById(R.id.item_content);

        StringBuilder sb = new StringBuilder();
        sb.append(data.getDataItem(FinanzamtData.DisPlz));
        sb.append(" ");
        sb.append(data.getDataItem(FinanzamtData.DisOrt));
        sb.append(", ");
        sb.append(data.getDataItem(FinanzamtData.DisStrasse));

        tv.setText(sb.toString());

        return itemView;
    }
}
