package com.example.tapesh.seerviceproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener {
   EditText editTextname,editText2password;
    Button buttonsave;
    String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextname=(EditText)findViewById(R.id.editTextname);
        editText2password=(EditText)findViewById(R.id.editText2password);

        buttonsave=(Button)findViewById(R.id.buttonsave);

        buttonsave.setOnClickListener(this);

    }


    @Override
    public void onClick(View view)
    {
        new AsynewTask().execute();





    }

private class AsynewTask extends AsyncTask<Void,Void,Void>
{

    @Override
    protected void onPreExecute()
    {
       name=editTextname.getText().toString();
       password=editText2password.getText().toString();

    }
    @Override
    protected Void doInBackground(Void...argo)
    {

        JSONObject jo = new JSONObject();

        try
        {


            jo.put("name",name);
            jo.put("password",password);


            Log.i("name",name);
            Log.i("password",password);


            URL url = new URL("http://192.168.0.17:8081/");
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(url.toURI());

            httpPost.setEntity(new StringEntity(jo.toString(), "UTF-8"));
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept-Encoding", "application/json");
            httpPost.setHeader("Accept-Language", "en-US");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // HttpResponse response=httpClient.execute(httpPost,responseHandler);
           String response=httpClient.execute(httpPost,responseHandler);


            Log.i("response",response);


        }catch(Exception e)
        {
            Log.i("error",e.toString());

        }




        return null;
    }


}


}
