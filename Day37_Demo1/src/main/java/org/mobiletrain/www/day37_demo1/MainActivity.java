package org.mobiletrain.www.day37_demo1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements SensorEventListener{

    private static final String TAG ="=============" ;
    private List<Sensor> sensorList;
    private SensorManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //传感器管理对象
        mgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //得到手机的所有传感器
        sensorList = mgr.getSensorList(Sensor.TYPE_ALL);
        ListView list= (ListView) findViewById(R.id.list);
        list.setAdapter(new MyAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor sensor= mgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        mgr.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mgr.unregisterListener(this);
    }
    //传感器的值发生变化调用
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[]v=event.values;
        Log.i(TAG,v[0]+"----------------");
    }
    //传感器的采样率发生变化时调用
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (sensorList!=null){
                return sensorList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return sensorList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=getLayoutInflater().inflate(android.R.layout.simple_list_item_2,null);
            }
            TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
            TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);

            Sensor s = sensorList.get(position);
            text1.setText(s.getName());
            text2.setText(s.getVendor());
            return convertView;
        }
    }


}
