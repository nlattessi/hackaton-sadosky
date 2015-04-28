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

public class CargarSubeActivity extends Activity implements OnClickListener {

	private ImageView imagen;
	private TextView texto;
	private TextView textoPaso;

	/* Hard codeado para la demo */
	private String[] fotos = { "foto1.jpg", "foto2.jpg", "foto3.jpg",
			"foto4.jpg", "foto5.jpg", "foto6.jpg", "foto7.jpg", "foto8.jpg", };
	private String[] textos = {
			"Busco la tarjeta Sube y la guardo dentro de la billetera.",
			"Agarro un billete de 100 pesos y lo guardo dentro de la billetera.",
			"Busco las llaves para salir de casa.",
			"Salgo de casa y doblo a la derecha.",
			"Llego hasta la esquina de la farmacia.",
			"Cruzo la calle y entro al kiosco.",
			"Le pido a Juan cargar la Sube. Le doy a Juan la Sube y el billete de 100 pesos.",
			"Juan me devuelve la Sube y la guardo en la Billetera.", };
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
		File dir = new File(sdCard.getAbsolutePath() + "/Apoyo/Cargar_Sube");

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
			File dir = new File(sdCard.getAbsolutePath() + "/Apoyo/Cargar_Sube");

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
