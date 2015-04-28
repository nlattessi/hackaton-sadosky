/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.example.android.support.appnavigation.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.example.android.support.appnavigation.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CamTestActivity extends Activity {
	private static final String TAG = "CamTestActivity";
	Preview preview;
	Button buttonClick;
	Camera camera;
	Activity act;
	Context ctx;
	byte[] dataG;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		act = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.simple_up);

		preview = new Preview(this,
				(SurfaceView) findViewById(R.id.surfaceView));
		preview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		((FrameLayout) findViewById(R.id.layoutCamara)).addView(preview);
		preview.setKeepScreenOn(true);

		preview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				camera.takePicture(shutterCallback, rawCallback, jpegCallback);
			}
		});

		Toast.makeText(ctx, getString(R.string.take_photo_help),
				Toast.LENGTH_LONG).show();

		// buttonClick = (Button) findViewById(R.id.btnCapture);
		//
		// buttonClick.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// // preview.camera.takePicture(shutterCallback, rawCallback,
		// jpegCallback);
		// camera.takePicture(shutterCallback, rawCallback, jpegCallback);
		// }
		// });
		//
		// buttonClick.setOnLongClickListener(new OnLongClickListener(){
		// @Override
		// public boolean onLongClick(View arg0) {
		// camera.autoFocus(new AutoFocusCallback(){
		// @Override
		// public void onAutoFocus(boolean arg0, Camera arg1) {
		// //camera.takePicture(shutterCallback, rawCallback, jpegCallback);
		// }
		// });
		// return true;
		// }
		// });
	}

	@Override
	protected void onResume() {
		super.onResume();
		// preview.camera = Camera.open();
		camera = Camera.open();
		camera.startPreview();
		preview.setCamera(camera);
	}

	@Override
	protected void onPause() {
		if (camera != null) {
			camera.stopPreview();
			preview.setCamera(null);
			camera.release();
			camera = null;
		}
		super.onPause();
	}

	private void resetCam() {
		camera.startPreview();
		preview.setCamera(camera);
	}

	private void refreshGallery(File file) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		mediaScanIntent.setData(Uri.fromFile(file));
		sendBroadcast(mediaScanIntent);
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			// Log.d(TAG, "onShutter'd");
		}
	};

	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// Log.d(TAG, "onPictureTaken - raw");
		}
	};

	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			FrameLayout fl = (FrameLayout) findViewById(R.id.layoutCamara);
			LinearLayout ll = new LinearLayout(ctx);

			Button btnSi = new Button(ctx);
			btnSi.setId(345);

			btnSi.setText("guardar");
			btnSi.setBackgroundColor(Color.rgb(70, 80, 90));
			btnSi.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Button b = (Button) findViewById(345);
					((LinearLayout) b.getParent()).removeAllViews();
					new SaveImageTask().execute(dataG);
					resetCam();
				}
			});

			ll.addView(btnSi, params);

			Button btnNo = new Button(ctx);

			btnNo.setText("descartar");
			btnNo.setBackgroundColor(Color.rgb(70, 80, 90));
			btnNo.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Button b = (Button) findViewById(345);
					((LinearLayout) b.getParent()).removeAllViews();
					resetCam();
				}
			});

			params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 40, 0, 0);
			ll.addView(btnNo, params);

			fl.addView(ll);

			dataG = data;

			// new SaveImageTask().execute(data);
			// resetCam();
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};

	private class SaveImageTask extends AsyncTask<byte[], Void, Void> {

		@Override
		protected Void doInBackground(byte[]... data) {
			FileOutputStream outStream = null;

			// Write to SD Card
			try {
				File sdCard = Environment.getExternalStorageDirectory();
				File dir = new File(sdCard.getAbsolutePath() + "/Apoyo");
				dir.mkdirs();
				Random r = new Random();
				int i1 = r.nextInt(100 - 1);
				String fileName = i1 + ".jpg";
				File outFile = new File(dir, fileName);

				outStream = new FileOutputStream(outFile);
				outStream.write(data[0]);
				outStream.flush();
				outStream.close();

				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length
						+ " to " + outFile.getAbsolutePath());

				refreshGallery(outFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			return null;
		}

	}
}
