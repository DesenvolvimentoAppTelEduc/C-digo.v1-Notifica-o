package com.example.andre.teleducgcm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class JanelaMensagem extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela_mensagem);
        TextView titulomensg =  (TextView) findViewById(R.id.textView);
        TextView mat =  (TextView) findViewById(R.id.textView2);
        TextView ConteudoMensg =  (TextView) findViewById(R.id.textView3);






        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        String ConteudoMensagem =(String) b.get("notice");
        Integer contador =(Integer) b.get("contador");
        String materia =(String) b.get("group");



        ConteudoMensg.setText(ConteudoMensagem);
        titulomensg.setText("Mensagem");
        mat.setText(materia);








        Button btvoltar = (Button) findViewById(R.id.btvoltar);
        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_janela_mensagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
