package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

import com.sun.java.swing.plaf.gtk.GTKConstants;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;

public class UIEffects {

    //--Singleton design---------------------------------------------------------
    private volatile static UIEffects instance = null;
    private UIEffects() { }
    public static synchronized UIEffects getInstance() {
        if(instance == null) {
            instance = new UIEffects();
        }
        return instance;
    }

    //--Effects methods--------------------------------------------------------------------

    /*Add or remove drop shadow to button
    * @param btn JavaFX button to add effect
    * @paraam m MouseEvent.EXIT place shadow back and MouseEvent.ENTER to remove shadow*/
    protected void btnShadow(Button btn, MouseEvent m) {
        switch(m) {
            case ENTER:
                btn.setEffect(null);
                break;
            case EXIT:
                DropShadow ds = new DropShadow();
                ds.setOffsetY(2.0);
                ds.setOffsetX(2.0);
                ds.setBlurType(BlurType.GAUSSIAN);
                btn.setEffect(ds);
                break;
        }
    }

    protected void blur(Node n, MouseEvent m) {

        DropShadow s = new DropShadow();

        BoxBlur b = new BoxBlur();
        b.setIterations(3);
        b.setHeight(5);
        b.setWidth(5);

        switch(m) {
            case LOCK:
                // maintain Dropshadow with Blur
                if(n.getEffect() instanceof DropShadow) {
                    s.setInput(b);
                    n.setEffect(s);
                }
                else {
                    n.setEffect(b);
                }
                break;

            case UNLOCK:
                if(n.getEffect() instanceof DropShadow) {
                    n.setEffect(s);
                }
                else {
                    n.setEffect(null);
                }
                break;
        }
    }
}
