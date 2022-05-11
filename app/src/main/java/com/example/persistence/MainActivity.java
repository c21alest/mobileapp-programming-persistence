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
    private Button deleteAllData;

    private Button deleteFirstField;
    private Button deleteSecondField;
    private Button deleteThirdField;
    private Button deleteFourthField;

    private Button readAllFields;

    private ArrayList appDataArray;
    private String[] updateId;
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
        deleteAllData = findViewById(R.id.delete_all);
        readAllFields = findViewById(R.id.read_all_fields);

        deleteFirstField = findViewById(R.id.delete_first_field);
        deleteSecondField = findViewById(R.id.delete_second_field);
        deleteThirdField = findViewById(R.id.delete_third_field);
        deleteFourthField = findViewById(R.id.delete_fourth_field);

        readField1.setOnClickListener((View.OnClickListener) this);
        writeField1.setOnClickListener((View.OnClickListener) this);

        readField2.setOnClickListener((View.OnClickListener) this);
        writeField2.setOnClickListener((View.OnClickListener) this);

        readField3.setOnClickListener((View.OnClickListener) this);
        writeField3.setOnClickListener((View.OnClickListener) this);

        readField4.setOnClickListener((View.OnClickListener) this);
        writeField4.setOnClickListener((View.OnClickListener) this);

        clearFields.setOnClickListener((View.OnClickListener) this);
        deleteAllData.setOnClickListener((View.OnClickListener) this);
        readAllFields.setOnClickListener((View.OnClickListener) this);

        deleteFirstField.setOnClickListener((View.OnClickListener) this);
        deleteSecondField.setOnClickListener((View.OnClickListener) this);
        deleteThirdField.setOnClickListener((View.OnClickListener) this);
        deleteFourthField.setOnClickListener((View.OnClickListener) this);
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

            try {
                appDataArray.get(0);
                updateAppData(updateText, new String[]{"1"});
            } catch (IndexOutOfBoundsException e) {
                addAppData(updateText, 1);
            }

            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField2){
            updateText = dataField2.getText().toString();

            getAppData();

            try {
                appDataArray.get(1);
                updateAppData(updateText, new String[]{"2"});
            } catch (IndexOutOfBoundsException e) {
                addAppData(updateText, 2);
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField3){
            updateText = dataField3.getText().toString();

            getAppData();

            try {
                appDataArray.get(2);
                updateAppData(updateText, new String[]{"3"});
            } catch (IndexOutOfBoundsException e) {
                addAppData(updateText, 3);
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if(view == writeField4){
            updateText = dataField4.getText().toString();

            getAppData();

            try {
                appDataArray.get(3);
                updateAppData(updateText, new String[]{"4"});
            } catch (IndexOutOfBoundsException e) {
                addAppData(updateText, 4);
            }
            Log.d("==>", String.valueOf(dataField4.getText()));
        }

        else if (view == clearFields) {
            dataField1.setText("");
            dataField2.setText("");
            dataField3.setText("");
            dataField4.setText("");
        }

        else if (view == deleteAllData) {
            deleteAppData();
        }

        else if (view == deleteFirstField) {
            updateAppData(null, new String[]{"1"});
            dataField1.setText("");
        }

        else if (view == deleteSecondField) {
            updateAppData(null, new String[]{"2"});
            dataField2.setText("");
        }

        else if (view == deleteThirdField) {
            updateAppData(null, new String[]{"3"});
            dataField3.setText("");
        }

        else if (view == deleteFourthField) {
            updateAppData(null, new String[]{"4"});
            dataField4.setText("");
        }

        if(view == readAllFields){
            getAppData();
            dataField1.setText((String) appDataArray.get(0));
            dataField2.setText((String) appDataArray.get(1));
            dataField3.setText((String) appDataArray.get(2));
            dataField4.setText((String) appDataArray.get(3));
        }
    }

    private void updateAppData(String text, String[] updateId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.appdata.COLUMN_NAME_TEXT, text);
        database.update(DatabaseTables.appdata.TABLE_NAME, values, "id = ?", updateId);
        Log.d("==>", "Uppdaterade data");
    }

    private void addAppData(String text, int id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.appdata.COLUMN_NAME_ID, id);
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
        Log.d("==>", Arrays.toString(appDataArray.toArray()));
        cursor.close();
    }

    private void deleteAppData() {
        database.execSQL(DatabaseTables.SQL_DELETE_TABLE_APPDATA);
        database.execSQL(DatabaseTables.SQL_CREATE_TABLE_APPDATA);

        addAppData(null, 1);
        addAppData(null, 2);
        addAppData(null, 3);
        addAppData(null, 4);
    }
}
