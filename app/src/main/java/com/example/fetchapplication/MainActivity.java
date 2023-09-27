package com.example.fetchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_sortByIDandName;
    ListView listResults;

    TextView message;
    ItemService itemService = new ItemService(MainActivity.this);
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control in the layout
        btn_sortByIDandName = findViewById(R.id.btn_sortByIDandName);
        message = findViewById(R.id.message);

        listResults = findViewById(R.id.listResults);
        itemService.getData(new ItemService.VolleyResponseListener() {
                     @Override
                     public void onError(String message) {
                         Toast.makeText(MainActivity.this, "id:  ", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onResponse(List<Item> itemList) {
                         //Toast.makeText(MainActivity.this, "Received  " + itemList.size(), Toast.LENGTH_SHORT).show();
                         List<String> elements = new ArrayList<>();
                         for(int i =0; i<itemList.size(); i++){
                             Item item = itemList.get(i);
                             int id = item.getListId();
                             elements.add(" ID: "+Integer.toString(item.getListId()) +" Name: " + item.getName());
                         }
                             ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, elements);
                             listResults.setAdapter(arrayAdapter);
                     }
                 });
//                 Toast.makeText(MainActivity.this, "id:  " + itemList.size(), Toast.LENGTH_SHORT).show();
//                 for(int i=0; i<itemList.size(); i++){
//                     Item item = itemList.get(i);
//                     Toast.makeText(MainActivity.this, "id:  " + item.getListId() + item.getName(), Toast.LENGTH_SHORT).show();
//
//                 }


         btn_sortByIDandName.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 btn_sortByIDandName.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                 message.setText("Elements Sorted by ID and Name");
                 itemService.getSortedData(new ItemService.VolleyResponseListener() {
                     @Override
                     public void onError(String message) {
                         Toast.makeText(MainActivity.this, "id:  ", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onResponse(List<Item> itemList) {
                         List<String> elements = new ArrayList<>();
                         for(int i =0; i<itemList.size(); i++){
                             Item item = itemList.get(i);
                             int id = item.getListId();
                             elements.add(" ID: "+Integer.toString(item.getListId()) +" Name: " + item.getName());
                         }
                         ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, elements);
                         listResults.setAdapter(arrayAdapter);

                     }
                 });
//                 Toast.makeText(MainActivity.this, "Clicked btn 2", Toast.LENGTH_SHORT).show();
             }
         });

    }
}