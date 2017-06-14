package cdap.sliit.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import static cdap.sliit.chatbot.MainActivity.selectFriend;

public class select_friend extends Activity {

    ImageView iv1,iv2;
    dbHelper db;
    TextView tv;
    public static String name;
    public static int bg;



    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_friend);

        iv1=(ImageView)findViewById(R.id.iv_noah);
        iv2=(ImageView)findViewById(R.id.iv_emma);

        tv=(TextView)findViewById(R.id.textView2);

        db=new dbHelper(this);

        Cursor data=db.getFriend();


        if (data != null){
            data.moveToFirst();

            name=data.getString(1);
            bg=data.getInt(2);



        }

        count=data.getCount();

        if(selectFriend){
            name="none";
        }


        if(name.equalsIgnoreCase("none")){
            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateData("Noah");
                    name="Noah";
                    tv.setText("Switching to Noah...");

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    selectFriend=false;
                }
            });

            iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateData("Emma");
                    name="Emma";
                    tv.setText("Switching to Emma...");
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    selectFriend=false;
                }
            });
        }
        else{



            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
            finish();
            selectFriend=false;
        }



    }


    private void updateData(String s){
        boolean bool=db.UpdateName(s);


    }
}
