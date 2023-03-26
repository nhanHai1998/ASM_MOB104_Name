package com.example.asm_mob104_name.API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.MainActivity2;
import com.example.asm_mob104_name.Mode.User;
import com.example.asm_mob104_name.SignUpFragment;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PostDataSignIn extends AsyncTask<String, Integer, String> {

    SignUpFragment context;


    public PostDataSignIn(SignUpFragment context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        StringBuilder builder;
        try {
             url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            JSONObject posData = new JSONObject();
            posData.put("fullName",context.edt_fullTen.getText().toString() );
            posData.put("email",context.edt_email.getText().toString() );
            posData.put("passwd",context.edt_mk.getText().toString() );
            posData.put("username",context.edt_ten.getText().toString() );

            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));


            bufferedWriter.append(posData.toString());

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            builder = new StringBuilder();
            String dong;
            while ((dong = reader.readLine()) != null) {
                builder.append(dong).append("\n");
            }
            reader.close();
            inputStream.close();
            connection.disconnect();
            Log.d("post", "Backgroud tra về " + builder.toString());





        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        context.edt_email.setText("");
        context.edt_fullTen.setText("");
        context.edt_mk.setText("");
        context.edt_ten.setText("");
        Toast.makeText(context.getContext(), "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
    }
}
