package com.mpersson.myrecipeorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Custom Adapter receives the sub items array
// The Custom Adapter also sets the image for each item in the grid
// The Adapter is attached to the GridView

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[]pic;
    String[] foodName;


    private LayoutInflater inflater;

    CustomAdapter(Context context, String[]foodName, int[]pic){
        this.context = context;
        this.foodName = foodName;
        this.pic = pic;

    }




    @Override
    public int getCount() {
        return foodName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.sample_view, viewGroup, false );
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.ImageViewId);
        TextView textView = (TextView)view.findViewById(R.id.textViewId);
        imageView.setImageResource(pic[i]);
        textView.setText(foodName[i]);
        return view;
    }
}
