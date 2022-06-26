package com.unite.customepack;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.unite.jasonjar.util.LogUtil;
import com.unite.jasonjar.util.StringUtil;
import com.unite.jasonjar.view.JasonButton;
import com.unite.jasonjar.view.JasonTextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();


        WaitDialog waitDialog1 = new WaitDialog();


        findViewById(R.id.start).setOnClickListener(v ->
        {
            waitDialog1.show(fragmentManager, "1");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    waitDialog1.dismiss();
                }
            }, 3000);
        });

        TextView textView = new TextView(this);
        textView.getTextSize();


    }

}