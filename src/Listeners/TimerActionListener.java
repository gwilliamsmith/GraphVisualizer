package Listeners;

import SwingElements.Base;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@link ActionListener} child used to perform step auto-running, as well as
 * repainting on an interval.
 */
public class TimerActionListener implements ActionListener {

    private final Base ref;                                                           //Base object, used for reference

    /**
     * Constructor.
     *
     * @param in {@link Base} class, used for reference
     */
    public TimerActionListener(Base in) {
        ref = in;
    }//end constructor

    @Override
    /**
     * Method performed on trigger. If auto-running is enabled, performs
     * {@link Graph#takeStep}. Also repaints the Canvas.
     */
    public void actionPerformed(ActionEvent evt) {
        new Thread() {
            public void run() {
                //ref.getGraph().takeStep();
            }
        }.start();
    }//actionPerformed

}//end TimerActionListner
