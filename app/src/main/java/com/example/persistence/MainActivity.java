package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    private EditText dataField1;
    private EditText dataField2;
    private EditText dataField3;
    private EditText dataField4;

    private Button readField1;
    private Button writeField1;

    private Button readField2;
    private Button writeField2;

    private Button readField3;
    private Button writeField3;

    private Button readField4;
    private Button writeField4;

    private Button clearFields;

    private ArrayList appDataArray;
    private String[] updateId;
    private int indexes;
    private int fields;
    private String updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        dataField1 = findViewById(R.id.first_field);
        dataField2 = findViewById(R.id.second_field);
        dataField3 = findViewById(R.id.third_field);
        dataField4 = findViewById(R.id.fourth_field);

        readField1 = findViewById(R.id.first_button_read);
        writeField1 = findViewById(R.id.first_button_write);

        readField2 = findViewById(R.id.second_button_read);
        writeField2 = findViewById(R.id.second_button_write);

        readField3 = findViewById(R.id.third_button_read);
        writeField3 = findViewById(R.id.third_button_write);

        readField4 = findViewById(R.id.fourth_button_read);
        writeField4 = findViewById(R.id.fourth_button_write);

        clearFields = findViewById(R.id.clear_all_button);

        readField1.setOnClickListener((View.OnClickListener) this);
        writeField1.setOnClickListener((View.OnClickListener) this);

        readField2.setOnClickListener((View.OnClickListener) this);
        writeField2.setOnClickListener((View.OnClickListener) this);

        readField3.setOnClickListener((View.OnClickListener) this);
        writeField3.setOnClickListener((View.OnClickListener) this);

        readField4.setOnClickListener((View.OnClickListener) this);
        writeField4.setOnClickListener((View.OnClickListener) this);

        clearFields.setOnClickListener((View.OnClickListener) this);

        // Antal rader som finns i databas som motsvarar antalet edittext fält
        fields = 4;
        // Index startar på noll
        indexes = (fields - 1);
    }

    @Override
    public void onClick(View view) {
        if(view == readField1){
            getAppData();
            dataField1.setText((String) appDataArray.get(0));
        }

        else if(view == readField2){
            getAppData();
            dataField2.setText((String) appDataArray.get(1));
        }

        else if(view == readField3){
            getAppData();
            dataField3.setText((String) appDataArray.get(2));
        }

        else if(view == readField4){
            getAppData();
            dataField4.setText((String) appDataArray.get(3));
        }

        else if(view == writeField1){
            updateText = dataField1.getText().toString();

            getAppData();

            Log.d("==>", "Start");
            if (indexes >= appDataArray.size() || indexes < 0) {
                // Gör när inte null
                updateAppData(updateText, new String[]{"1"});

            }
            else {
                // Gör på null
                addAppData("New123");
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField2){
            updateText = dataField2.getText().toString();

            getAppData();

            Log.d("==>", "Start");
            if (indexes >= appDataArray.size() || indexes < 1) {
                // Gör när inte null
                updateAppData(updateText, new String[]{"2"});

            }
            else {
                // Gör på null
                addAppData("New123");
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField3){
            updateText = dataField3.getText().toString();

            getAppData();

            Log.d("==>", "Start");
            if (indexes >= appDataArray.size() || indexes < 2) {
                // Gör när inte null
                Log.d("==>", "Ska uppdatera med: " + updateText);
                updateAppData(updateText, new String[]{"3"});

            }
            else {
                // Gör på null
                addAppData("New123");
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField4){
            updateText = dataField4.getText().toString();

            getAppData();

            Log.d("==>", "Start");
            if (indexes >= appDataArray.size() || indexes < 3) {
                // Gör när inte null
                updateAppData(updateText, new String[]{"4"});

            }
            else {
                // Gör på null
                addAppData("New123");
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if (view == clearFields) {
            dataField1.setText("");
            dataField2.setText("");
            dataField3.setText("");
            dataField4.setText("");
        }
    }

    private void updateAppData(String text, String[] updateId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.appdata.COLUMN_NAME_TEXT, text);
        database.update(DatabaseTables.appdata.TABLE_NAME, values, "id = ?", updateId);
        Log.d("==>", "Uppdaterade data");
    }

    private void addAppData(String text) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.appdata.COLUMN_NAME_TEXT, text);
        database.insert(DatabaseTables.appdata.TABLE_NAME, null, values);
        Log.d("==>", "Skrev data");
    }

    private void getAppData() {
        Cursor cursor = database.query(DatabaseTables.appdata.TABLE_NAME, null, null, null, null, null, null);
        appDataArray = new ArrayList();
        while (cursor.moveToNext()) {
            AppData appData = new AppData(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.appdata.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.appdata.COLUMN_NAME_TEXT))
            );
            Log.d("==>", String.valueOf(appData.getText()));
            appDataArray.add(appData.getText());
        }
        cursor.close();
    }
}
