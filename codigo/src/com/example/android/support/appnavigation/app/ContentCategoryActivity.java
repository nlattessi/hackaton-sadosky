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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.android.support.appnavigation.R;

@SuppressLint("NewApi")
public class ContentCategoryActivity extends Activity {

	private Button bt;
	private ListView lv;
	private ArrayList<String> strArr;
	// private ArrayAdapter<String> adapter;
	private SimpleCursorAdapter adapter;
	private EditText et;
	private Context context = this;
	private Cursor cursor;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_category);

		bt = (Button) findViewById(R.id.button1);
		lv = (ListView) findViewById(R.id.listView1);
		et = (EditText) findViewById(R.id.editText1);

		strArr = new ArrayList<String>();

		listAll();

		// cunado hace click en la lista tengo q borrarla? menu con opciones
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				String selectedFromList = (String) (lv
						.getItemAtPosition(myItemInt));

				// a modo ejemplo aca agrego uno mas a la lista. pero iria lo q
				// tengo q hacer
				// ACA BORRO
			}
		});

		// cunado hace click en el boton...
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DataBaseManager manager = new DataBaseManager(context);
				manager.insertar(et.getText().toString());
				listAll();
				// TODO Auto-generated method stub

			}

		});
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	private void listAll() {
		DataBaseManager manager = new DataBaseManager(context);
		cursor = manager.getAll();

		String[] from = new String[] { manager.CN_NAME };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		adapter = new SimpleCursorAdapter(context,
				android.R.layout.simple_expandable_list_item_1, cursor, from,
				to, 0);

		lv.setAdapter(adapter);
	}

}
