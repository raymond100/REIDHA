package com.ray100.reidha;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ray100.reidha.Model.Survey;

public class ProfilActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private RecyclerView mSurveyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Survey");

//        mSurveyList =  findViewById(R.id.survey_list);
//        mSurveyList.setHasFixedSize(true);
//        mSurveyList.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilActivity.this, SurveyFormActivity.class));
            }
        });



    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<Survey, SurveyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Survey, SurveyViewHolder>(
//                Survey.class,
//                R.layout.survey_row,
//                SurveyViewHolder.class,
//                mDatabase
//        ) {
//            @Override
//            protected void populateViewHolder(SurveyViewHolder viewHolder, Survey model, int position) {
//                viewHolder.setFullName(model.getNom());
//                viewHolder.setDepartement(model.getCin());
//            }
//        };
//
//        mSurveyList.setAdapter(firebaseRecyclerAdapter);
//    }

    public static class SurveyViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public SurveyViewHolder(View itemView) {
            super(itemView);
            itemView = mview;
        }

        public void setFullName(String name){
            TextView fullName = (TextView) mview.findViewById(R.id.full_name);
            fullName.setText(name);
        }

        public void setDepartement(String departement){
            TextView departementText = (TextView) mview.findViewById(R.id.departement);
            departementText.setText(departement);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
        finish();
    }
}
