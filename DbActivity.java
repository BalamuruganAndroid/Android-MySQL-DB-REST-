package com.example.androidmysqldb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DbActivity extends Activity 
{
	Button btnInsert,btnUpdate,btnDelete,btnSelect;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_functionality);
		
		btnInsert.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent insertIntent = new Intent(DbActivity.this,MainActivity.class);
				startActivity(insertIntent);	
			}
		});
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent updateIntent = new Intent(DbActivity.this,UpdateActivity.class);
				startActivity(updateIntent);
			}
		});
		btnSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent selectIntent = new Intent(DbActivity.this,SelectActivity.class);
				startActivity(selectIntent);
			}
		});
		//btnUpdate.seto
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent deleteIntent = new Intent(DbActivity.this,DeleteActivity.class);
				startActivity(deleteIntent);
			}
		});
	}

}
