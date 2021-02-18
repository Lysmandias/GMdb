package com.example.gmdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Film_detail_modification extends AppCompatActivity {

    EditText et_add_titreFilm, et_add_anneeFilm, et_add_acteur, et_add_resumefilm;
    Button btn_add_acteurs, btn_add_enregistrer_modification;
    LinearLayout linear_addacteur;

    public void init(){
        et_add_acteur = (EditText) findViewById(R.id.et_add_acteurs);
        et_add_anneeFilm = (EditText) findViewById(R.id.et_add_anneeFilm);
        et_add_resumefilm = (EditText) findViewById(R.id.et_add_resumeFilm);
        et_add_titreFilm = (EditText) findViewById(R.id.et_add_titreFilm);
        btn_add_acteurs = (Button) findViewById(R.id.btn_add_acteurs);
        btn_add_enregistrer_modification = (Button) findViewById(R.id.btn_enregistrer_modification);
        linear_addacteur = (LinearLayout) findViewById(R.id.Linear_addActeur);
    }

    public void addActeur(String content){
        //creation du nouvelle acteur
        EditText et_newacteur = new EditText(getApplicationContext());
        //mise en forme de l'ET avec un inputtype textepersonname et en maj
        et_newacteur.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        if( content!= null){
            et_newacteur.setText(content);
        }
        // ajout de cet element au linear grace a addview
        linear_addacteur.addView(et_newacteur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail_modification);
        init();

        btn_add_acteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActeur(null);
            }
        });
        //gestion de la rotation de l'ecran et la save des data
        if(savedInstanceState != null){
            String[] acteurs = savedInstanceState.getStringArray("acteurs");
            for (String s : acteurs){
                addActeur(s);
            }
        }else {
            //aucun acteur saisi on affiche un composant editText vide
            addActeur(null);
        }
    }
     @Override
    public void onSaveInstanceState(@Nullable Bundle outState){
        String[] acteurs = new String[linear_addacteur.getChildCount()];
        for (int i=0; i < linear_addacteur.getChildCount(
        ); i++){
            View child_linear_addacteur = linear_addacteur.getChildAt(i);
            if (child_linear_addacteur instanceof EditText){
                acteurs[i] = ((EditText)child_linear_addacteur).getText().toString();
            }
        }
        outState.putStringArray("acteurs", acteurs);
        super.onSaveInstanceState(outState);
     }
}