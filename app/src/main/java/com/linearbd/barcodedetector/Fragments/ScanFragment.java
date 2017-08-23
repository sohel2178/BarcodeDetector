package com.linearbd.barcodedetector.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linearbd.barcodedetector.Activities.BarcodeScannerActivity;
import com.linearbd.barcodedetector.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements View.OnClickListener {
    private ImageView ivScan;
    private static final int BAR_CODE_REQUEST=100;


    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(view);
        return  view;
    }

    private void initView(View view) {
        ivScan = view.findViewById(R.id.iv_scan);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ivScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_scan:
                openBarcodeScannerActivity();
                break;
        }
    }

    private void openBarcodeScannerActivity() {
        //startActivity(new Intent(getActivity().getApplicationContext(), BarcodeScannerActivity.class));
        getActivity().startActivityForResult(new Intent(getActivity().getApplicationContext(), BarcodeScannerActivity.class),BAR_CODE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==BAR_CODE_REQUEST){
            Log.d("SOHELCODE","YYY");
        }
    }
}
