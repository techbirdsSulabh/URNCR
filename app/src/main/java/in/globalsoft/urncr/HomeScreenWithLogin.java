package in.globalsoft.urncr;

import in.globalsoft.beans.BeansLogin;
import in.globalsoft.urncr.R;
import in.globalsoft.preferences.AppPreferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeScreenWithLogin extends Activity 
{
	Button btn_logOut,btnChat;
	AppPreferences appPref;
	BeansLogin loginBeans;
	TextView tv_userName;
	Button btn_painMgmtSpecialities,btn_findPhysian,btn_findFacility,btn_search_doctor;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen_with_login);
		appPref = new AppPreferences(this);
		
		btn_logOut = (Button) findViewById(R.id.logout_btn);
		btn_painMgmtSpecialities = (Button) findViewById(R.id.painMgmtSpecBtn);
		btn_findPhysian = (Button) findViewById(R.id.find_physician);
		btn_findFacility= (Button) findViewById(R.id.find_facility);
		tv_userName = (TextView)findViewById(R.id.username_text);
		btnChat=(Button) findViewById(R.id.btnChat);
		setUserName();
		
btn_search_doctor=(Button)findViewById(R.id.btnSearchDoctor);
		
		btn_search_doctor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(HomeScreenWithLogin.this, SearchDoctor.class);
				startActivity(i);
				
			}
		});
		
		btn_painMgmtSpecialities.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				Intent i = new Intent(HomeScreenWithLogin.this,HospitalList.class);
                appPref.saveMapType(getString(R.string.map_name));
				startActivity(i);
				
			}
		});
		
		btnChat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showChatSelectionDialog();
				
			}
		});
		
		
		btn_findFacility.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(HomeScreenWithLogin.this,FacilityList.class);
				startActivity(i);
				
			}
		});
		btn_findPhysian.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(HomeScreenWithLogin.this,ListBusinessPersons.class);
			
				startActivity(i);
				
			}
		});
		
		
		btn_logOut.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				
				appPref.clearPref();
				Intent i = new Intent(HomeScreenWithLogin.this,HomeScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
				finish();
				
			}
		});
		
	}
	
	public void setUserName()
	{
		String userLoginInfo = appPref.getUserLoginInfo();
		Gson gson = new Gson();
		loginBeans = gson.fromJson(userLoginInfo, BeansLogin.class);
		tv_userName.setText("Welcome "+loginBeans.getFirst_name()+" "+loginBeans.getLast_name());
	}

    //dialog for showing chat selection
    public void showChatSelectionDialog()
    {
        final ArrayList<String> listChattype = new ArrayList<String>();
        listChattype.add("Chat with Doctor");
        listChattype.add("Chat with Doctor Office");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, listChattype);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Chat type:");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                if (item == 0) {

                    // TODO Auto-generated method stub
                    Intent i = new Intent(HomeScreenWithLogin.this,RecentChatActivity.class);
                    startActivity(i);

                }
                else if (item == 1) {

                    // TODO Auto-generated method stub
                    Intent i = new Intent(HomeScreenWithLogin.this,RecentOfficeChat.class);
                    startActivity(i);


                }


            }
        });

        Dialog dialogChatType = builder.create();
        dialogChatType.show();
    }




}
