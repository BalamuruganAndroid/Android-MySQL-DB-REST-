package com.example.androidmysqldb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends Activity 
{
	
	String id;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		 final EditText e_id=(EditText) findViewById(R.id.detelId);
	        Button delete=(Button) findViewById(R.id.Delete);
	        Button back = (Button)findViewById(R.id.backDelete);
	        delete.setOnClickListener(new View.OnClickListener() 
	        {
				
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub				
				id=e_id.getText().toString();
				delete();
			}
		});
	        
	}
	public void delete()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("id",id));
    	
    	try
    	{
    		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://192.168.5.62/delete.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
    	}
        catch(Exception e)
    	{
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
			Toast.LENGTH_LONG).show();
    	}     
        
        try
        {
            	BufferedReader reader = new BufferedReader
				(new InputStreamReader(is,"iso-8859-1"),8);
            	StringBuilder sb = new StringBuilder();
            	while ((line = reader.readLine()) != null)
		{
        	    sb.append(line + "\n");
    		}
            	is.close();
            	result = sb.toString();
	        Log.e("pass 2", "connection success ");
	}
        catch(Exception e)
    	{
        	Log.e("Fail 2", e.toString());
    	}     
       
	try
    	{
        	JSONObject json_data = new JSONObject(result);
        	code=(json_data.getInt("code"));
        	if(code==1)
        	{
			Toast.makeText(getBaseContext(),"Record Deleted",
				Toast.LENGTH_SHORT).show();
        	}
        	else
        	{
    			Toast.makeText(getBaseContext(),"Sorry, Try Again",
				Toast.LENGTH_SHORT).show();
        	}
    	}
        catch(Exception e)
    	{
        	Log.e("Fail 3", e.toString());
    	}
    }
}
