package cdap.sliit.chatbot;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class splash_screen extends Activity {
    public Bot bot;
    public static Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);



        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        Thread thread1=new Thread(){
            @Override
            public void run() {
                try{

                    do_stuff();
                    sleep(1000);
                    Intent intent1 = new Intent(getApplicationContext(), select_friend.class);
                    startActivity(intent1);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };

        thread1.start();



    }



    private void do_stuff(){
        //checking SD card availablility
        boolean a = isSDCARDAvailable();
        //receiving the assets from the app directory
        AssetManager assets = getResources().getAssets();
        File jayDir = new File(Environment.getExternalStorageDirectory().toString() + "/cdap/bots/DB");
        boolean b = jayDir.mkdirs();

        if (jayDir.exists()) {
            //Reading the file
            try {
                for (String dir : assets.list("alice2")) {
                    File subdir = new File(jayDir.getPath() + "/" + dir);
                    boolean subdir_check = subdir.mkdirs();
                    for (String file : assets.list("alice2/" + dir)) {
                        File f = new File(jayDir.getPath() + "/" + dir + "/" + file);
                        if (f.exists()) {
                            continue;
                        }
                        InputStream in = null;
                        OutputStream out = null;
                        in = assets.open("alice2/" + dir + "/" + file);
                        out = new FileOutputStream(jayDir.getPath() + "/" + dir + "/" + file);
                        //copy file from assets to the mobile's SD card or any secondary memory
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //get the working directory
        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/cdap";
        System.out.println("Working Directory = " + MagicStrings.root_path);
        AIMLProcessor.extension =  new PCAIMLProcessorExtension();

        //Assign the AIML files to bot for processing
        bot = new Bot("DB", MagicStrings.root_path, "chat");
        chat = new Chat(bot);

    }



    //check SD card availability
    public static boolean isSDCARDAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)? true :false;
    }


    //copying the file
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }





}
