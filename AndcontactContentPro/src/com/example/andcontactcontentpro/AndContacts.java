package com.example.andcontactcontentpro;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class AndContacts extends TabActivity {
Button buttonsave;
	TabHost mTabHost = null;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        mTabHost = getTabHost(); 
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Contacts", getResources().getDrawable(R.drawable.contact)).setContent(R.id.contactsLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Music", getResources().getDrawable(R.drawable.music)).setContent(R.id.musicLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Video", getResources().getDrawable(R.drawable.video)).setContent(R.id.videoLayout)); 
         
        mTabHost.setCurrentTab(0); 
         buttonsave = (Button) findViewById(R.id.buttonsave);
         buttonsave.setOnClickListener(new AddButtonClickHandler());
    }
    public class AddButtonClickHandler implements OnClickListener {
		public void onClick(View view) {
			
			  // Add a new student record
		      ContentValues values = new ContentValues();

		      values.put(StudentsProvider.NAME, 
		      ((EditText)findViewById(R.id.txtname)).getText().toString());
		      
		      values.put(StudentsProvider.PHONE, 
		      ((EditText)findViewById(R.id.txtphone)).getText().toString());
		      
		      values.put(StudentsProvider.EMAIL, 
				      ((EditText)findViewById(R.id.txtemail)).getText().toString());
		      
		      values.put(StudentsProvider.POSTAL, 
				      ((EditText)findViewById(R.id.txtpostaladdress)).getText().toString());

		      Uri uri = getContentResolver().insert(
		      StudentsProvider.CONTENT_URI, values);
		      
		      Toast.makeText(getBaseContext(), 
		      uri.toString(), Toast.LENGTH_LONG).show();
		}

	
    }
}

   

