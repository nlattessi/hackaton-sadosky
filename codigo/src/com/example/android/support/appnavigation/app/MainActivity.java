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

import java.util.ArrayList;

import com.example.android.support.appnavigation.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends Activity {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	CustomGridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		// set grid view item
		Bitmap subeIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.sube);
		Bitmap ollaIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.olla);
		Bitmap diarioIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.diario);
		Bitmap todasIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.todas);

		Intent target = new Intent();
		target.setClassName("com.example.android.support.appnavigation",
				"com.example.android.support.appnavigation.app.CargarSubeActivity");
		gridArray.add(new Item(subeIcon, "Recargar la SUBE", target));

		target = new Intent();
		target.setClassName("com.example.android.support.appnavigation",
				"com.example.android.support.appnavigation.app.CocinarFideosActivity");
		gridArray.add(new Item(ollaIcon, "Cocinar fideos", target));

		target = new Intent();
		target.setClassName("com.example.android.support.appnavigation",
				"com.example.android.support.appnavigation.app.ListaTareasActivity");
		gridArray.add(new Item(diarioIcon, "Que hago hoy", target));

		target = new Intent();
		target.setClassName("com.example.android.support.appnavigation",
				"com.example.android.support.appnavigation.app.ListaTareasActivity");

		gridArray.add(new Item(todasIcon, "Todas mis tareas", target));

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Item it = gridArray.get(position);
				startActivity(it.getIntent());
			}
		});
	}

	public void llamarA(View view) {
		try {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:123123123123"));			
			startActivity(callIntent);
		} catch (ActivityNotFoundException e) {
			Log.e("CALL", "no anduvo");
		}
	}
}
