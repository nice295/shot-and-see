package com.nice295.shotandsee;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.util.Arrays;

public class ShotAndSeeApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		// enable the Local Datastore
		Parse.enableLocalDatastore(getApplicationContext());
		Parse.initialize(this, "---", "---");
		ParseInstallation.getCurrentInstallation().put("channels", Arrays.asList("Android", "ShotAndSee"));
		ParseInstallation.getCurrentInstallation().saveInBackground(); //for push
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		ParseACL.setDefaultACL(defaultACL, true);
	}
}
