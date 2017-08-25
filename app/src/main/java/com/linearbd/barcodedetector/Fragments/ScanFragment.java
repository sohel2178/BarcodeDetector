package com.linearbd.barcodedetector.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.vision.barcode.Barcode;
import com.linearbd.barcodedetector.Activities.BarcodeScannerActivity;
import com.linearbd.barcodedetector.Activities.TestActivity;
import com.linearbd.barcodedetector.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements View.OnClickListener {
    private ImageView ivScan,ivTest,ivImage;
    private static final int BAR_CODE_REQUEST=100;

    private RelativeLayout rl1,rl2,rlContainer;// rl2 is a hidden layout

    private Bitmap scanBitMap;


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
        rlContainer = view.findViewById(R.id.cointainer);
        rl1 = view.findViewById(R.id.layout1);
        rl2 = view.findViewById(R.id.layout2);
        ivScan = view.findViewById(R.id.iv_scan);
        ivTest = view.findViewById(R.id.iv_test);
        ivImage = view.findViewById(R.id.iv_image);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ivScan.setOnClickListener(this);
        ivTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_scan:
                openBarcodeScannerActivity();
                break;

            case R.id.iv_test:
                openTestActivity();
                break;
        }
    }

    private void openTestActivity() {
        //startActivity(new Intent(getActivity().getApplicationContext(), TestActivity.class));
        startActivityForResult(new Intent(getActivity().getApplicationContext(), TestActivity.class),BAR_CODE_REQUEST);
    }

    private void openBarcodeScannerActivity() {
        startActivity(new Intent(getActivity().getApplicationContext(), BarcodeScannerActivity.class));
        //startActivityForResult(new Intent(getActivity().getApplicationContext(), BarcodeScannerActivity.class),BAR_CODE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("SOHELCODE","onActivityResult");

        if(requestCode==BAR_CODE_REQUEST && resultCode== Activity.RESULT_OK){

            //Bundle bundle = data.getExtras();

            Log.d("SOHELCODE",data.getStringExtra("result"));

            Barcode barcode = (Barcode) data.getExtras().getParcelable("data");

            if(barcode!=null){
                Log.d("SOHELCODE",barcode.displayValue);
                Log.d("SOHELCODE","Format "+barcode.format+"");
                Log.d("SOHELCODE","Value Format "+barcode.valueFormat+"");

                if(barcode.valueFormat==2){
                    Barcode.Email email = barcode.email;

                    Log.d("SOHELCODE","Address "+email.address);
                    Log.d("SOHELCODE","Subject "+email.subject);
                    Log.d("SOHELCODE","Body "+email.body);

                }

                String url = barcode.displayValue;

                if(url.startsWith("http://") || url.startsWith("https://")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(barcode.displayValue));
                    startActivity(browserIntent);
                }

                scanBitMap = data.getParcelableExtra("bitmap");
                ivImage.setImageBitmap(scanBitMap);

                transitionLayout();




            }


        }
    }

    private void transitionLayout() {
        TransitionManager.beginDelayedTransition(rlContainer);

        if(rl1.getVisibility()==View.VISIBLE){
            //getScanBitMap();
            rl1.setVisibility(View.GONE);
            //ivImage.setImageBitmap(scanBitMap);
            rl2.setVisibility(View.VISIBLE);

        }else{
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.GONE);
        }


    }

    public void getScanBitMap() {
        View view = rl1;
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if(bitmap!=null){
            ivImage.setImageBitmap(bitmap);
        }else{
            Log.d("HHHH","ggh");
        }

       // return view.getDrawingCache();
    }
}
