package sg.edu.rp.c346.practicalquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    Spinner spnClass;
    Button btnSave;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etAge = findViewById(R.id.editTextAge);
        spnClass = findViewById(R.id.spinnerClass);
        btnSave = findViewById(R.id.buttonSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("class", spnClass.getSelectedItem().toString());
                prefEdit.putInt("classNo", spnClass.getSelectedItemPosition());
                prefEdit.commit();

                Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        String age = etAge.getText().toString();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name", name);
        if(age.isEmpty()){
            prefEdit.putInt("age",0);
        }
        else{
            prefEdit.putInt("age",Integer.parseInt(age));
        }
        //int pos = spnClass.getSelectedItemPosition();
        //prefEdit.putInt("pos", pos);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get focus on the etAge
        etAge.requestFocus();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name", "");
        int age = prefs.getInt("age", 0);
        //int pos = prefs.getInt("pos", 0);

        etName.setText(name);
        if (age == 0){
            etAge.setText("");
        }
        else{
            etAge.setText(age + "");
        }
        //spnClass.setSelection(pos);

        int classNo = prefs.getInt("classNo", 0);
        spnClass.setSelection(classNo);
    }
}
