package com.ray100.reidha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyFormActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText cinEditText;
    private EditText birthEditText;
    private Spinner sexSpinner;
    private EditText addressEditText;
    private Spinner situationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_form);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Survey");

        nomEditText = (EditText) findViewById(R.id.input_first_name);
        prenomEditText = (EditText) findViewById(R.id.input_last_name);
        cinEditText = (EditText) findViewById(R.id.input_cin);
        birthEditText = (EditText) findViewById(R.id.input_birth);
        addressEditText = (EditText) findViewById(R.id.input_address);

        sexSpinner =  findViewById(R.id.sex_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sexSpinner.setAdapter(adapter);

        situationSpinner =  findViewById(R.id.situation_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.situation, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        situationSpinner.setAdapter(adapter2);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.survey_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save_survey){
            saveToFirebase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToFirebase(){
        String nom = nomEditText.getText().toString().trim();
        String prenom = prenomEditText.getText().toString().trim();
        String cin = cinEditText.getText().toString().trim();
        String addresse = addressEditText.getText().toString().trim();
        String sexe = sexSpinner.getSelectedItem().toString();
        String situation = situationSpinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(nom) &&
            !TextUtils.isEmpty(prenom) &&
               !TextUtils.isEmpty(cin) &&
                !TextUtils.isEmpty(addresse) &&
                !TextUtils.isEmpty(sexe) &&
                !TextUtils.isEmpty(situation)){

            DatabaseReference survey = mDatabase.push();

            survey.child("Nom").setValue(nom);
            survey.child("Prenom").setValue(prenom);
            survey.child("Cin").setValue(cin);
            survey.child("Addresse").setValue(addresse);
            survey.child("Sexe").setValue(sexe);
            survey.child("Situation Familiale").setValue(situation);
        }


    }
}
