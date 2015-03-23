package com.example.lala.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity2 extends Activity {

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        Intent i = getIntent();

        id = i.getLongExtra(MainActivity.db.DB_ROW_ID, -1);
        String Titulo = i.getStringExtra(MainActivity.db.DB_ROW_TITLE);
        String Nota = i.getStringExtra(MainActivity.db.DB_ROW_NOTE);

        EditText cuadro_Titulo = (EditText) findViewById(R.id.titulo2);
        EditText cuadro_Nota = (EditText) findViewById(R.id.cuerpo2);

        cuadro_Titulo.setText(Titulo);
        cuadro_Nota.setText(Nota);
    }

    @Override
    public void onPause(){
        super.onPause();

        EditText title = (EditText) findViewById(R.id.titulo2);
        EditText content = (EditText) findViewById(R.id.cuerpo2);
        String titulo = title.getText().toString();
        String texto = content.getText().toString();

        if( titulo!="" || texto!="" )
        {
            if ( id==-1 )
            {
                MainActivity.db.noteAdd(titulo, texto);
            }
            else
            {
                MainActivity.db.noteUpdate(id, titulo, texto);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
