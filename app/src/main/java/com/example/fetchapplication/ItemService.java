package com.example.fetchapplication;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ItemService {

    private ListView itemListView;

    public static final String HTTPS_FETCH_HIRING_S_3_AMAZONAWS_COM_HIRING_JSON = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private List<Item> itemList;

    Context context;
    public ItemService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(List<Item> itemList);
    }
    public void getData(VolleyResponseListener volleyResponseListener){
        itemList = new ArrayList<>();
        String url = HTTPS_FETCH_HIRING_S_3_AMAZONAWS_COM_HIRING_JSON;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    try {
                        // Group items by listId
                        Map<Integer, List<Item>> groupedItems = new HashMap<>();

                        for (int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            int listId = jsonObject.getInt("listId");
                            String name = jsonObject.getString("name");


                            // Filter out items with blank names
                            if (!name.isEmpty() && name!="null") {
                                Item item = new Item(listId, name);
//                                Toast.makeText(context, "id: " + item.getName(), Toast.LENGTH_SHORT).show();

                                if (!groupedItems.containsKey(listId)) {
                                    groupedItems.put(listId, new ArrayList<>());
                                }

                                groupedItems.get(listId).add(item);
                            }
                        }
                        // Populate itemList with grouped items
                        for (List<Item> items : groupedItems.values()) {
                            itemList.addAll(items);
                        }

                        //Just for the
//                        for (int i = 0; i < itemList.size(); i++) {
//                            Item item = itemList.get(i);
//                            Toast.makeText(context, "id:  " + item.getListId() + item.getName(), Toast.LENGTH_SHORT).show();
//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    volleyResponseListener.onResponse(itemList);
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Unable to fetch");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
//        return itemList;
    }

    public void getSortedData(VolleyResponseListener volleyResponseListener){
        itemList = new ArrayList<>();
        String url = HTTPS_FETCH_HIRING_S_3_AMAZONAWS_COM_HIRING_JSON;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int listId = jsonObject.getInt("listId");
                        String name = jsonObject.getString("name");

                        // Filter out items with blank or null names
                        if (name != "null" && !name.isEmpty()) {
                            itemList.add(new Item(listId, name));
                        }
                    }

                    // Sort the items by listId and then by name
                    Collections.sort(itemList, new Comparator<Item>() {
                        @Override
                        public int compare(Item item1, Item item2) {
                            if (item1.getListId() != item2.getListId()) {
                                return Integer.compare(item1.getListId(), item2.getListId());
                            } else {
                                return item1.getName().compareTo(item2.getName());
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(itemList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Unable to fetch");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
//        return itemList;
    }

}
