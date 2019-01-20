package com.example.lenovo.mymoviestage1;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView1;
    TextView title_tv,vote_tv,overview_tv,release_tv;
    String ntitle,nvote,nposter,noverview,nrelease;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView1=findViewById(R.id.main2img);
        title_tv=findViewById(R.id.main2title);
        vote_tv=findViewById(R.id.main2vote);

        overview_tv=findViewById(R.id.main2overview);
        release_tv=findViewById(R.id.main2release);
        ntitle=getIntent().getStringExtra("mtitle");
        nvote=getIntent().getStringExtra("mvote_avg");
        nposter=getIntent().getStringExtra("mposter");
        noverview=getIntent().getStringExtra("moverview");
        nrelease=getIntent().getStringExtra("mrelease");

        Picasso.with(this).load(nposter).into(imageView1);

        title_tv.setText(ntitle);
        vote_tv.setText(nvote);
        overview_tv.setText(noverview);
        release_tv.setText(nrelease);


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
               break;
        }
        return super.onOptionsItemSelected(item);
    }
}
