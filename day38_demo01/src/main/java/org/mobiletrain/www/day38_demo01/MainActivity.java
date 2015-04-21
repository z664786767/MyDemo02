package org.mobiletrain.www.day38_demo01;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;


public class MainActivity extends ActionBarActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> discoverDeviceAdapter;
    private AlertDialog dialog;
    private ArrayList<BluetoothDevice> discoverydevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list=(ListView)findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        //得到蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //是否支持蓝牙
        if(bluetoothAdapter!=null){
            //是否支持蓝牙
            if(!bluetoothAdapter.isEnabled()){
                //开户蓝牙
                //直接打开蓝牙
//                bluetoothAdapter.enable();
                //关闭蓝牙
                //blutoothAdapter.disable();
                //打开设置界面
                Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(i,0);
            }else{
                //获得已经绑定的设备
                getBoundDevices();
            }
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索设备
                bluetoothAdapter.startDiscovery();
                discoverDeviceAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
                discoverydevices = new ArrayList<BluetoothDevice>();

               dialog= new AlertDialog.Builder(MainActivity.this).setTitle("正在搜索设置")
                        .setAdapter(discoverDeviceAdapter,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("取消搜索",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消搜索操作
                       bluetoothAdapter.cancelDiscovery();

                    }
                }).setCancelable(false)
                .show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);

        registerReceiver(blurtoothReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(blurtoothReceiver);
    }

    private BroadcastReceiver blurtoothReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           String action= intent.getAction();
            switch (action){
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    break;
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice devicess=
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    discoverydevices.add(devicess);
                    discoverDeviceAdapter.add(devicess.getName()+"\n"+devicess.getAddress()
                            +"\n"+(devicess.getBondState()==BluetoothDevice.BOND_BONDED?"已绑定":"未绑定"));

                    break;
            }
        }
    };
    public void getBoundDevices(){
        Set<BluetoothDevice> boun=bluetoothAdapter.getBondedDevices();
        for(BluetoothDevice d:boun){
            adapter.add(d.getName() +
                    "\n" + d.getAddress());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==RESULT_OK){
            if(bluetoothAdapter.isEnabled()){
                getBoundDevices();
            }
        }
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
