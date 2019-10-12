package com.example.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtN, txtT, txtI;
    Button btnAdd, btnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar(txtN.getText().toString(), txtT.getText().toString());
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(Integer.parseInt(txtI.getText().toString()));
            }
        });

    }

    private void conectar() {
        txtN=findViewById(R.id.txtnombre);
        txtT=findViewById(R.id.txtTelefono);
        btnAdd=findViewById(R.id.btnAdd);
        btnDel=findViewById(R.id.btnDelete);
        txtI=findViewById(R.id.txtId);
    }

    public void Guardar(String N, String T)
    {
        DbHelper helper = new DbHelper(this, "BD", null, 1);
        SQLiteDatabase db= helper.getWritableDatabase();
        try
        {
            ContentValues cv= new ContentValues();
            cv.put("Nombre",N );
            cv.put("Telefono", T);
            db.insert("contactos", null, cv);
            Toast.makeText(getApplicationContext(), " Registro insertado!!!", Toast.LENGTH_LONG).show();
            db.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), " Error " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void Eliminar(int I){
        DbHelper helper = new DbHelper(this, "BD", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("Delete from contactos where id ='I'");
        db.close();
    }

    public void Actualizar(String N, String T, int I){
        DbHelper helper = new DbHelper(this, "BD", null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("Update contactos set nombre='" + N + "', telefono='"+T+"' where id='I'");
        db.close();
    }

}
