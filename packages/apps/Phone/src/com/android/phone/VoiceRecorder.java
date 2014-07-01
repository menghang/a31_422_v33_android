/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.phone;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/**
 * lkj
 */
public class VoiceRecorder implements OnErrorListener {

    public static String TAG= "MyRecorderActivity";
    
    static final String SAMPLE_PREFIX = "recording";
    File mSampleFile = null;
    
    MediaRecorder mRecorder = null;

    public static final int IDLE_STATE = 0;
    public static final int RECORDING_STATE = 1;
    public static final int ERROR_STATE = -1;
	private Time time;

    int mState = IDLE_STATE;

    public VoiceRecorder() {
		time = new Time();
		Log.d(TAG, "VoiceRecorder...");
    }

    public int getState(){
		return mState;
   }
  public void startRecording(int outputfileformat, String extension) {
	Log.d(TAG, "startRecording");
        stop();
        mState = IDLE_STATE;
      //  if (mSampleFile == null) {
            File sampleDir = Environment.getExternalStorageDirectory();
            if (!sampleDir.canWrite()) // Workaround for broken sdcard support on the device.
                sampleDir = new File("/sdcard/sdcard");
            
        	time.setToNow();
		//mSampleFile = new File(sampleDir.getPath(), time.format("%Y-%m-%d-%H-%M-%S"));
	//	mSampleFile = new File(time.format("%Y-%m-%d-%H-%M-%S"));
        try {
            //mRecorder.prepare();
			mSampleFile = new File(sampleDir.getPath(), time.format("%Y-%m-%d-%H-%M-%S") + ".3gp");
			Log.d(TAG, "audio file" + mSampleFile.getPath() );
        } catch(Exception exception) {
				Log.d(TAG, "create file exception");
				mState = ERROR_STATE;
                return;
            }
     //   }
        
        mRecorder = new MediaRecorder();
       // mRecorder.setOnErrorListener((android.media.MediaRecorder.OnErrorListener) this);
        
  //      mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mRecorder.setOutputFormat(outputfileformat);
  //lkj add  Log.d(TAG, "!!!!!!!!!!!!!!! mRecorder.setAudioChannels(1) ");
      //lkj add    mRecorder.setAudioChannels(1);    //amr_nb support max 1 channel.
     //   mRecorder.setOutputFile("/sdcard/lkj.3gp");
   mRecorder.setAudioChannels(1);    //amr_nb support max 1 channel.
        mRecorder.setOutputFile(mSampleFile.getPath());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//MediaRecorder.AudioEncoder.AMR_NB
//	mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
 
        // Handle IOException
        try {
            mRecorder.prepare();
        } catch(IOException exception) {
            Log.d(TAG, "prepare RECORDER exception");
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
            mState = IDLE_STATE;
            return;
        }
        // Handle RuntimeException if the recording couldn't start
        try {
            mRecorder.start();
            mState = RECORDING_STATE;
        } catch (RuntimeException exception) {
            Log.d(TAG, "startRecording exception");
           // AudioManager audioMngr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
            //boolean isInCall = ((audioMngr.getMode() == AudioManager.MODE_IN_CALL));
            //if (isInCall) {

//            } else {

  //          }
            mRecorder.reset();
            mRecorder.release();
            mRecorder = null;
            mState = IDLE_STATE;
            return;
        }

    }
    
    public void stopRecording() {
	Log.d(TAG, "stopRecording "+mRecorder);
        if (mRecorder == null)
            return;

        mRecorder.stop();
	mRecorder.reset();
        mRecorder.release();
        mRecorder = null;
        mState = IDLE_STATE;
	if (mSampleFile.exists()){
		Log.d(TAG, "rm  file "+mSampleFile.toString());
		mSampleFile.delete();
	}
    }
    
    public void stop() {
        stopRecording();
    }
    
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "onError");
        stop();
	mState = ERROR_STATE;
        return true;
    }

}