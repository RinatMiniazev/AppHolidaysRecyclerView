package com.minyazev.appholidaysrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minyazev.appholidaysrecyclerview.model.ListHolidaysInfo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private int counter_createMyViewHolder = 0;
    private int counter_bindMyViewHolder = 0;

    final private ListItemClickListener mOnclickListener;

    public interface ListItemClickListener {
        void onListItemClick (int clickedItemIndex);
    }

    private ListHolidaysInfo listHolidaysInfo;
    private int mNumberItems;
    public static final String TAG = "MyAdapter";

    public MyAdapter (ListHolidaysInfo listHolidaysInfo, int length, ListItemClickListener listener){
        this.mOnclickListener = listener;
        this.mNumberItems = length;
        this.listHolidaysInfo = listHolidaysInfo;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holiday_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        counter_createMyViewHolder++;
        Log.d(TAG, "onCreateViewHolder: called "+counter_createMyViewHolder);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
        counter_bindMyViewHolder++;
        Log.d(TAG, "onBindViewHolder: called "+counter_bindMyViewHolder);

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView holidayName;
        private TextView holidayDate;

        public MyViewHolder(View itemView){
            super(itemView);
            holidayName = itemView.findViewById(R.id.holiday_name_text_view);
            holidayName.setOnClickListener(this);
            holidayDate = itemView.findViewById(R.id.holiday_date_text_view);

        }

        void bind (int position){
            holidayName.setText(listHolidaysInfo.listHolidaysInfo[position].getHoliday_name());
            holidayDate.setText(listHolidaysInfo.listHolidaysInfo[position].getHoliday_date());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnclickListener.onListItemClick(position);
        }
    }

}
