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

import com.example.android.support.appnavigation.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CocinarFideosActivity extends Activity implements OnClickListener {

	private ImageView imagen;
	private TextView texto;
	private TextView textoPaso;

	/* Hard codeado para la demo */
	private String[] fotos = { "foto1.png", "foto2.png", "foto3.png",
			"foto4.png", "foto5.png", "foto6.png", "foto7.png", "foto8.png", };
	private String[] textos = {
			"Agarro la bolsa de fideos.",
			"Lleno la cazuela roja de fideos.",
			"Lleno la olla con agua.",
			"Prendo la hornalla y pongo la olla arriba.",
			"Tiro un puñado de sal gruesa en la olla.",
			"Cuando el agua de la olla hierve, le tiro los fideos dentro.",
			"Dejo los fideos cocinándose 10 minutos.",
			"Pongo el colador en la pileta y tiro el agua y los fideos en el colador.", };
	private int numPaso;
	private int cantPasosTotal = 8;

	/* Fin Hard codeado */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tarea);

		Button btn = (Button) findViewById(R.id.boton);
		btn.setOnClickListener(this);

		/* primer paso */
		numPaso = 0;

		imagen = (ImageView) findViewById(R.id.imagen);
		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File(sdCard.getAbsolutePath() + "/Apoyo/Cocinar_fideos");

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		Bitmap bm = BitmapFactory.decodeFile(dir.toString() + "/"
				+ fotos[numPaso], options);
		imagen.setImageBitmap(bm);

		textoPaso = (TextView) findViewById(R.id.paso);
		textoPaso.setText("Paso " + String.valueOf(numPaso + 1));
		texto = (TextView) findViewById(R.id.texto);
		texto.setText(textos[numPaso]);
		/* fin primer paso */

	}

	@Override
	public void onClick(View view) {
		numPaso++;
		if (numPaso < cantPasosTotal) {
			imagen = (ImageView) findViewById(R.id.imagen);

			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath()
					+ "/Apoyo/Cocinar_fideos");

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap bm = BitmapFactory.decodeFile(dir.toString() + "/"
					+ fotos[numPaso], options);
			imagen.setImageBitmap(bm);

			textoPaso = (TextView) findViewById(R.id.paso);
			textoPaso.setText("Paso " + String.valueOf(numPaso + 1));
			texto = (TextView) findViewById(R.id.texto);
			texto.setText(textos[numPaso]);
			if ((numPaso + 1) == cantPasosTotal) {
				Button btn = (Button) findViewById(R.id.boton);
				btn.setText("Termine");
			}
		} else {
			this.finish();
		}
	}

}
