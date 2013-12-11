package edu.berkeley.cs160.off_by_1;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment{

	MessageFragment fragment;
	
	public MessageFragment() {
		
	}
	
    public MessageFragment(String type) {
		if (type.equals("text")) {
			fragment = new TextMessageFragment();
		} else if (type.equals("voice")) {
			fragment = new VoiceMessageFragment();
		} else if (type.equals("draw")) {
			fragment = new DrawMessageFragment();
		}
	}

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.text_message, container, false);
		return fragment.onCreateView(inflater, container, savedInstanceState);
	}
	
	Intent getIntent() {
		return fragment.getIntent();
	}
	
	void clear() {
		fragment.clear();
		
	}
        
//	public static Fragment getFragment(String type) {
}
