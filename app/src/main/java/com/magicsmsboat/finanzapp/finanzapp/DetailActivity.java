package com.magicsmsboat.finanzapp.finanzapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.magicsmsboat.finanzapp.finanzapp.data.FinanzamtData;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_DATA = "detailData";
    private FinanzamtData mDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras)
        {
            mDetailData = (FinanzamtData) extras.getSerializable(DETAIL_DATA);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);

        initCtrls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    private void initCtrls()
    {
        if (null == mDetailData) return;

        TextView tv;

        tv = (TextView) findViewById(R.id.detail_title);
        tv.setText(mDetailData.getDataItem(FinanzamtData.DisNameLang));

        tv = (TextView) findViewById(R.id.detail_adress);
        StringBuilder sb = new StringBuilder();
        sb.append(mDetailData.getDataItem(FinanzamtData.DisPlz));
        sb.append(" ");
        sb.append(mDetailData.getDataItem(FinanzamtData.DisOrt));
        sb.append(", ");
        sb.append(mDetailData.getDataItem(FinanzamtData.DisStrasse));
        tv.setText(sb.toString());

        tv = (TextView) findViewById(R.id.detail_hours);
        tv.setText(mDetailData.getDataItem(FinanzamtData.DisOeffnung));

        tv = (TextView) findViewById(R.id.detail_tel);
        tv.setText(mDetailData.getDataItem(FinanzamtData.DisTel));

        WebView wv = (WebView) findViewById(R.id.detail_img);
        wv.loadUrl(mDetailData.getDataItem(FinanzamtData.DisFotoUrl));

        Button btn = (Button) findViewById(R.id.detail_show_map);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onShowMap();
            }
        });
    }


    private void onShowMap()
    {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(DetailActivity.DETAIL_DATA, mDetailData);
        startActivity(intent);
    }

}
