package com.example.installed.practics1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * https://code.tutsplus.com/tutorials/android-user-interface-design-creating-a-numeric-keypad-with-gridlayout--mobile-8677
 * http://developer.alexanderklimov.ru/android/layout/gridlayout.php
 */
public class NumpadFragment extends Fragment {

    public FrameLayout frameLayout;
    public GridLayout gridLayout;
    public Button btn0;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;
    public Button btn9;
    public ImageButton btnDel;


    public NumpadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numpad, container, false);
        frameLayout = (FrameLayout) v.findViewById(R.id.NumpadFragmentFrameLayout);
        gridLayout = (GridLayout) v.findViewById(R.id.NumpadFragmentGridLayout);
        btn0 = (Button) v.findViewById(R.id.NumpadFragmentButton0);
        btn1 = (Button) v.findViewById(R.id.NumpadFragmentButton1);
        btn2 = (Button) v.findViewById(R.id.NumpadFragmentButton2);
        btn3 = (Button) v.findViewById(R.id.NumpadFragmentButton3);
        btn4 = (Button) v.findViewById(R.id.NumpadFragmentButton4);
        btn5 = (Button) v.findViewById(R.id.NumpadFragmentButton5);
        btn6 = (Button) v.findViewById(R.id.NumpadFragmentButton6);
        btn7 = (Button) v.findViewById(R.id.NumpadFragmentButton7);
        btn8 = (Button) v.findViewById(R.id.NumpadFragmentButton8);
        btn9 = (Button) v.findViewById(R.id.NumpadFragmentButton9);
        btnDel = (ImageButton) v.findViewById(R.id.NumpadFragmentButtonDel);
        return v;
    }

}
