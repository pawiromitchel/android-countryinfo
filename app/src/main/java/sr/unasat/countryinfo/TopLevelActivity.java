package sr.unasat.countryinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sr.unasat.countryinfo.dto.CountryDto;

public class TopLevelActivity extends Activity {

    private TextView MessageTextView;
    private ListView ListCounties;

    private List<CountryDto> countryDtoList;
    private ArrayAdapter<CountryDto> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        ListCounties = (ListView) findViewById(R.id.ListCounties);
        MessageTextView = (TextView) findViewById(R.id.MessageTextView);

        // Get all data from source
        getCountryData();
    }

    private void getCountryData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        final String URL = "https://restcountries.eu/rest/v2/all";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        countryDtoList = mapJsonToCountryObject(response);

                        populateCountryListView(countryDtoList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MessageTextView.setText("something went wrong");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void populateCountryListView(final List<CountryDto> countryDtoList){
        // convert List to ArrayAdapter
        adapter = new ArrayAdapter<>(TopLevelActivity.this, android.R.layout.simple_list_item_1, countryDtoList);

        // convert de ArrayAdapter van de CountryDto naar list
        ListCounties.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CountryDto countryData = null;

                Intent intent  = new Intent(TopLevelActivity.this, CountryActivity.class);
                intent.putExtra("countryData", countryDtoList.get(i));
                startActivity(intent);
            }
        };

        // assign the itemclick listener to the listView
        ListCounties.setOnItemClickListener(itemClickListener);
    }


    private List<CountryDto> mapJsonToCountryObject(String jsonArray) {
        ObjectMapper mapper = new ObjectMapper();
        List<CountryDto> countryList = new ArrayList<>();
        List<Map<String, ?>> countryArray = null;
        CountryDto country = null;

        try {
            countryArray = mapper.readValue(jsonArray, List.class);
            for (Map<String, ?> map : countryArray) {
                country = new CountryDto((String) map.get("name"), (String) map.get("capital"), (String) map.get("region"));
                countryList.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Er is wat fout gegaan bij het parsen van de json data");
        }
        return countryList;
    }

//    public void searchCountry(View view) {
//        getCountryData(CountryNameEditText.getText().toString());
//    }
}
