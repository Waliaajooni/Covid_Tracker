package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidtracker.api.ApiUtilities;
import com.example.covidtracker.api.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView totalConfirm, totalActive, totalRecovered, totalDeaths, totalTests;
    private TextView todayConfirm, todayRecovered, todayDeath, dateTV;

    private List<CountryData> list;
    private PieChart pieChart;

    String country = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        if (getIntent().getStringExtra("country") != null) {
            country = getIntent().getStringExtra("country");
        }

        init();


        findViewById(R.id.covid_info).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CovidActivity.class)));

        TextView cname = findViewById(R.id.country_name);
        cname.setText(country);

        findViewById(R.id.country_name).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CountryActivity.class)));

        ApiUtilities.getApiInterface().getCountryData()
                     .enqueue(new Callback<List<CountryData>>() {
                         @Override
                         public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                             list.addAll(response.body());

                             for (int i = 0; i < list.size(); i++) {
                                 if (list.get(i).getCountry().equals(country)) {
                                     int confirm = Integer.parseInt(list.get(i).getCases());
                                     int active = Integer.parseInt(list.get(i).getActive());
                                     int recovered = Integer.parseInt(list.get(i).getRecovered());
                                     int deaths = Integer.parseInt(list.get(i).getDeaths());


                                     totalActive.setText(NumberFormat.getInstance().format(active));
                                     totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                     totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                     totalDeaths.setText(NumberFormat.getInstance().format(deaths));

                                     todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                     todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                     todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                     totalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                                     setDate(list.get(i).getUpdated());

                                     pieChart.addPieSlice(new PieModel("Confirm", confirm, getResources().getColor(R.color.colorYellow)));
                                     pieChart.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.colorBlue)));
                                     pieChart.addPieSlice(new PieModel("Recovered", recovered, getResources().getColor(R.color.colorGreen)));
                                     pieChart.addPieSlice(new PieModel("Deaths", deaths, getResources().getColor(R.color.colorRed)));

                                     pieChart.startAnimation();
                                 }
                             }


                         }

                         @Override
                         public void onFailure(Call<List<CountryData>> call, Throwable t) {
                             Toast.makeText(MainActivity.this, "Error : " +t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     });
    }

    private void setDate(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");

        long milliseconds = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        dateTV.setText(format.format(calendar.getTime()));
    }


    private void init() {
        totalConfirm = (TextView) findViewById(R.id.totalConfirm);
        totalActive = (TextView) findViewById(R.id.totalActive);
        totalRecovered = (TextView) findViewById(R.id.totalRecovered);
        totalDeaths = (TextView) findViewById(R.id.totalDeaths);
        todayConfirm = (TextView) findViewById(R.id.todayConfirm);
        todayRecovered = (TextView) findViewById(R.id.todayRecovered);
        todayDeath = (TextView) findViewById(R.id.todayDeaths);
        totalTests = (TextView) findViewById(R.id.totalTests);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        dateTV = (TextView) findViewById(R.id.date);

    }
}
