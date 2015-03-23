package com.example.lala.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    public static Database db;
    public Cursor readNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        readNotes = db.leer_notas();

        String[] fromColumns = {db.DB_COL_TITLE, db.DB_COL_NOTE};
        int[] toViews = {R.id.title, R.id.notes};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.note_list,
                readNotes,
                fromColumns,
                toViews,
                0);

        ListView list = (ListView) findViewById(R.id.text);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void nueva_nota(View v){
        Intent in= new Intent(this,MainActivity2.class);
        startActivity(in);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent in = new Intent(this, MainActivity2.class);

        TextView title_content = (TextView) findViewById(R.id.title);
        TextView text_content = (TextView) findViewById(R.id.notes);
        String title = title_content.getText().toString();
        String text = text_content.getText().toString();

        in.putExtra(db.DB_ROW_ID, id);
        in.putExtra(db.DB_ROW_TITLE, title);
        in.putExtra(db.DB_ROW_NOTE, text);

        startActivity(in);
    }
}
