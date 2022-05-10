package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        readField1.setOnClickListener((View.OnClickListener) this);
        writeField1.setOnClickListener((View.OnClickListener) this);

        readField2.setOnClickListener((View.OnClickListener) this);
        writeField2.setOnClickListener((View.OnClickListener) this);

        readField3.setOnClickListener((View.OnClickListener) this);
        writeField3.setOnClickListener((View.OnClickListener) this);

        readField4.setOnClickListener((View.OnClickListener) this);
        writeField4.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if(view == readField1){
            Log.d("==>", String.valueOf(dataField1.getText()));
        }
        else if(view == readField2){
            Log.d("==>", String.valueOf(dataField2.getText()));
        }
        else if(view == readField3){
            Log.d("==>", String.valueOf(dataField3.getText()));
        }
        else if(view == readField4){
            Log.d("==>", String.valueOf(dataField4.getText()));
        }
        else if(view == writeField1){
            addAppData("Test123");
        }
    }

    private long addAppData(String text) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.appdata.COLUMN_NAME_TEXT, text);
        return database.insert(DatabaseTables.appdata.TABLE_NAME, null, values);
    }
}
