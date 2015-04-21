package org.mobiletrain.www.quickmedia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bean.First;


public class MainActivity extends ActionBarActivity {
    private String path="http://api.le123.com/kuaikan/apithemelist_json.so?pagesize=10&platform=Le123Plat101xiaomi&vt=1&pageindex=1&plattype=aphone&code=7884b0bd86607a87&uuid=d76028e4-9439-366e-b54d-72f8b7bb49d6&version=1.6.3";
    private String TAG="========================";
    private ArrayList<First> firsts;
    private First first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firsts = new ArrayList<First>();
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,path,new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {
                String json=(String)(objectResponseInfo.result);
                try {
                    JSONObject object=new JSONObject(json);
                    first.setPagesize(object.getInt("pagesize"));
                    JSONArray data = object.getJSONArray("data");
                    for (int i = 0; i <data.length() ; i++) {
                        first=new First();
                        JSONObject obj=data.getJSONObject(i);
                        first.setName(obj.getString("name"));
                        first.setThemeid(obj.getInt("themeid"));
                        first.setPic(obj.getString("pic"));
                        first.setShortdesc(obj.getString("shortdesc"));
                        firsts.add(first);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
        BitmapUtils bitmapUtils=new BitmapUtils(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
