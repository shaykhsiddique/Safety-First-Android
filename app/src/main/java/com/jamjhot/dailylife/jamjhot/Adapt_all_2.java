package com.jamjhot.dailylife.jamjhot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Tanin on 5/19/2016.
 */
public class Adapt_all_2 extends ArrayAdapter<String> {

    LinkedList<String> gojol_n1,gojol_n2,gojol_n3 = new LinkedList<>();
    int[] logo;

    Context c;
    LayoutInflater inflater;
    public Adapt_all_2(Context context, LinkedList<String> gojol_n_st1, LinkedList<String> gojol_n_st2, LinkedList<String> gojol_n_st3, String[] bla) {
        super(context, R.layout.adapt_all_2, bla);
        this.c = context;
        //this.no= no;
        this.gojol_n1 = gojol_n_st1;
        this.gojol_n2 = gojol_n_st2;
        this.gojol_n3 = gojol_n_st3;
        this.logo = logo;
    }
    public void addAlldata(Context context, LinkedList<String> gojol_n_st1, LinkedList<String> gojol_n_st2, LinkedList<String> gojol_n_st3, String[] bla) {
        this.c = context;
        //this.no= no;
        this.gojol_n1.clear();
        this.gojol_n2.clear();
        this.gojol_n3.clear();
        this.gojol_n1 = gojol_n_st1;
        this.gojol_n2 = gojol_n_st2;
        this.gojol_n3 = gojol_n_st3;


        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView tvNo, tvName1,tvName2,tvName3, tvArabic;
        ImageView leftLogo, rightLogo;
        RelativeLayout carrier;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView== null)
        {
            inflater= (LayoutInflater) c.getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.adapt_all_2,null);
        }

        final ViewHolder holder= new ViewHolder();
       // holder.tvNo= (TextView) convertView.findViewById(R.id.tvNo);
        holder.tvName1= (TextView) convertView.findViewById(R.id.tvName1);
        holder.tvName2= (TextView) convertView.findViewById(R.id.tvName2);
        holder.tvName3= (TextView) convertView.findViewById(R.id.tvName3);
        holder.carrier= (RelativeLayout) convertView.findViewById(R.id.carrier);
//        holder.leftLogo= (ImageView) convertView.findViewById(R.id.leftLogo);
//        holder.rightLogo= (ImageView) convertView.findViewById(R.id.rightLogo);


       // holder.tvNo.setText(no[position]);
        if (position==0){

            holder.carrier.setEnabled(false);
            holder.carrier.setBackgroundResource(R.drawable.btncolor_action);
        }
        else {
            holder.carrier.setBackgroundResource(R.drawable.btnstateupdated);
            holder.carrier.setEnabled(true);
        }

        holder.tvName1.setText(gojol_n1.get(position));
        holder.tvName2.setText(gojol_n2.get(position));
        holder.tvName3.setText(gojol_n3.get(position));
//        holder.leftLogo.setImageResource(logo[position]);
//        holder.rightLogo.setImageResource(logo[position]);

        return convertView;
    }


}
