package cdap.sliit.chatbot;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static cdap.sliit.chatbot.select_friend.bg;
import static cdap.sliit.chatbot.select_friend.name;
import static cdap.sliit.chatbot.splash_screen.chat;
import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {



    private ListView mListView;
    private EditText mEditTextMessage;


    private ChatMessageAdapter mAdapter;
    dbHelper db;
    String name1;



    public static boolean selectFriend=false;
    TextView tv_view;


    Cursor data5 ;

    String response;

    final Handler handler= new Handler();
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView_main);
        FloatingActionButton mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);

        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        //mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        tv_view=(TextView)findViewById(R.id.tv_view);




        switch (bg){
            case 0:mListView.setBackgroundResource(R.drawable.bg0); break;
            case 1:mListView.setBackgroundResource(R.drawable.bg1); break;
            case 2:mListView.setBackgroundResource(R.drawable.bg2); break;
            case 3:mListView.setBackgroundResource(R.drawable.bg3); break;
            case 4:mListView.setBackgroundResource(R.drawable.bg4); break;
            case 5:mListView.setBackgroundResource(R.drawable.bg5); break;
            case 6:mListView.setBackgroundResource(R.drawable.bg6); break;
            case 7:mListView.setBackgroundResource(R.drawable.bg7); break;
            case 8:mListView.setBackgroundResource(R.drawable.bg8); break;

        }


        db=new dbHelper(this);
        name1=name;




        actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            if (name.equalsIgnoreCase("emma")) {


                actionBar.setLogo(R.drawable.emma_contact);

                actionBar.setTitle("  " + name);

                data5 = db.getMessageEmma();

            } else {

                actionBar.setLogo(R.drawable.noah_contact);

                actionBar.setTitle("  " + name);

                data5 = db.getMessageNoah();


            }
            actionBar.setSubtitle("  online");
        }


        boolean c=loadMsg();





        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                //bot
                response = chat.multisentenceRespond(mEditTextMessage.getText().toString());
                if (TextUtils.isEmpty(message)) {
                    return;
                }

                mEditTextMessage.setText("");
                sendMessage(message);
                mListView.setSelection(mAdapter.getCount() - 1);
                actionBar.setSubtitle("  typing...");

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        actionBar.setSubtitle("  online");
                        mimicOtherMessage(response);
                        mListView.setSelection(mAdapter.getCount() - 1);


                    }
                }, 2100);


                db.addMessage(2,name1,message);
                db.addMessage(1,name1,response);



            }
        });


       /* mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info = ((TextView) view).getText().toString();

                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);

                adb.setTitle("Great Job!").setMessage("\n "+info)
                        .setCancelable(false)
                        .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alertDialog=adb.create();
                alertDialog.show();
            }
        });*/


       /* mListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);

                adb.setTitle("Great Job!").setMessage("\nhello")
                        .setCancelable(false)
                        .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alertDialog=adb.create();
                alertDialog.show();
                return true;
            }
        });
        */







        //----------------------------------------------------------------------------------------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_frnd:
                selectFriend=true;
                Intent intent=new Intent(this,select_friend.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.mi_setting:

                Intent intent1=new Intent(this,Settings.class);
                startActivity(intent1);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);

    }


    private void mimicOtherMessage(String message) {

        ChatMessage chatMessage = new ChatMessage(message, false, false);

        mAdapter.add(chatMessage);

    }


    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true);
        mAdapter.add(chatMessage);

        mimicOtherMessage();
    }



    private void mimicOtherMessage() {

        ChatMessage chatMessage = new ChatMessage(null, false, true);

        mAdapter.add(chatMessage);
    }




    private boolean loadMsg() {


        int i = 1;
        String s;


        try {
            if (data5.moveToLast()) {
                do {
                    i = data5.getInt(1);
                    s = data5.getString(2);

                    if (i == 2) {
                        sendMessage(s);
                    } else {
                        mimicOtherMessage(s);
                    }



                } while (data5.moveToPrevious());

                mListView.setSelection(mAdapter.getCount() - 1);
            }



        }catch (Exception e){
            return false;
        }
        return true;
    }









}

