package com.minyazev.appholidaysrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.minyazev.appholidaysrecyclerview.model.ListHolidaysInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ListItemClickListener {

    public static final String TAG = "MainActivity";
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    private Toast toast;
    private ListHolidaysInfo listHolidaysInfo;


    private static String urlString = "https://calendarific.com/api/v2/holidays?api_key=312b82ac9b4a3a90002c36fa8d19aa1350f03460&country=RU&year=2023";
    private Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage( Message msg) {
            if(msg.what == SUCCESS){
                String responseData = (String)msg.obj;
                parseData(responseData);
                //tvData.setText(responseData);
            } else{
                if(msg.what == ERROR){
                    Log.e(TAG, "handleMessage: Ошибка при получении данных" );
                    //tvData.setText("Ошибка при получении данных");
                }
            }

        }
    };


    @Override
    public void onListItemClick(int clickedItemIndex) {

        CharSequence text = listHolidaysInfo.listHolidaysInfo[clickedItemIndex].getHoliday_desc();
        int duration = Toast.LENGTH_SHORT;
        if(toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(this, text, duration);
        toast.show();

    }

    void parseData(String output){
        try{
            JSONObject outputJSON = new JSONObject(output);
            JSONObject responseJSON = outputJSON.getJSONObject("response");
            JSONArray array = responseJSON.getJSONArray("holidays");
            int length = array.length();
            listHolidaysInfo = new ListHolidaysInfo(length);
            ArrayList <String> namesHolidays = new ArrayList<String>();
            for (int i = 0; i < length; i++) {
                JSONObject obj = array.getJSONObject(i);

                String name = obj.getString("name");
                String desc = obj.getString("description");
                JSONObject obj_data = obj.getJSONObject("date");
                String data_iso = obj_data.getString("iso");

                namesHolidays.add(name);
                listHolidaysInfo.addHoliday(name, data_iso, desc, i);

            }

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new MyAdapter(listHolidaysInfo, length, this));


        } catch (JSONException e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataFetcherThread thread = new DataFetcherThread(mHandler, urlString);
        thread.start();

    }
}