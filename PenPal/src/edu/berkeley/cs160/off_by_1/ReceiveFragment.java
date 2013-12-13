package edu.berkeley.cs160.off_by_1;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class ReceiveFragment extends Fragment{

	ReceiveFragment fragment;
	boolean draw = false;
	Activity act;

	public ReceiveFragment() {

	}
	public ReceiveFragment(String type) {
		if (type.equals("text")) {
			fragment = new TextReceiveFragment();
			
			//fragment = null;
		} else if (type.equals("voice")) {
			fragment = new VoiceReceiveFragment();
			Log.d("debug", "made voice frag" + fragment);
		} else if (type.equals("draw")) {
			//fragment = new DrawReceiveFragment();
			//Log.d("debug", "hi");
			//draw = true;
			fragment = null;
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		act = activity;
	}


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.text_message, container, false);	
			fragment.onAttach(act);
			return fragment.onCreateView(inflater, container, savedInstanceState);
	
	}


	//		public static Fragment getFragment(String type) {
}


