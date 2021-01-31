package com.example.gradecalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class gradesStatistics extends AppCompatActivity {
    ArrayList<Double> studentsGradesInfor;
    CalculatorDatabaseHelper calculatorDatabaseHelper;
    String SessionStudentID;
    DataPoint[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_statistics);

        GraphView graph = findViewById(R.id.graph);
        
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(gradesStatistics.this);
        final SharedPreferences.Editor editor= sharedPreferences.edit();
        SessionStudentID = sharedPreferences.getString("SessionStudentID","0");

        calculatorDatabaseHelper = new CalculatorDatabaseHelper(this);

        studentsGradesInfor = calculatorDatabaseHelper.getStats(SessionStudentID);

        data = new DataPoint[studentsGradesInfor.size()];

        for(int i=0; i<studentsGradesInfor.size(); i++){
            data[i] = new DataPoint(i, (double) studentsGradesInfor.get(i));
            Log.d("CheckGet", String.valueOf(studentsGradesInfor.get(i)));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);

    }
}