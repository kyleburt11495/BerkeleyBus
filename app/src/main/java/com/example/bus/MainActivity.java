package com.example.bus;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bus.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String routeSelected;
    private String directionSelected;
    private String stopSelected;
    private TextView arrivalTimeTextView;
    private TextView arrivalTimeTextView2;
    private TextView arrivalTimeTextView3;
    private Spinner routeSpinner;
    private Spinner directionSpinner;
    private Spinner stopSpinner;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        arrivalTimeTextView = (TextView) findViewById(R.id.arrivalTimeView);
        arrivalTimeTextView2 = (TextView) findViewById(R.id.secondArrivalTimeView);
        arrivalTimeTextView3 = (TextView) findViewById(R.id.thirdArrivalTimeView);

        routeSpinner = (Spinner) findViewById(R.id.routeSpinner);
        directionSpinner = (Spinner) findViewById((R.id.directionSpinner));
        stopSpinner = (Spinner) findViewById(R.id.stopSpinner);

        routeSpinner.setOnItemSelectedListener(new routeSpinnerListener());
        ArrayAdapter<CharSequence> routeAdapter = ArrayAdapter.createFromResource(this,
                R.array.ucbRouteArray, android.R.layout.simple_spinner_item);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(routeAdapter);

        new ArrivalTimesTask().execute("http://www.nextbus.com/customStopSelector/fancyNewPredictionLayer.jsp?a=ucb&r=peri&d=loop&s=berkbart_d&ts=berkbart_d");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_launch_schedule:
                Intent intent = new Intent(this, ClassScheduleActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }


    public class ArrivalTimesTask extends AsyncTask<String, Void, List<String>>
    {
        @Override
        protected List<String> doInBackground(String... params)
        {
            List<String> arrivalTimeResults = null;
            arrivalTimeResults = NetworkUtils.getArrivalTimes(params[0]);


            return arrivalTimeResults;
        }

        @Override
        protected void onPostExecute(List<String> arrivalTime)
        {
            if(arrivalTime == null)
            {
                arrivalTimeTextView.setText("No current prediction");
                arrivalTimeTextView2.setText("No current prediction");
                arrivalTimeTextView3.setText("No current prediction");
            }
            else
            {

                arrivalTimeTextView.setText(arrivalTime.get(0));
                if(arrivalTime.size()>=2)
                    arrivalTimeTextView2.setText(arrivalTime.get(1));
                if(arrivalTime.size()==3)
                    arrivalTimeTextView3.setText(arrivalTime.get(2));
            }
        }
    }


    public class routeSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            routeSelected = parent.getItemAtPosition(position).toString();
            directionSpinner.setOnItemSelectedListener(new directionSpinnerListener());
            //Hill was selected
            if(position == 1)
            {
                ArrayAdapter<CharSequence> directionAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbDirectionUphillDownhill, android.R.layout.simple_spinner_item);
                directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                directionSpinner.setAdapter(directionAdapter);
            }
            else
            {
                                                                                                //MainActivity.this
                ArrayAdapter<CharSequence> directionAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbDirectionLoop, android.R.layout.simple_spinner_item);
                directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                directionSpinner.setAdapter(directionAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //do nothing
        }
    }

    public class directionSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            directionSelected = parent.getSelectedItem().toString();
            stopSpinner.setOnItemSelectedListener(new stopSpinnerListener());

            if(routeSelected.equals("Hill") && directionSelected.equals("Uphill"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbUphillStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Hill") && directionSelected.equals("Downhill"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbDownhillStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Perimeter"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbPerimeterStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Central Campus"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbCentralCampusStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("RFS"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbRFSStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Night Safety North"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbNightSafetyNorthStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Night Safety South"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbNightSafetySouthStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else if(routeSelected.equals("Shared Services"))
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbSharedServicesStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }
            else
            {
                ArrayAdapter<CharSequence> stopAdapter = ArrayAdapter.createFromResource(getBaseContext(),
                        R.array.ucbSharedServicesStops, android.R.layout.simple_spinner_item);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
            }



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //do nothing
        }
    }

    public class stopSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            stopSelected = parent.getSelectedItem().toString();
            String url = NetworkUtils.getAddress(routeSelected,directionSelected,position);
            new ArrivalTimesTask().execute(url);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            //do nothing
        }
    }



}
