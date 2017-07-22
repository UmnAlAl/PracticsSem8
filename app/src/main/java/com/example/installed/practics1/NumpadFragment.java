package com.example.installed.practics1;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;

import com.example.installed.practics1.helper_classes.screen_controller.IOnNumpadFragmentCreateView;


/**
 * A simple {@link Fragment} subclass.
 * https://code.tutsplus.com/tutorials/android-user-interface-design-creating-a-numeric-keypad-with-gridlayout--mobile-8677
 * http://developer.alexanderklimov.ru/android/layout/gridlayout.php
 */
public class NumpadFragment extends Fragment {

    public LinearLayout mainLinearLayout;
    public GridLayout gridLayoutRadios;
    public TableLayout tableLayoutButtons;
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
    public RadioButton radio0;
    public RadioButton radio1;
    public RadioButton radio2;
    public RadioButton radio3;
    public RadioButton radio4;
    public ImageButton btnDel;
    public View view;
    public IOnNumpadFragmentCreateView onNumpadFragmentCreateView;


    public NumpadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numpad, container, false);
        view = v;
        mainLinearLayout = (LinearLayout) v.findViewById(R.id.NumpadFragmentMainLinearLayout);
        tableLayoutButtons = (TableLayout) v.findViewById(R.id.NumpadFragmentTableLayoutButtons);
        gridLayoutRadios = (GridLayout) v.findViewById(R.id.NumpadFragmentGridLayoutRadios);

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

        radio0 = (RadioButton) v.findViewById(R.id.NumpadFragmentRadioButton0);
        radio0.setClickable(false);
        radio0.setChecked(false);
        radio1 = (RadioButton) v.findViewById(R.id.NumpadFragmentRadioButton1);
        radio1.setClickable(false);
        radio1.setChecked(false);
        radio2 = (RadioButton) v.findViewById(R.id.NumpadFragmentRadioButton2);
        radio2.setClickable(false);
        radio2.setChecked(false);
        radio3 = (RadioButton) v.findViewById(R.id.NumpadFragmentRadioButton3);
        radio3.setClickable(false);
        radio3.setChecked(false);
        radio4 = (RadioButton) v.findViewById(R.id.NumpadFragmentRadioButton4);
        radio4.setClickable(false);
        radio4.setChecked(false);

        btnDel = (ImageButton) v.findViewById(R.id.NumpadFragmentButtonDel);
        if(getResources().getBoolean(R.bool.fragment_numpad_use_html_keyboard)) {
            btn0.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button0_html)));
            btn1.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button1_html)));
            btn2.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button2_html)));
            btn3.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button3_html)));
            btn4.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button4_html)));
            btn5.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button5_html)));
            btn6.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button6_html)));
            btn7.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button7_html)));
            btn8.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button8_html)));
            btn9.setText(Html.fromHtml(getResources().getString(R.string.fragment_numpad_button9_html)));
        }
        if(onNumpadFragmentCreateView != null) {
            onNumpadFragmentCreateView.OnNumpadFragmentCreateView(this);
        }
        return v;
    }

}
