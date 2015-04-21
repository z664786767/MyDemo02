package org.mobiletrain.www.aday30_demo02_textswitcher;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final TextSwitcher textSwitcher= (TextSwitcher) findViewById(R.id.textSwitcher);
   //    textSwitcher.setCurrentText("中国社会科学院");
       textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
           @Override
           public View makeView() {

               TextView tv=new TextView(MainActivity.this);
               tv.setTextSize(30);
               tv.setTextColor(Color.BLACK);
               return tv;
           }
       });
        textSwitcher.setInAnimation(this,R.anim.downtoup_in);
        textSwitcher.setOutAnimation(this,R.anim.downtoup_out);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSwitcher.setText("WWWWWWW");
            }
        });

    }


}
