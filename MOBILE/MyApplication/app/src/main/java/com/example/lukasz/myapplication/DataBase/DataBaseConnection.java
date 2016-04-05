package com.example.lukasz.myapplication.DataBase;

import android.os.AsyncTask;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * Created by Lukasz on 2016-04-01.
 */
public class DataBaseConnection extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... params) {
        String link=params[0];
        String data="";
        try {
            for(int i=1;i<params.length;i++){
                data += URLEncoder.encode(params[i], "UTF-8") + "=" + URLEncoder.encode(params[++i], "UTF-8");
                if(i!=params.length-1)data+="&";
            }
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            System.out.println("Error: "+e);
            return "blad odczytu z bazy danych";
        }
    }
}
