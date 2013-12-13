package edu.berkeley.cs160.off_by_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TextReceiveFragment extends ReceiveFragment {
	TextView text;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.receive_text, container, false);
        //text = (TextView) v.findViewById(R.id.textMessage);
        return v;
    }

}
