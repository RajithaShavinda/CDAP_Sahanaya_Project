package cdap.sliit.chatbot;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import static cdap.sliit.chatbot.select_friend.name;

public class Settings extends AppCompatActivity {

    ImageView iv0,iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8;
    dbHelper db;
    boolean b;

    Button btnE,btnN,btnAbout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnE=(Button)findViewById(R.id.delEmma);
        btnN=(Button)findViewById(R.id.delNoah);
        btnAbout=(Button)findViewById(R.id.about);

        iv0=(ImageView)findViewById(R.id.image_view_1) ;
        iv1=(ImageView)findViewById(R.id.image_view_2) ;
        iv2=(ImageView)findViewById(R.id.image_view_3) ;
        iv3=(ImageView)findViewById(R.id.image_view_4) ;
        iv4=(ImageView)findViewById(R.id.image_view_5) ;
        iv5=(ImageView)findViewById(R.id.image_view_6) ;
        iv6=(ImageView)findViewById(R.id.image_view_7) ;
        iv7=(ImageView)findViewById(R.id.image_view_8) ;
        iv8=(ImageView)findViewById(R.id.image_view_9) ;

        db=new dbHelper(this);

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.drawable.bot);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("  Settings");
        }


        iv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(0);
                b=db.UpdateBG(0);
            }
        });


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(1);
                b=db.UpdateBG(1);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(2);
                b=db.UpdateBG(2);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(3);
                b=db.UpdateBG(3);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(4);
                b=db.UpdateBG(4);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(5);
                b=db.UpdateBG(5);
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(6);
                b=db.UpdateBG(6);
            }
        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(7);
                b=db.UpdateBG(7);
            }
        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelected(8);
                b=db.UpdateBG(8);
            }
        });


        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder adb=new AlertDialog.Builder(Settings.this);

                adb.setTitle("Delete!").setMessage("\n Are you sure you wanna delete Emma ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                b=db.deleteAll('e');
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog=adb.create();
                alertDialog.show();

            }
        });



        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder adb=new AlertDialog.Builder(Settings.this);

                adb.setTitle("Delete!").setMessage("\n Are you sure you wanna delete Noah ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                b=db.deleteAll('n');
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog=adb.create();
                alertDialog.show();

            }
        });



        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), About.class);
                startActivity(intent1);
            }
        });
    }

    private void showSelected(int i){

    iv0.setImageResource(R.drawable.bg0_ic);
    iv1.setImageResource(R.drawable.bg1_ic);
    iv2.setImageResource(R.drawable.bg2_ic);
    iv3.setImageResource(R.drawable.bg3_ic);
    iv4.setImageResource(R.drawable.bg4_ic);
    iv5.setImageResource(R.drawable.bg5_ic);
    iv6.setImageResource(R.drawable.bg6_ic);
    iv7.setImageResource(R.drawable.bg7_ic);
    iv8.setImageResource(R.drawable.bg8_ic);

        switch (i)
         {
             case 0: iv0.setImageResource(R.drawable.bg0_ic_ok); break;
             case 1: iv1.setImageResource(R.drawable.bg1_ic_ok); break;
             case 2: iv2.setImageResource(R.drawable.bg2_ic_ok); break;
             case 3: iv3.setImageResource(R.drawable.bg3_ic_ok); break;
             case 4: iv4.setImageResource(R.drawable.bg4_ic_ok); break;
             case 5: iv5.setImageResource(R.drawable.bg5_ic_ok); break;
             case 6: iv6.setImageResource(R.drawable.bg6_ic_ok); break;
             case 7: iv7.setImageResource(R.drawable.bg7_ic_ok); break;
             case 8: iv8.setImageResource(R.drawable.bg8_ic_ok); break;


        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_back:
                Intent intent=new Intent(this,select_friend.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
