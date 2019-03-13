package com.example.amr.connecttomysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;


public class MainActivity extends AppCompatActivity {
    private static final String url = "jdbc:mysql://192.168.1.8:3305/design_home";
    private static final String user = "root";
    private static String pass = "";
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);

        new Mytask().execute();


    }

    private class Mytask extends AsyncTask<Void, Void, Void> {
        String testStr;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Connection con = DriverManager.getConnection(url, user, pass);
                Connection con= DriverManager.getConnection(url,user,pass);
                Statement st = con.createStatement();
                String sql = "SELECT * FROM `eng` WHERE eng_id=1";

                final ResultSet rs = st.executeQuery(sql);
                rs.next();

                testStr=rs.getString(2); //amr
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            test.setText(testStr);

            super.onPostExecute(aVoid);
        }
    }

}
