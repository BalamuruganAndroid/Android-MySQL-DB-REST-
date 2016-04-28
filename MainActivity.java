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

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	String name;
	String id;
	InputStream is=null;
	String result1=null;
	String line=null;
	int code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//For building strict mode
				if( Build.VERSION.SDK_INT >= 9)
				{
			            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			            StrictMode.setThreadPolicy(policy); 
			    }		
        final EditText e_id=(EditText) findViewById(R.id.editText1);
        final EditText e_name=(EditText) findViewById(R.id.editText2);
        Button insert=(Button) findViewById(R.id.button1);
        Button back = (Button)findViewById(R.id.backInsert);
        insert.setOnClickListener(new View.OnClickListener() 
        {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				
			id = e_id.getText().toString();
			name = e_name.getText().toString();
			
			insert();
		}
	});
		
        
	}
	 public void insert()
	 {
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    	nameValuePairs.add(new BasicNameValuePair("id",id));
	    	nameValuePairs.add(new BasicNameValuePair("name",name));
	    	
	    	try
	    	{
	    		HttpClient httpclient = new DefaultHttpClient();
	    		System.out.println("Http clent" + httpclient);
		        HttpPost httppost = new HttpPost("http://192.168.5.61/create.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		        Log.e("pass 1", "connection success ");
		}
	        catch(Exception e)
		{
	        	e.printStackTrace();
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
	            result1 = sb.toString();
		    Log.e("pass 2", "connection success ");
		}
	        catch(Exception e)
		{
	            Log.e("Fail 2", e.toString());
		}     
	       
		try
		{
	           JSONObject json_data = new JSONObject(result1);
	            code=(json_data.getInt("code"));
				
	            if(code==1)
	            {
	            	Toast.makeText(getBaseContext(), "Inserted Successfully",
	            	Toast.LENGTH_SHORT).show();
	            }
	            else
	            {
	            	Toast.makeText(getBaseContext(), "Sorry, Try Again",
	            	Toast.LENGTH_LONG).show();
	            }
		}
		catch(Exception e)
		{
	            Log.e("Fail 3", e.toString());
		}
   }
	

}
