package edu.berkeley.cs160.off_by_1;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class VoiceReceiveFragment extends ReceiveFragment{

	TextView time;
	Button playButton;
	String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/voicemessage.mp4";
	MediaRecorder recorder;
	MediaPlayer player;
	ProgressBar progress;
	Handler handler = new Handler();
	Timer timer;
	int record = 0;

	AsyncTask<Integer, Integer, Integer> task;

	int x = 0;
	long delay;
	long cap;
	boolean cancel = false;


	boolean playing = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("debug", "inflating voice message");
		View v = inflater.inflate(R.layout.receive_voice, container, false);
		Log.d("debug", "inflated view" );

		playButton = (Button) v.findViewById(R.id.playButton);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playMessage(playing);
				playing = !playing;
			}
		});

		
		time = (TextView) v.findViewById(R.id.playTimer);
		setTime(":15");

		progress = (ProgressBar) v.findViewById(R.id.playProgressBar);
		timer = new Timer();

		cap = progress.getMax();
		delay = (15 * 1000) / cap;
		Log.d("debug", "inititalized everything" + playButton + time + progress);
		return v;
	}

	void startPlaying() {
		player = new MediaPlayer();
		try {
			playButton.setText("Stop Playing");
			player.setDataSource(fileName);
			player.prepare();
			player.start();
			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer arg0) {
					playing = false;
					playButton.setText("Play Button");
				}
			});
		} catch (IOException e) {
			Log.e("error", "voicemessage player.prepare() failed");
		}

	}

	void stopPlaying() {
		playButton.setText("Play Button");
		player.release();
		player = null;
	}

	void playMessage(boolean stop) {
		if (stop) {
			stopPlaying();
		} else { 
			startPlaying();
		}
	}


	void setTime(String s) {
		time.setText(s);
	}

	void setProgressTask() {
		task = new AsyncTask<Integer, Integer, Integer>() {
			int p = 0;

			@Override
			protected Integer doInBackground(Integer... arg0) {
				while (p < cap && !cancel) {
					try {
						Thread.sleep(delay);
					} catch (Exception e) {
					}
					p++;
					publishProgress(p);
				}
				return null;
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				setTime(":" + String.valueOf(15-p*15/cap));
				handler.post(new Runnable() {
					public void run() {
						progress.setProgress(p);
					}
				});
			}

			@Override
			protected void onPostExecute(Integer x) {
				stopPlaying();
			}
		};
	}

}
