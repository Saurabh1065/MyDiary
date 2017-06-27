package com.apkglobal.mydiary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private Button btnsave;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave=(Button)findViewById(R.id.btn_save);
        editText=(EditText)findViewById(R.id.editText);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals(""))
                {
                    String data =editText.getText().toString();
                    writeToFile(data);
                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG) .show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Enter Text !",Toast.LENGTH_LONG) .show();
                }
            }
        });
        if (readFromFile()!=null) {
            editText.setText(readFromFile());
             }
             else {

        }
    }
     private void writeToFile(String myData){
         try{
             OutputStreamWriter outputStreamWriter= new OutputStreamWriter(openFileOutput("diary.txt", Context.MODE_PRIVATE));
             outputStreamWriter.write(myData);
             outputStreamWriter.close();

         }catch(Exception e){
             Log.v("MyActivity",e.toString());
         }

     }

    private String readFromFile(){
        String result="";
        try{
            InputStream inputStream=openFileInput("diary.txt");
            if(inputStream != null)
            {
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String tempString="";
                StringBuilder stringBuilder = new StringBuilder();
                while((tempString  = bufferedReader.readLine()) != null){
                    stringBuilder.append(tempString);

            }
            inputStream.close();
                result=stringBuilder.toString();
            }


        }catch(FileNotFoundException e){
            Log.v("MyActivity","File Not Found"+e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
