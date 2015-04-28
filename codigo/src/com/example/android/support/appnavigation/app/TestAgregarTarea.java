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

import com.example.android.support.appnavigation.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 import java.util.Calendar;
 import android.app.DatePickerDialog;
 import android.app.Dialog;
 import android.app.DialogFragment;
 import android.app.TimePickerDialog;
 import android.text.format.DateFormat;
 import android.widget.DatePicker;
 import android.widget.TimePicker;
 import android.widget.RadioButton;
 */

public class TestAgregarTarea extends Activity {

	private EditText tarea;
	private CheckBox checkbox;
	/*
	 * private RadioButton diariaBoton; private RadioButton semanalBoton;
	 */
	private Spinner spinner, spinner2;

	String[] PosiblesTarea = { "Lista", "Anotador", "Colectivo", "Calendario",
			"Olla", "Sube" };

	/*
	 * private static int hora; private static int minutos; private static int
	 * año; private static int mes; private static int dia;
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agregar_tarea);

		tarea = (EditText) findViewById(R.id.at_tarea);
		// diariaBoton = (RadioButton) findViewById(R.id.at_radio0);
		// semanalBoton = (RadioButton) findViewById(R.id.at_radio1);

		checkbox = (CheckBox) findViewById(R.id.at_checkbox_1);

		Spinner spinnerDia = (Spinner) findViewById(R.id.at_spinner_dia);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.at_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDia.setAdapter(adapter);

		addListenerOnSpinnerItemSelection();

		Spinner spinnerIcono = (Spinner) findViewById(R.id.at_spinner_icono);
		spinnerIcono.setAdapter(new MyCustomAdapter(this, R.layout.row,
				PosiblesTarea));

	}

	public class MyCustomAdapter extends ArrayAdapter<String> {

		public MyCustomAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			// return super.getView(position, convertView, parent);

			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.row, parent, false);
			TextView label = (TextView) row.findViewById(R.id.weekofday);
			label.setText(PosiblesTarea[position]);

			ImageView icon = (ImageView) row.findViewById(R.id.icon);

			switch (PosiblesTarea[position]) {
			case "Lista":
				icon.setImageResource(R.drawable.todas);
				break;

			case "Anotador":
				icon.setImageResource(R.drawable.anotador);
				break;

			case "Colectivo":
				icon.setImageResource(R.drawable.colectivo);
				break;

			case "Calendario":
				icon.setImageResource(R.drawable.diario);
				break;

			case "Olla":
				icon.setImageResource(R.drawable.olla);
				break;

			case "Sube":
				icon.setImageResource(R.drawable.sube);
				break;

			default:
				icon.setImageResource(R.drawable.logo);
				break;
			}

			return row;
		}

	}

	public void addListenerOnSpinnerItemSelection() {
		spinner = (Spinner) findViewById(R.id.at_spinner_dia);
		spinner.setOnItemSelectedListener(new CustomItemSelectedListener());

		spinner2 = (Spinner) findViewById(R.id.at_spinner_icono);
		spinner2.setOnItemSelectedListener(new CustomItemSelectedListener());
	}

	/*
	 * public static class TimePickerFragment extends DialogFragment implements
	 * TimePickerDialog.OnTimeSetListener {
	 * 
	 * @Override public Dialog onCreateDialog(Bundle savedInstanceState) { //
	 * Use the current time as the default values for the picker final Calendar
	 * c = Calendar.getInstance(); int hour = c.get(Calendar.HOUR_OF_DAY); int
	 * minute = c.get(Calendar.MINUTE);
	 * 
	 * // Create a new instance of TimePickerDialog and return it return new
	 * TimePickerDialog(getActivity(), this, hour, minute,
	 * DateFormat.is24HourFormat(getActivity())); }
	 * 
	 * public void onTimeSet(TimePicker view, int hourOfDay, int minute) { // Do
	 * something with the time chosen by the user hora = hourOfDay; minutos =
	 * minute; } }
	 * 
	 * public void showTimePickerDialog(View v) { DialogFragment newFragment =
	 * new TimePickerFragment(); newFragment.show(getFragmentManager(),
	 * "timePicker"); }
	 * 
	 * public static class DatePickerFragment extends DialogFragment implements
	 * DatePickerDialog.OnDateSetListener {
	 * 
	 * @Override public Dialog onCreateDialog(Bundle savedInstanceState) { //
	 * Use the current date as the default date in the picker final Calendar c =
	 * Calendar.getInstance(); int year = c.get(Calendar.YEAR); int month =
	 * c.get(Calendar.MONTH); int day = c.get(Calendar.DAY_OF_MONTH);
	 * 
	 * // Create a new instance of DatePickerDialog and return it return new
	 * DatePickerDialog(getActivity(), this, year, month, day); }
	 * 
	 * public void onDateSet(DatePicker view, int year, int month, int day) { //
	 * Do something with the date chosen by the user año = year; mes = month;
	 * dia = day; } }
	 * 
	 * public void showDatePickerDialog(View v) { DialogFragment newFragment =
	 * new DatePickerFragment(); newFragment.show(getFragmentManager(),
	 * "datePicker"); }
	 */

	public void onClick(View view) {
		String salida = "Tarea: ";
		salida = salida + tarea.getText().toString();
		if (checkbox.isChecked()) {
			salida = salida + " - Semanal";
		}
		salida = salida + " - " + String.valueOf(spinner.getSelectedItem());
		salida = salida + " - " + String.valueOf(spinner2.getSelectedItem());

		Toast.makeText(this, salida, Toast.LENGTH_LONG).show();
	}

}