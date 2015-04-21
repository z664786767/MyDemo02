package org.mobiletrain.www.demo01;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by aaa on 15-4-18.
 */
public class MyProvader extends ActionProvider {

     private ArrayList<HashMap<String,Object>>data;

    public MyProvader(Context context) {
        super(context);
        data=new ArrayList<HashMap<String,Object>>();
        for (int i = 0; i <5 ; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("icon",R.drawable.ic_launcher);
            map.put("item","item"+i);
            data.add(map);
        }
    }

    @Override
    public View onCreateActionView() {

        final ImageView view=new ImageView(getContext());
        view.setImageResource(R.drawable.ic_launcher);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow window = new PopupWindow(getContext());
                window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setWidth(300);
                ListView listView = new ListView(getContext());
                SimpleAdapter adapter = new SimpleAdapter(getContext(),
                        data,
                        R.layout.item_list,
                        new String[]{"icon", "item"},
                        new int[]{R.id.icon, R.id.text});
                listView.setAdapter(adapter);
                listView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });
                window.setContentView(listView);
                window.setOutsideTouchable(true);
                window.showAsDropDown(view);
            }
        });
        return view;
    }
//    @Override
//    public boolean onPerformDefaultAction() {
//        return super.onPerformDefaultAction();
//    }
}
