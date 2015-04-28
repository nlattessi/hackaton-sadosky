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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {
	public static final String TABLE_NAME = "tareas";
	public static final String CN_ID = "_id";
	public static final String CN_NAME = "name";

	public static final String CREATE_TABLE = "create table " + TABLE_NAME
			+ " (" + CN_ID + " integer primary key autoincrement," + CN_NAME
			+ " text not null);";

	private DbHelper helper;
	private SQLiteDatabase db;

	public DataBaseManager(Context context) {
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
	}

	public ContentValues generarContentValues(String nombre) {
		ContentValues valores = new ContentValues();
		valores.put(CN_NAME, nombre);
		return valores;
	}

	public void insertar(String nombre) {
		db.insert(TABLE_NAME, null, generarContentValues(nombre));
	}

	public Cursor getAll() {
		String[] columnas = new String[] { CN_ID, CN_NAME };
		return db.query(TABLE_NAME, columnas, null, null, null, null, null);
	}

}