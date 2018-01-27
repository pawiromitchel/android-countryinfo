package sr.unasat.countryinfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;

import sr.unasat.countryinfo.dto.CountryDto;

public class CountryActivity extends Activity {

    private TextView countryNameText;
    private TextView countryCapitalText;
    private TextView countryRegionText;


    private Serializable countryObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countryNameText = (TextView) findViewById(R.id.countryNameText);
        countryCapitalText = (TextView) findViewById(R.id.countryCapitalText);
        countryRegionText = (TextView) findViewById(R.id.countryRegionText);

        // get intent data from TopLevelActivity
        CountryDto countryObject = (CountryDto)getIntent().getSerializableExtra("countryData");

        // set text to object
        countryNameText.setText(countryObject.getName());
        countryCapitalText.setText(countryObject.getCapital());
        countryRegionText.setText(countryObject.getRegion());
    }


}
