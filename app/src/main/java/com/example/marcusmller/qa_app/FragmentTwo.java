package com.example.marcusmller.qa_app;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentTwo extends Fragment implements View.OnClickListener {

    public FragmentTwo() {
        // Required empty public constructor
    }

    ListView lvLink;
    static int listPosition;

    static ArrayList<String> list = new ArrayList<String>();
    static ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_two, container, false);

        lvLink = (ListView) v.findViewById(R.id.lvLink);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lvLink.setAdapter(adapter);

        lvLink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = lvLink.getItemAtPosition(position);
                listPosition = position;
                String url = listItem.toString().substring(listItem.toString().lastIndexOf("►")+1).toString();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);       //öffnet Browserfenster

            }
        });

        Button b = v.findViewById(R.id.btnLink);
        b.setOnClickListener(this);
        return v;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLink:
                Intent intent = new Intent(getActivity(), AddLink.class);
                startActivity(intent);
                break;
        }
    }
}