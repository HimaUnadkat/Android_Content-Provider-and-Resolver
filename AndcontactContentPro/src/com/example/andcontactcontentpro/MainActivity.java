package com.example.andcontactcontentpro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btnwrite, btnshow, btnclear;
	ListView list_data;
	List<Pojo> list = null;
	List<Pojo>list1=null;
	DataAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnwrite = (Button) findViewById(R.id.btnwrite);
		btnshow = (Button) findViewById(R.id.btnshow);
		btnclear = (Button) findViewById(R.id.btnclear);
		// btnwrite.setOnClickListener(new AddButtonClickHandler());
		// btnshow.setOnClickListener(new ShowButtonClickHandler());
		list_data= (ListView)findViewById(R.id.list_data);
		btnwrite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent explicitIntent = new Intent(MainActivity.this,
						AndContacts.class);
				startActivity(explicitIntent);
			}
		});
		
		
		btnclear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
				String URL = "content://com.example.andcontactcontentpro.College/students";
			    Uri mUri = Uri.parse(URL); 
			    getContentResolver().delete(mUri, null, null);
			}
		});
		
		list = new ArrayList<Pojo>();
		btnshow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Retrieve student records
				String URL = "content://com.example.andcontactcontentpro.College/students";
				Uri students = Uri.parse(URL);
				Cursor c = managedQuery(students, null, null, null, "name");
				list.clear();
				if (c.moveToFirst()) {
					do {
						
						Pojo pojo = new Pojo();
						
						pojo.setId(c.getString(c
								.getColumnIndex(StudentsProvider._ID)));
						pojo.setName(c.getString(c
										.getColumnIndex(StudentsProvider.NAME)));
						pojo.setNumber(c.getString(c
										.getColumnIndex(StudentsProvider.PHONE)));
						pojo.setEmail(c.getString(c
										.getColumnIndex(StudentsProvider.EMAIL)));
						pojo.setPostal(c.getString(c
								.getColumnIndex(StudentsProvider.POSTAL)));
						
						list.add(pojo);
						
					} while (c.moveToNext());
				}
					
				
				adapter = new DataAdapter(getApplicationContext());
				list_data.setAdapter(adapter);
				
			}
		});
	}


	
	public class DataAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public DataAdapter(Context c) {
			mInflater = LayoutInflater.from(c);

		}

		@Override
		public int getCount() {
			// return branchlist.size();
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			convertView = mInflater.inflate(R.layout.custom_row_view, null);

			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.name);

			holder.phone = (TextView) convertView
					.findViewById(R.id.phone);
			holder.email = (TextView) convertView
					.findViewById(R.id.email);
			holder.postal = (TextView) convertView
					.findViewById(R.id.postal);

			holder.name.setText(""+list.get(position).getName());
			holder.phone.setText(""+list.get(position).getNumber());
			holder.email.setText(""+list.get(position).getEmail());
			holder.postal.setText(""+list.get(position).getPostal());
			
			return convertView;
		}

		class ViewHolder {
			TextView name, phone, email,postal;

		}
	}
}
