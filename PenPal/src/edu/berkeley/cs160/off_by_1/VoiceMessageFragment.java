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

public class VoiceMessageFragment extends MessageFragment {

	TextView time;
	Button recordButton;
	Button playButton;
	String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/voicemessage.mp4";
	MediaRecorder recorder;
	MediaPlayer player;
	ProgressBar progress;
	Handler handler = new Handler();
	Timer timer;
	int record = 0;
	String startPlay;
	AsyncTask<Integer, Integer, Integer> task;
	int x = 0;
	long delay;
	long cap;
	boolean cancel = false;

	boolean recording = false;
	boolean playing = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		View v = inflater.inflate(R.layout.voice_message, container, false);
		startPlay = act.getString(R.string.start_play);
		recordButton = (Button) v.findViewById(R.id.recordButton);
		
		recordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				recordMessage(recording);
				recording = !recording;
			}
		});
		//setPlayButton();

		time = (TextView) v.findViewById(R.id.recordTimer);
		setTime(":15");

		progress = (ProgressBar) v.findViewById(R.id.recordProgressBar);
		timer = new Timer();

		cap = progress.getMax();
		delay = (15 * 1000) / cap;
		return v;
	}

	void startRecording() {
		record++;
		recordButton.setText(act.getString(R.string.stop_record));
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		recorder.setOutputFile(fileName);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			recorder.prepare();
		} catch (IOException e) {
			Log.e("error", "voicemessage recorder.prepare() failed");
		}
		cancel = false;
		setProgressTask();
		task.execute();
		recorder.start();
	}


	void stopRecording() {
		record++;
		if (recorder != null) {
			recorder.stop();
			recorder.release();
			recorder = null;
		}
		cancel = true;
		//recordButton.setText("Record Again");
		recordButton.setVisibility(View.INVISIBLE);

	}

	void startPlaying() {
		player = new MediaPlayer();
		try {
			playButton.setText(act.getString(R.string.stop_play));
			player.setDataSource(fileName);
			player.prepare();
			player.start();
			player.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer arg0) {
					playing = false;
					playButton.setText(startPlay);
				}
			});
		} catch (IOException e) {
			Log.e("error", "voicemessage player.prepare() failed");
		}

	}

	void stopPlaying() {
		playButton.setText(startPlay);
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

	void setPlayButton() {
		playButton = (Button) act.findViewById(R.id.playButton);
		playButton.setVisibility(View.VISIBLE);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playMessage(playing);
				playing = !playing;
			}
		});
	
	}

	void recordMessage(boolean stop) {
		if (stop) {
			stopRecording();
		} else {
			startRecording();
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
				stopRecording();
				setPlayButton();
			}
		};
	}

	Intent getIntent() {
		Intent i = new Intent();
		return i;
	}

	@Override
	void clear() {
		if(recorder != null) {
			recorder.stop();
		}
		timer = new Timer();
		File f = new File(fileName);
		f.delete();
		record = 0;
		recordButton.setText(act.getString(R.string.start_record));
		recordButton.setVisibility(View.VISIBLE);
		playButton.setVisibility(View.INVISIBLE);
		progress.setProgress(0);
		time.setText(":15");
	}
}
