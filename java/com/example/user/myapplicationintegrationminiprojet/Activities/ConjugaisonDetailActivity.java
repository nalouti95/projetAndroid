package com.example.user.myapplicationintegrationminiprojet.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.user.myapplicationintegrationminiprojet.R;

import static com.example.user.myapplicationintegrationminiprojet.fragment.CoursFragment.selectedCour;


public class ConjugaisonDetailActivity extends AppCompatActivity {

    String title , subtitle , grammBody , conjuBody , orthoBody ;
    TextView textfonts , textfonts1,textfonts2,textfonts3, textfonts4  ;
    Typeface    OrangeJuce , AgentOrange , NationalCartoon ;
    private Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conjugaison_detail);

        textfonts = findViewById(R.id.textfonts);

        textfonts1 = findViewById(R.id.textfonts1);

        textfonts2 = findViewById(R.id.textfonts2);

        textfonts3 = findViewById(R.id.textfonts3);

        textfonts4 = findViewById(R.id.textfonts4);

        toolbar = findViewById(R.id.toolbarDt);
        toolbar.setTitle("Cours"+selectedCour.getId());
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }




       if (selectedCour.getLangue().equals("1")){
            title = "Cours of English";
            subtitle = "Level "+selectedCour.getIdLevel();
            grammBody = selectedCour.getGrammaire();
            conjuBody = selectedCour.getConjugaison() ;
            orthoBody = selectedCour.getOrthographe();

       }else
       if (selectedCour.getLangue().equals("2")){
            title = "Cours de Francais";
            subtitle = "Level "+selectedCour.getIdLevel();
            grammBody = selectedCour.getGrammaire();
            conjuBody = selectedCour.getConjugaison() ;
            orthoBody = selectedCour.getOrthographe();

       }else
        if (selectedCour.getLangue().equals("3")){
             title = "Cours of Spanish";
             subtitle = "Level "+selectedCour.getIdLevel();
             grammBody = selectedCour.getGrammaire();
             conjuBody = selectedCour.getConjugaison() ;
             orthoBody = selectedCour.getOrthographe();

        }else
        if (selectedCour.getLangue().equals("4")){
              title = "Cours of German";
              subtitle = "Level "+selectedCour.getIdLevel();
              grammBody = selectedCour.getGrammaire();
              conjuBody = selectedCour.getConjugaison() ;
              orthoBody = selectedCour.getOrthographe();
        }else{
           title = "error cour selected";
            subtitle = "Level 9999999";
            grammBody = "error while searching for courses";
            conjuBody = "error while searching for courses";
            orthoBody ="error while searching for courses";
        }




try {


    OrangeJuce = Typeface.createFromAsset(getAssets(), "fonts/orange juice 2.0.ttf");
    AgentOrange = Typeface.createFromAsset(getAssets(), "fonts/AgentOrange.ttf");
    NationalCartoon = Typeface.createFromAsset(getAssets(), "fonts/National Cartoon.ttf");


    textfonts.setText(title);
    textfonts.setTypeface(AgentOrange);

    textfonts1.setText(subtitle);
    textfonts1.setTypeface(OrangeJuce);

    textfonts2.setText(grammBody);


    textfonts3.setText(conjuBody);


    textfonts4.setText(orthoBody);





  //  y.setText("9"+text1);
   // y.setTypeface(Starjedi);



}catch (Exception e){
    e.getStackTrace();
}


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToQuestion(View view) {
        Intent intent = new Intent(ConjugaisonDetailActivity.this,Main2Activity.class);
        intent.putExtra("langue","1");
        startActivity(intent);
    }
}
