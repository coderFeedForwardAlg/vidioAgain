package com.example.vidioagain;

// I got some help from the vidio below
// https://www.youtube.com/watch?v=9t8VVWebRFM&t=711s


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name, dob, contact;
Button insert, update, delete, view;
DBHelper DB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        contact = findViewById(R.id.contact);

        insert = findViewById(R.id.buttonInsert);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
        view = findViewById(R.id.buttonView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob .getText().toString();

                boolean checkinsertdata = DB.insertuserdata(nameTXT,contactTXT,dobTXT);
                if(checkinsertdata == true){
                    Toast.makeText(MainActivity.this, "new entry inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "new entry inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob .getText().toString();

                boolean checkupdatedata = DB.updateuserdata(nameTXT,contactTXT,dobTXT);
                if(checkupdatedata == true){
                    Toast.makeText(MainActivity.this, " entry updated", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, " entry not updated", Toast.LENGTH_LONG).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob .getText().toString();

                boolean checkdeletedata = DB.deletedata(nameTXT);
                if(checkdeletedata == true){
                    Toast.makeText(MainActivity.this, " entry deleted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, " entry not deeted", Toast.LENGTH_LONG).show();
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, " no data their ", Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Name: " + res.getString(0) + "\n");
                            buffer.append("Contact: " + res.getString(1) + "\n");
                            buffer.append("Email: " + res.getString(2) + "\n\n");

                        }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                builder.setTitle("data entry ");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }
}