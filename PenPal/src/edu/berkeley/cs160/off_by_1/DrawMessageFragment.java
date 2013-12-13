package edu.berkeley.cs160.off_by_1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DrawMessageFragment extends MessageFragment {
	CustomView CusView;
	public Context context;
	CustomView first = null;
	CustomView Second = null;
	CustomView Third = null;
	OnTouchListener touchListener;   
	int color = Color.BLACK;
	RelativeLayout layout;  
	private Paint paint = new Paint();
	private Path path = new Path();
    ImageView image;
    ArrayList<View> views;
	Map<Path, Paint> pathMap;
	Context act;
	
	public class CustomView extends View {
        public CustomView(Context context) {
        	super(context);
        	
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(12);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
        	canvas.drawPath(path, paint);
        	super.onDraw(canvas);
        	for (Map.Entry<Path, Paint> p : pathMap.entrySet()) {
                canvas.drawPath(p.getKey(), p.getValue());
            }
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
		super.onCreate(savedInstanceState);
		if (act == null) {
			Log.d("debug", "it's nulllllllllllllllllllllllllllllllll");
		} else {
			Log.d("debug", "itllll");
		}
		CusView = new CustomView(act);
		View v = inflater.inflate(R.layout.draw_message, container, false);
		layout = (RelativeLayout) v.findViewById(R.id.kk);
		views = new ArrayList<View>();
        pathMap = new HashMap<Path, Paint>();
		Log.d("debug", "generated view");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        CusView.setLayoutParams(params);
        layout.addView(CusView);
        touchListener = new OnTouchListener() {
        	public boolean onTouch(View v, MotionEvent event) {
        		CusView.setDrawingCacheEnabled(true);
        		float eventX = event.getX();
        	    float eventY = event.getY();
        	    switch (event.getAction()) {
        	    case MotionEvent.ACTION_DOWN:
        	    	path = new Path();
                    path.reset();
    				path.moveTo(eventX, eventY);
    		        break;
        	    case MotionEvent.ACTION_MOVE:
        	    case MotionEvent.ACTION_UP:
        	    	Paint newPaint = new Paint();
        	        newPaint.set(paint); 
        	        pathMap.put(path, newPaint);
    				path.lineTo(eventX, eventY);
    		        break;
        		} // ends switch statement
        	    CusView.invalidate();
        	    return true;
        	}
        };
        CusView.setOnTouchListener(touchListener);
        return v;
    }
	
    public void clear() {
		for (Map.Entry<Path, Paint> p : pathMap.entrySet()) {
            p.getKey().rewind();
            CusView.invalidate();
            CusView.setDrawingCacheEnabled(false);
      }
	}
    
    Intent getIntent() {
    	String msg = "";
    	Intent i = new Intent();
    	i.putExtra("msg", msg);
    	return i;
    }
}
