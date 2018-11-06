package com.jamjhot.dailylife.jamjhot;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Tanin on 5/19/2016.
 */
public class Adapt_all_calc extends ArrayAdapter<String> {

    LinkedList<String> gojol_n = new LinkedList<>();
    int[] logo;

    Context c;
    LayoutInflater inflater;
    public Adapt_all_calc(Context context, LinkedList<String> gojol_n_st, String[] bla) {
        super(context, R.layout.adapt_all_calc, bla);
        this.c = context;
        //this.no= no;
        this.gojol_n = gojol_n_st;
        this.logo = logo;
    }

    public class ViewHolder{
        TextView tvNo, tvName, tvArabic;
        ImageView leftLogo, rightLogo;
        RelativeLayout carrier;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView== null)
        {
            inflater= (LayoutInflater) c.getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.adapt_all_calc,null);
        }

        final ViewHolder holder= new ViewHolder();
       // holder.tvNo= (TextView) convertView.findViewById(R.id.tvNo);
        holder.tvName= (TextView) convertView.findViewById(R.id.tvName);
        holder.carrier= (RelativeLayout) convertView.findViewById(R.id.carrier);
//        holder.leftLogo= (ImageView) convertView.findViewById(R.id.leftLogo);
//        holder.rightLogo= (ImageView) convertView.findViewById(R.id.rightLogo);


       // holder.tvNo.setText(no[position]);

        if (position==0 || position==2 || position==7 || position==9){

            holder.carrier.setEnabled(false);

            holder.carrier.setBackgroundResource(R.drawable.background);
            holder.tvName.setTextColor(Color.parseColor("#000000"));
        }
        else {
            holder.carrier.setBackgroundResource(R.drawable.btnstateupdated);
            holder.carrier.setEnabled(true);
            holder.tvName.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.tvName.setText(gojol_n.get(position));





//        holder.leftLogo.setImageResource(logo[position]);
//        holder.rightLogo.setImageResource(logo[position]);

        return convertView;
    }


}
