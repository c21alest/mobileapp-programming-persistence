package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
    }
}
