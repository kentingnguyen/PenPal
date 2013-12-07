package edu.berkeley.cs160.off_by_1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TextMessageFragment extends MessageFragment {
	
	EditText editText;
	
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                             Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View v = inflater.inflate(R.layout.text_message, container, false);
	        editText = (EditText) v.findViewById(R.id.editTextMessage);
	        return v;
	    }
	    
	    Intent getIntent() {
	    	Log.d("debug", "edittext" + editText);
	    	String msg = editText.getText().toString();
	    	Intent i = new Intent();
	    	i.putExtra("msg", msg);
	    	return i;
	    	
	    }
	}

