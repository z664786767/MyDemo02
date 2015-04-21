package fragmentbean;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.BitmapUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mobiletrain.www.chickendemo.R;

import java.util.ArrayList;

import bean.Home;
import utils.DownLoad;
import utils.ImageLoad;


public class HomeFragment extends Fragment {

    private ViewPager pager;
    String path = "http://www.xinshipu.com/api/doWeeklyhotshipu.html";
    private RequestQueue requestQueue;
    private ArrayList<Home> homes = new ArrayList<Home>();
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    private ImageRequest imgrequest;
    public static final String TAG ="=============" ;
    private BitmapUtils bitmapUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bitmapUtils = ImageLoad.getBitmapUtils(getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager_home);
        requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest(path, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("list");

                    for (int i = 0; i < array.length(); i++) {
                        Home home = new Home();
                        JSONObject obj = array.getJSONObject(i);
                        home.setId(obj.getInt("id"));
                        home.setShipuName(obj.getString("shipuName"));
                        home.setName(obj.getString("name"));
                        home.setShipuNameInFourDiv(obj.getString("shipuNameInFourDiv"));
                        home.setImage(obj.getString("image"));

                        home.setBigimage(obj.getString("bigimage"));
                        homes.add(home);
                    }
                    for (int i = 0; i <homes.size() ; i++) {
                        ImageView imageview=new ImageView(getActivity());
                        imageview.setScaleType(ImageView.ScaleType.FIT_XY);
                        bitmapUtils.display(imageview,homes.get(i).getImage());
                        images.add(imageview);
                        Log.i(TAG,images.size()+"  1");
                    }
                    pager.setAdapter(new HomePager());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        for (int i = 0; i < homes.size(); i++) {
//            imgrequest=new ImageRequest(homes.get(i).getImage(), new Response.Listener<Bitmap>() {
//                @Override
//                public void onResponse(Bitmap response) {
//                    ImageView view=new ImageView(getActivity());
//                    view.setImageBitmap(response);
//                    images.add(view);
//                }
//            }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//
//            });
//
//            requestQueue.add(imgrequest);
//        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        requestQueue.add(jsonRequest);




        return view;


    }

    class HomePager extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View img=images.get(position%homes.size());
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView(images.get(position%homes.size()));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

}
