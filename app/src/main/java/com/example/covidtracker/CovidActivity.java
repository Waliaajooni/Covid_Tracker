package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CovidActivity extends AppCompatActivity {

    int cid=0;
    AboutCovid currentI;
    TextView txtTitle;
    TextView covInfo;
    TextView cnter;
    ImageView butNext;

    final ArrayList<AboutCovid> infoList  = new ArrayList<>();
    AboutCovid sym = new AboutCovid("1/3",
                                    "Some common symptoms that have been specifically linked to COVID-19 include: shortness of breath, a cough that gets more severe over time, fever, chills, fatigue \n Less common symptoms include: repeated shaking with chills sore throat headache muscle aches and pains loss of taste or smell a stuffy or runny nose gastrointestinal symptoms such as diarrhea, nausea, and vomiting",
                                    "Symptoms");

    String causeContent = "Coronaviruses are zoonotic. This means they first develop in animals before being transmitted to humans.For the virus to be transmitted from animals to humans, a person has to come into close contact with an animal that has the infection. Once the virus develops in people, coronaviruses can be transmitted from person to person through respiratory droplets viral material hangs out in these droplets.";
    AboutCovid causes = new AboutCovid("2/3", causeContent, "Causes");

    String prevContent = "The best way to prevent from infecting from COVID 19 is through social distancing and prevent going to places which has crowd. Also as an individual we can wear mask and avoid the spread of Covid 19 and by this we can keep us as well as our family safe. Wash your hands often with soap and water for at least 20 seconds especially after you have been in a public place, or after blowing your nose, coughing, or sneezing.\n" +
            "You should get a COVID-19 vaccine when it is available to you.";
    AboutCovid prev = new AboutCovid("3/3", prevContent, "Prevention");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);

        txtTitle = (TextView) findViewById(R.id.title);
        covInfo = (TextView) findViewById(R.id.covid_content);
        cnter = (TextView) findViewById(R.id.counter);
        butNext = (ImageView) findViewById(R.id.conext);

        infoList.add(sym);
        infoList.add(causes);
        infoList.add(prev);

        currentI=infoList.get(cid);
        setCovidView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cid < infoList.size()){
                    currentI=infoList.get(cid);
                    setCovidView();
                }
            }
        });
    }

    private void setCovidView()
    {
        txtTitle.setText(currentI.getHeading());
        cnter.setText(currentI.getCnt());
        covInfo.setText(currentI.getContent());
        cid++;
    }
}
