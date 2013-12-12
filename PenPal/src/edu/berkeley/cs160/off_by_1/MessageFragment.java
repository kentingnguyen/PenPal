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
public class MessageFragment extends Fragment{

	MessageFragment fragment;
	boolean draw = false;
	Activity con;
	
	public MessageFragment() {
		
	}
    public MessageFragment(String type) {
		if (type.equals("text")) {
			fragment = new TextMessageFragment();
		} else if (type.equals("voice")) {
			fragment = new VoiceMessageFragment();
		} else if (type.equals("draw")) {
			fragment = new DrawMessageFragment();
			Log.d("debug", "hi");
			draw = true;
		}
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        con = activity;
        Log.d("debug", "hello onattach");
    }
    
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.text_message, container, false);
		
		if (draw) {
			Log.d("debug", "it's drawing");
			Log.d("debug", getActivity().toString());
			onAttach(getActivity());
			fragment.onAttach(con);
			draw = false;
			return fragment.onCreateView(inflater, container, savedInstanceState);
		}
		return fragment.onCreateView(inflater, container, savedInstanceState);
	}
	
	private void set(Context con2) {
		// TODO Auto-generated method stub
		
	}
	Intent getIntent() {
		return fragment.getIntent();
	}
	
	void clear() {
		fragment.clear();
		
	}
        
//	public static Fragment getFragment(String type) {
}
