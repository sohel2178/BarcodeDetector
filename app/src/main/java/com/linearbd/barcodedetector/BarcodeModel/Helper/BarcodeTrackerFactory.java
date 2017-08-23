package com.linearbd.barcodedetector.BarcodeModel.Helper;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.linearbd.barcodedetector.BarcodeModel.UI.GraphicOverlay;
import com.linearbd.barcodedetector.Listener.BarcodeListener;

/**
 * Created by Genius 03 on 8/23/2017.
 */

public class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {

    private GraphicOverlay graphicOverlay;
    private BarcodeListener listener;

    public BarcodeTrackerFactory(GraphicOverlay graphicOverlay) {
        this.graphicOverlay = graphicOverlay;
    }

    public void setBarcodeListener(BarcodeListener listener){
        this.listener = listener;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay);

        if(listener!= null){
            listener.detect(barcode);
        }
        return new GraphicTracker<>(graphicOverlay,barcodeGraphic);
    }
}
