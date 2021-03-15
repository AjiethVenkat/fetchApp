package edu.newhaven.fetchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Adapter itemAdapter;
    private RequestQueue requestQueue;
    private final String JSON_URL = " https://fetch-hiring.s3.amazonaws.com/hiring.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        itemAdapter = new Adapter();


        recyclerView.setAdapter(itemAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        JsonArrayRequest request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ItemModel> tempList = new ArrayList<>();
                List<UserInfo> listId = new ArrayList<>();
                try {

                   for (int i=0;i < response.length();i++) {

                       /* The JSON response is treated as object */
                       JSONObject json_Object = response.getJSONObject(i);
                       String n = json_Object.getString("listId");
                       String objId = json_Object.getString("id");
                       String objName = json_Object.getString("name");

                       if(!objName.equals("null") && !objName.isEmpty()) {
                           listId.add(new UserInfo(objId, n, objName));
                       }
                       Collections.sort(listId, new Comparator<UserInfo>() {
                           @Override
                           public int compare(UserInfo t0, UserInfo t1) {
                               return t0.listId.compareTo(t1.listId);
                           }
                       });
                   }

                   Log.d("MainActivity", String.valueOf(listId.get(0).listId));

                   for(int j=0;j<listId.size();j++){
                       ItemModel model = new ItemModel();
                       model.id = listId.get(j).id;
                       model.listId = listId.get(j).listId;
                       model.name = listId.get(j).name;
                       tempList.add(model);
                   }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /* Sending the data to adapter to populate each cell */
                itemAdapter.setItem_Model_List(tempList);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private class UserInfo {
        String id,listId,name;
        public UserInfo(String id, String listId,String name) {
            this.listId=listId;
            this.id=id;
            this.name =name;
        }
    }
}

