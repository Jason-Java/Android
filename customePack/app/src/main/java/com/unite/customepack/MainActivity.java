package com.unite.customepack;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.unite.jasonjar.util.JasonToast;
import com.unite.jasonjar.util.LogUtil;
import com.unite.jasonjar.util.StringUtil;
import com.unite.jasonjar.view.JasonButton;
import com.unite.jasonjar.view.JasonTextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        JasonToast.getInstance().init(this);

        WaitDialog waitDialog1 = new WaitDialog();
    }

}