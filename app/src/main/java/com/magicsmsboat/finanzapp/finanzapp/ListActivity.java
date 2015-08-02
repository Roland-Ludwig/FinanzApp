package com.magicsmsboat.finanzapp.finanzapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtData;
import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtList;
import com.magicsmsboat.finanzapp.finanzapp.data.JsonDownload;
import com.magicsmsboat.finanzapp.finanzapp.data.StoreData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ListActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private LoaderManager.LoaderCallbacks<String> mLoaderJsonCallback;
    private FinanzamtList mDataList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoaderJsonCallback = new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int id, Bundle args)
            {
                return new JsonDownload(ListActivity.this);
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String data)
            {
                if (null != data)
                {
                    StoreData.writeFile(data, ListActivity.this);
                    initList(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader)
            {

            }
        };

        mListView = new ListView(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemsClicked(position);
            }
        });

        setContentView(mListView);

        String savedData = StoreData.readFile(this);
        if (null != savedData) initList(savedData);

        getLoaderManager().initLoader(0, null, mLoaderJsonCallback).forceLoad();
    }


    private void initList(String json)
    {
        try
        {
            FinanzamtList l = new FinanzamtList();
            l.initJSON(json);
            mDataList = l;
            ListAdapter adapter = new ListAdapter(l);
            mListView.setAdapter(adapter);

        } catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this, R.string.errtext_import_failed, Toast.LENGTH_LONG).show();
        }
    }

    private void onItemsClicked(int pos)
    {
        if (null == mDataList) return;
        if (mDataList.getCount() < pos+1) return;
        FinanzamtData data = mDataList.getItem(pos);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.DETAIL_DATA, data);
        startActivity(intent);
    }

}
