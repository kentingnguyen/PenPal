package edu.berkeley.cs160.off_by_1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Header extends Fragment {

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        return inflater.inflate(R.layout.fragment_header, container, false);
	    }	
	 
	 public void goToHome(View v) {
		 //finish();	 
	 }
	 
	 
	 public void goBack(View v) {
		//finish(); 
	 }
		 
	 
}
