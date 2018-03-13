package com.example.marcusmller.qa_app;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentOne extends Fragment implements View.OnClickListener {

   public FragmentOne(){}

    public ListView lv01;
    static int listPosition;

   static ArrayList<String> list = new ArrayList<String>();
   static ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_one, container, false);

        TextView txtName = v.findViewById(R.id.textViewName);
        txtName.setText("Hallo "+Login.eingabeMail);

        lv01 = (ListView) v.findViewById(R.id.lv01);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lv01.setAdapter(adapter);

        lv01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = lv01.getItemAtPosition(position);
                listPosition = position;
                Intent intent = new Intent(getActivity(), Antwort.class);
                startActivity(intent);
            }
        });

        Button b = v.findViewById(R.id.btnFrage);
        b.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFrage:
                Intent intent = new Intent(getActivity(), FrageStellen.class);
                startActivity(intent);
            break;
        }
    }
}