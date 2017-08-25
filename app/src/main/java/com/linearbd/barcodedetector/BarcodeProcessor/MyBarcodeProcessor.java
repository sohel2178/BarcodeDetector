package com.linearbd.barcodedetector.BarcodeProcessor;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by sohel on 8/24/2017.
 */

public class MyBarcodeProcessor {
    public static final int CONTACT_INFO = 1;
    public static final int EMAIL = 2;
    public static final int ISBN = 3;
    public static final int PHONE = 4;
    public static final int PRODUCT = 5;
    public static final int SMS = 6;
    public static final int TEXT = 7;
    public static final int URL = 8;
    public static final int WIFI = 9;
    public static final int GEO = 10;
    public static final int CALENDAR_EVENT = 11;
    public static final int DRIVER_LICENSE = 12;

    private Barcode mBarcode;

    public MyBarcodeProcessor(Barcode mBarcode) {
        this.mBarcode = mBarcode;
    }

    private void process(){

    }
}
