package com.shahareldad.basecalc.basecalculator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainCalculatorActivityFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    EditText base2EditText;
    EditText base4EditText;
    EditText base8EditText;
    EditText base10EditText;
    EditText base16EditText;
    EditText inputNumberEditText;
    Button btnCalculate;
    View view;

    public MainCalculatorActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_calculator, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        base2EditText = (EditText)view.findViewById(R.id.baseTwoEditText);
        base4EditText = (EditText)view.findViewById(R.id.baseFourEditText);
        base8EditText = (EditText)view.findViewById(R.id.baseEightEditText);
        base10EditText = (EditText)view.findViewById(R.id.baseTenEditText);
        base16EditText = (EditText)view.findViewById(R.id.baseSixteenEditText);

        inputNumberEditText = (EditText)view.findViewById(R.id.inputNumberEditText);
        inputNumberEditText.setOnEditorActionListener(this);

        btnCalculate = (Button)view.findViewById(R.id.buttonCalculateBases);
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String input = inputNumberEditText.getText().toString();
        int numericInput = Integer.parseInt(input);

        base2EditText.setText(CalculateBase(numericInput, 2));
        base4EditText.setText(CalculateBase(numericInput, 4));
        base8EditText.setText(CalculateBase(numericInput, 8));
        base10EditText.setText(CalculateBase(numericInput, 10));
        base16EditText.setText(CalculateBaseAboveDecimal(numericInput, 16));
    }

    private String CalculateBaseAboveDecimal(int numericInput, int base) {

        String binaryOutput = "";
        int temp;

        do {
            temp = numericInput % base;
            if (temp > 9){
                temp += 55;
                binaryOutput = ((char)temp) + binaryOutput;
            }
            else
                binaryOutput = String.valueOf(numericInput % base) + binaryOutput;

            numericInput = numericInput / base;
        }while (numericInput != 0);

        return binaryOutput;
    }

    private String CalculateBase(int numericInput, int base) {

        String binaryOutput = "";

        do {
            binaryOutput = String.valueOf(numericInput % base) + binaryOutput;
            numericInput = numericInput / base;
        }while (numericInput != 0);

        while (base == 2 && binaryOutput.length() % 4 != 0){
            binaryOutput = "0" + binaryOutput;
        }

        return binaryOutput;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_GO)
        {
            onClick(v);
            return true;
        }
        return false;
    }
}
