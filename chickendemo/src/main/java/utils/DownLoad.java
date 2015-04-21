package utils;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by aaa on 15-4-16.
 */
public class DownLoad {

    private static InputStream input;
    private static ByteArrayOutputStream out;
    private static Handler handler;
    private NetState state;

    public DownLoad(NetState state) {
        this.state = state;
    }

    public static void loadJson(final String path, final NetState state){
        if(handler==null){
            handler=new Handler();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    input=conn.getInputStream();
                    out=new ByteArrayOutputStream();
                    int len;
                    byte a[]=new byte[2048];
                    while((len= input.read(a))!=-1){
                        out.write(a,0,len);
                    }
                    final String json=new String(out.toByteArray());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            state.ok(json);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    state.error("下载失败");
                }
            }
        }.start();
    }
    public interface NetState{
        public void ok(String data);
        public void error(String data);
    }
}
