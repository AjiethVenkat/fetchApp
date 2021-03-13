package edu.newhaven.fetchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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


        StringRequest request = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<ItemModel> tempList = new ArrayList<>();

                try {
                    /* The JSON response is treated as object */
                    JSONObject json_Object = new JSONObject(response);
                    JSONArray user_ArrayObject = json_Object.getJSONArray("");

                    for (int i = 0; i < user_ArrayObject.length(); i++) {
                        JSONObject single_User_Object = user_ArrayObject.getJSONObject(i);

                        ItemModel model = new ItemModel();

                        /* Did not retrieve id from JSON since it was not required */
                        model.id = single_User_Object.getInt("id");
                       // model.listId = single_User_Object.getInt("listId");
                       // model.name = single_User_Object.getString("name");

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
}