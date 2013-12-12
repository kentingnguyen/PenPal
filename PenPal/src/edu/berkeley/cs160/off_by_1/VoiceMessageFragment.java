	package edu.berkeley.cs160.off_by_1;


import java.io.File;
import java.io.IOException;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class VoiceMessageFragment extends MessageFragment {
		    
		    Button recordButton;
		    Button playButton;
		    String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/voicemessage.mp4";
		    MediaRecorder recorder;
		    MediaPlayer player;
        	int record = 0;

		    @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                             Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		    	Log.d("debug", "inflating voice message");
		    	View v = inflater.inflate(R.layout.voice_message, container, false);
		    	recordButton = (Button) v.findViewById(R.id.recordButton);
		    	recordButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (record == 0) {
							record++;
							recordButton.setText("Stop Button");
							startRecording();
						} else if (record == 1){
							record++;
							stopRecording();
							recordButton.setText("Done Recording");
						}
					}
				});
		    	
		    	playButton = (Button) v.findViewById(R.id.playButton);
		    	recordButton.setOnClickListener(new View.OnClickListener() {
		    		boolean playing = false;
					@Override
					public void onClick(View v) {
						playMessage(playing);
						playing = !playing;
					}
				});
		        
		        return v;
		    }
		    
		    void startRecording() {
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
		        recorder.start();
		    }
		    
		    void stopRecording() {
		    	recorder.stop();
		        recorder.release();
		        recorder = null;
		    }
		    
		    void startPlaying() {
		    	player = new MediaPlayer();
		    	try {
		            player.setDataSource(fileName);
		            player.prepare();
		            player.start();
		        } catch (IOException e) {
		            Log.e("error", "voicemessage player.prepare() failed");
		        }

		    }
		    
		    void stopPlaying() {
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
		    
		    Intent getIntent() {
		    	Intent i = new Intent();
		    	return i;
		    }
		    
		    void clear() {
		    	File f = new File(fileName);
		    	f.delete();
		    	record = 0;
		    	recordButton.setText("Record Button");
		    }
		}
