package com.example.asm_mob104_name.API;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.asm_mob104_name.LogInFragment;
import com.example.asm_mob104_name.Mode.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataLogin extends AsyncTask<String, Void, Integer> {

    LogInFragment context;
    List<User> userList;

    public GetDataLogin(LogInFragment context) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String noiDung = "";
        try {

            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String dong ;
            while ((dong = reader.readLine()) != null){
                builder.append(dong)
                        .append("\n");
            }

            reader.close();
            inputStream.close();
            connection.disconnect();
            noiDung = builder.toString();
            userList = new ArrayList();
            JSONArray array = new JSONArray(noiDung);

            for (int i = 0 ; i <array.length() ; i++) {

                JSONObject object = array.getJSONObject(i);
               User user = new User();
                user.username = (String) object.get("username");
                user.password = (String) object.get("passw");


                if(user.username.equals(context.edt_ten.getText().toString()) && user.password.equals(context.edt_mk.getText().toString())){
                    userList.add(user);
                    return userList.size();
                }


            }



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Log.d("list", "doInBackground: "+ userList.size());


        return userList.size();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(context.getContext(), "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();


    }
}
