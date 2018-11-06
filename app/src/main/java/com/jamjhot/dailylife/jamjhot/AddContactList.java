package com.jamjhot.dailylife.jamjhot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.LinkedList;

public class AddContactList extends AppCompatActivity {

    Button addContact;
    DbHelperVab dbHelper;
    Adapt_all_2 adapter2;
    ListView listData;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_list);

        addContact =(Button) findViewById(R.id.addtoList);
        add =(Button) findViewById(R.id.add);
        listData = (ListView) findViewById(R.id.listData);

        dbHelper= new DbHelperVab(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterName(AddContactList.this, AddContactList.this,dbHelper);
            }
        });
        loadData();


    }


    LinkedList<String> nameList= new LinkedList<>();
    LinkedList<String> phoneList= new LinkedList<>();
    LinkedList<String> numbers= new LinkedList<>();
    void loadData(){
        String dataDB = "আপডেট বাটন চাপুন";
        numbers.clear();
        nameList.clear();
        phoneList.clear();

        numbers.add("Position");
        nameList.add("Name");
        phoneList.add("Phone");



        int k=0;
        Cursor cursor=dbHelper.getAllHOMEDATAs();
        if (cursor.moveToFirst()) {
            do {
                numbers.add(""+(k++));
                nameList.add(cursor.getString(1));
                phoneList.add(cursor.getString(2));

//                }
                //                quest.setDATA(cursor.getString(2));
//                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        String[] totalData= new String[nameList.size()];

        adapter2 = new Adapt_all_2(AddContactList.this,nameList,phoneList,numbers,totalData);
        listData.setAdapter(adapter2);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listData.setEnabled(false);

            }
        });

    }

    void loadDataAgain(){
        String dataDB = "আপডেট বাটন চাপুন";
        numbers.clear();
        nameList.clear();
        phoneList.clear();


        numbers.add("Position");
        nameList.add("Name");
        phoneList.add("Phone");



        int k=0;
        Cursor cursor=dbHelper.getAllHOMEDATAs();
        if (cursor.moveToFirst()) {
            do {
                numbers.add(""+(k++));
                nameList.add(cursor.getString(1));
                phoneList.add(cursor.getString(2));

//                }
                //                quest.setDATA(cursor.getString(2));
//                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        String[] totalData= new String[nameList.size()];

        adapter2.addAlldata(AddContactList.this,nameList,phoneList,numbers,totalData);

//        adapter2.notifyDataSetChanged();

    }



    public static void enterName(final AddContactList mainActivity, final Context context,final DbHelperVab dbHelper) {
        AlertDialog.Builder mBuilder= new AlertDialog.Builder(context);
//        mBuilder.setCancelable(false);
        View mView = mainActivity.getLayoutInflater().inflate(R.layout.nameenter,null);
//        final TextView tvDetail= (TextView) mView.findViewById(R.id.tvDetail);

        final EditText phone_num=mView.findViewById(R.id.phone);
        final EditText name =mView.findViewById(R.id.name);
        final Button addContact =mView.findViewById(R.id.addtoList);
//        final Button moreA= (Button) mView.findViewById(R.id.more_a);
//        final ListView listData=(ListView) mView.findViewById(R.id.listData);







        mBuilder.setView(mView);


        final AlertDialog dialog = mBuilder.create();

//                UpdateData("don't Show",1,0);
//                editor.putInt("dontshowagain", 1);
//                editor.commit();

//                noWay=true;
//
//                SharedPreferences pref_3 = context2.getSharedPreferences("myPrefsKeyAR", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2 = pref_3.edit();
//
//                editor2.putBoolean("keyAR", noWay);
//                Log.e("okkkkkkk",""+noWay);




                addContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nameStr= name.getText().toString();
                        String phoneStr= phone_num.getText().toString();


                        if (nameStr.equals("")|| phoneStr.equals("")){
                            Toast.makeText(mainActivity,"Wrong Input",Toast.LENGTH_LONG).show();
                        }else {

                            dbHelper.addHOMEDATA(nameStr,phoneStr);

                            Toast.makeText(mainActivity,"Saved!",Toast.LENGTH_LONG).show();
                        }

                        dialog.dismiss();
                        Intent intent = new Intent(mainActivity,AddContactList.class);
                        mainActivity.startActivity(intent);
                    }
                });



//        moreA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                try {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:AppsArena")));
//                } catch (android.content.ActivityNotFoundException anfe) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=AppsArena")));
//                }
//
//            }
//        });

        if(!((Activity) context).isFinishing())
        {
            dialog.show();
            //show dialog
        }
        else if (((Activity) context).isFinishing()){
            dialog.dismiss();
        }


    }


    @Override
    public void onBackPressed() {
        Intent intent= new Intent(AddContactList.this, HomeActivity.class);
        startActivity(intent);
    }
}
