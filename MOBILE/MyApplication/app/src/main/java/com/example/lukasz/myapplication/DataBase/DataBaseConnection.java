package com.example.lukasz.myapplication.dataBase;

import android.os.AsyncTask;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 *  Odczyt i zapis danych do bazy. Przy odczycie podajemy tylko ścieżkę do pliku .php,
 *  przy zapisie pierwszy parametr to ścieżka do pliku .php, później nazwa zmiennej i zmienna.
 *  np.
 *  ... new DatabaseConnection.execute("mobile/save.php", nazwa_zmiennej, zmienna, nazwa_zmiennej, zmienna itd...);
 */
public class DataBaseConnection extends AsyncTask<String,Void,String> {

    private String serverAdres = "http://192.168.0.2:80/";

    @Override
    protected String doInBackground(String... params) {
        String link=serverAdres+params[0];
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
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            reader.close();
            return sb.toString();
        }
        catch(Exception e){
            System.out.println("Error: "+e);
            return "blad odczytu z bazy danych";
        }
    }
}
