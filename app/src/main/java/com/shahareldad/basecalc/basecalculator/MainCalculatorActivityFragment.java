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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainCalculatorActivityFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener {

    public static final String StringEmpty = "";
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

        InitControls();

        InitAdMob();
    }

    private void InitAdMob() {
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest testRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
                .build();

        mAdView.loadAd(testRequest);
    }

    private void InitControls() {
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
        long numericInput = Long.parseLong(input);

        base2EditText.setText(CalculateBaseAboveDecimal(numericInput, 2));
        base4EditText.setText(CalculateBaseAboveDecimal(numericInput, 4));
        base8EditText.setText(CalculateBaseAboveDecimal(numericInput, 8));
        base10EditText.setText(CalculateBaseAboveDecimal(numericInput, 10));
        base16EditText.setText(CalculateBaseAboveDecimal(numericInput, 16));
    }

    private String CalculateBaseAboveDecimal(long numericInput, int base) {

        String binaryOutput = StringEmpty;
        long temp;

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
