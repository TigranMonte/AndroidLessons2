package ru.startandroid.p1051fragmentdynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        checkBox = (CheckBox) findViewById(R.id.chbStack);
    }

    public void onClick(View v) {
        fTrans = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btnAdd:
                fTrans.add(R.id.frgmCont, frag1);
                break;
            case R.id.btnRemove:
                fTrans.remove(frag1);
                break;
            case R.id.btnReplace:
                fTrans.replace(R.id.frgmCont, frag2);
            default:
                break;
        }
        if (checkBox.isChecked()) fTrans.addToBackStack(null);
        fTrans.commit();
    }
}