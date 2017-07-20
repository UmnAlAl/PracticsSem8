package com.example.installed.practics1.helper_classes.screen_controller;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.installed.practics1.LogoFragment;
import com.example.installed.practics1.MainActivity;
import com.example.installed.practics1.NumpadFragment;
import com.example.installed.practics1.R;

import java.util.Stack;

/**
 * Created by Installed on 19.07.2017.
 */

public class NumpadScreenController implements IShow<NumpadScreenMetada>, IRemove, IOnLogoFragmentCreateView, IOnNumpadFragmentCreateView {

    public NumpadFragment numpadFragment;
    public LogoFragment logoFragment;
    public MainActivity activity;
    public NumpadScreenMetada metada;
    public IOnNumpadScreenControllerViewCreated onNumpadScreenControllerViewCreated;
    private Boolean isNumpadCreated = false;
    private Boolean isLogoCreated = false;


    public NumpadScreenController(MainActivity activity) {
        this.numpadFragment = new NumpadFragment();
        this.logoFragment = new LogoFragment();
        this.activity = activity;
        this.logoFragment.onLogoFragmentCreateView = this;
        this.numpadFragment.onNumpadFragmentCreateView = this;
    }


    public void Show(NumpadScreenMetada metada) {
        this.metada = metada;
        activity.getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.MainActivityRelativeLayout, logoFragment)
                .commit();
        activity.getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.MainActivityRelativeLayout, numpadFragment)
                .commit();

    }


    public void Remove() {
        isNumpadCreated = false;
        isLogoCreated = false;
        ResetUiState();
        activity.getFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .remove(logoFragment)
                .remove(numpadFragment)
                .commit();
    }


    public void ResetUiState() {
        numpadFragment.radio0.setChecked(false);
        numpadFragment.radio1.setChecked(false);
        numpadFragment.radio2.setChecked(false);
        numpadFragment.radio3.setChecked(false);
        numpadFragment.radio4.setChecked(false);
    }


    public void OnLogoFragmentCreateView(LogoFragment logoFragment) {
        isLogoCreated = true;
        if(viewCreated() && (onNumpadScreenControllerViewCreated != null))
            onNumpadScreenControllerViewCreated.OnNumpadScreenControllerViewCreated(this);
    }


    public void OnNumpadFragmentCreateView(NumpadFragment numpadFragment){
        isNumpadCreated = true;
        if(viewCreated() && (onNumpadScreenControllerViewCreated != null))
            onNumpadScreenControllerViewCreated.OnNumpadScreenControllerViewCreated(this);
    }


    private Boolean viewCreated() {
        return isLogoCreated && isNumpadCreated;
    }


    public interface INumpadInsertionDoneListener {
        public void NumpadInsertionDoneListener(Integer[] code);
    }


    public class NumpadLogic {

        public INumpadInsertionDoneListener insertionDoneListener;
        private int codeLen;
        private int curPos;
        private Stack<Integer> currentCode;

        public NumpadLogic(int len) {
            codeLen = len;
            curPos = 0;
            currentCode = new Stack<>();
            setListeners();
        }

        private void setListeners() {
            //TODO TRY TO MAKE LISTENERS SETTING NORMAL

            getNumberButton(0).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(0);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(1).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(1);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(2).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(2);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(3).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(3);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(4).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(4);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(5).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(5);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(6).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(6);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(7).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(7);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(8).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(8);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            getNumberButton(9).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentCode.push(9);
                            getRadio(curPos).setChecked(true);
                            curPos++;
                            if(curPos == codeLen) {
                                Integer[] code = new Integer[codeLen];
                                currentCode.toArray(code);
                                insertionDoneListener.NumpadInsertionDoneListener(code);
                            }
                        }
                    }
            );

            numpadFragment.btnDel.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!currentCode.isEmpty()) {
                                getRadio(curPos).setChecked(false);
                                currentCode.pop();
                                curPos--;
                            }
                        }
                    }
            );
        }

        private RadioButton getRadio(int i) {
            switch (i) {
                case 0 : return numpadFragment.radio0;
                case 1 : return numpadFragment.radio1;
                case 2 : return numpadFragment.radio2;
                case 3 : return numpadFragment.radio3;
                case 4 : return numpadFragment.radio4;
                default: return null;
            }
        }


        private Button getNumberButton(int i) {
            switch (i) {
                case 0 : return numpadFragment.btn0;
                case 1 : return numpadFragment.btn1;
                case 2 : return numpadFragment.btn2;
                case 3 : return numpadFragment.btn3;
                case 4 : return numpadFragment.btn4;
                case 5 : return numpadFragment.btn5;
                case 6 : return numpadFragment.btn6;
                case 7 : return numpadFragment.btn7;
                case 8 : return numpadFragment.btn8;
                case 9 : return numpadFragment.btn9;
                default: return null;
            }
        }

        public boolean compareCodes(Integer[] code1, Integer[] code2, int len) {
            boolean res = true;
            for (int i = 0; i < len; ++i) {
                if(!code1[i].equals(code2[i])) {
                    res = false;
                    break;
                }
            }
            return res;
        }


    }//NumpadLogic


}
