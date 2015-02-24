package com.example.mysql_crud;

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
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import com.example.mysql_crud.R;


import android.R.string;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String name;
	String id;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText e_id=(EditText) findViewById(R.id.editText1);
        final EditText e_name=(EditText) findViewById(R.id.editText2);
        Button insert=(Button) findViewById(R.id.button1);
        
        insert.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				
			id = e_id.getText().toString();
			name = e_name.getText().toString();
			
			new insert().execute();
		}
	});
    }
 
/*
    public void insert()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
   	nameValuePairs.add(new BasicNameValuePair("id",id));
   	nameValuePairs.add(new BasicNameValuePair("name",name));
    	
    	try
    	{
		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost("http://192.168.1.15/insert.php");
	        
	        Log.e("tag","1");
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        Log.e("tag","2");
	        
	        HttpResponse response = httpclient.execute(httppost); 
	        
	        Log.e("tag","3");
	        
	        HttpEntity entity = response.getEntity();
	        
	        Log.e("tag","4");
	        
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	}
        catch(Exception e)
	{
        	Log.e("Fail 1", e.toString());
        	e.printStackTrace();
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

  */
    
    
    class insert extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() 
        {
        super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg0)
        {
        	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        	 
           	nameValuePairs.add(new BasicNameValuePair("id",id));
           	nameValuePairs.add(new BasicNameValuePair("name",name));
            	
        	try
        	{
        		HttpClient httpclient = new DefaultHttpClient();
        		HttpContext localContext = new BasicHttpContext();
    	        HttpPost httppost = new HttpPost("http://192.168.1.15/insert.php");    	        
    	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    	        HttpResponse response = httpclient.execute(httppost,localContext); 
    	        HttpEntity entity = response.getEntity();
    	        is = entity.getContent();
    	        Log.e("pass 1", "connection success ");
        	}
            catch(Exception e)
        	{
                	Log.e("Fail 1", e.toString());
                	e.printStackTrace();
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
        		Log.e("Tag",result);
        		final String temp = "{\"code\":1}"+"\n";
        		Log.e("Tag",temp);
        		runOnUiThread(new Runnable()
        		{
        			@Override
        			public void run()
        			{
        				 if(result.equals(temp))
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
        		});
        		
        	}
        	catch(Exception e)
        	{
                    Log.e("Fail 3", e.toString());
                    e.printStackTrace();
        	}
        	return null;
        }

        @Override
        protected void onPostExecute(String result) 
        {
            super.onPostExecute(result);

        }
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }    
}