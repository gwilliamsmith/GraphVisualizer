/*TODO:
 Stop Using Magic Numbers for JPanel/BufferedImage size
 */
package SwingElements;

import Listeners.CanvasMouseListener;
import graphvisualizer.GraphNode;
import graphvisualizer.GraphTupleInfo;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    private Base ref;
    public int windowX = 0;
    public int windowY = 0;

    //Says what the next action is
    private String actionString = "";

    //////////////////////////////
    //   Connection Variables   //
    //////////////////////////////
    private GraphNode connectA;
    private GraphNode connectB;
    private boolean connect = false;
    
    //Color for next line(black if null)
    private Color tempColor = null;
    
    //Info for next line
    private GraphTupleInfo gtiStorage = null;

    public Canvas(Base in) {
        ref = in;
        this.setBackground(Color.white);
        this.setSize(in.getWidth(), in.getHeight());
        CanvasMouseListener mouseListener = new CanvasMouseListener(ref);
        this.addMouseListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }//end constructor

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(producePicture(), 0, 0, null);
        ref.updateAverageColor();
    }//end paint

    public BufferedImage producePicture() {
        BufferedImage picture = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = picture.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ref.getGraph().paint(g);
        FontMetrics fontSize = g.getFontMetrics();
        g.setColor(Color.black);
        int textWidth = fontSize.stringWidth("Action: " + actionString);
        g.drawString("Action: " + actionString, this.getWidth() - textWidth, 11);
        textWidth = fontSize.stringWidth("Steps taken:" + ref.getGraph().getStepCount());
        g.drawString("Steps taken: " + ref.getGraph().getStepCount(), this.getWidth() - textWidth - 5, this.getHeight() - 11);
        if (ref.getGraph().getCycleBase() > 0) {
            textWidth = fontSize.stringWidth("Cycles: " + ref.getGraph().getCycleCount());
            g.drawString("Cycles: " + ref.getGraph().getCycleCount(), this.getWidth() - textWidth - 5, this.getHeight() - 22);
        }//end if
        g.dispose();
        return picture;
    }//end updatePicture

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }//end resize

    public int getWindowX() {
        return windowX;
    }//end getWindowX

    public void modifyWindowX(int mod) {
        windowX = windowX + mod;
    }//end modifyWindowX

    public int getWindowY() {
        return windowY;
    }//end getWindowY

    public void modifyWindowY(int mod) {
        windowY = windowY + mod;
    }//end modifyWindowX

    /**
     * Sets the variable connect
     *
     * @param in The new boolean value
     */
    public void setConnect(boolean in) {
        connect = in;
    }//end setConnect

    /**
     * Returns value of connect
     *
     * @return the value of connect
     */
    public boolean getConnect() {
        return connect;
    }//end getConnect

    /**
     * Sets the first node when connecting two nodes
     *
     * @param in The selected node
     */
    public void setConnectA(GraphNode in) {
        connectA = in;
    }//end setConnectA

    /**
     * Returns the first selected node when connecting two nodes
     *
     * @return
     */
    public GraphNode getConnectA() {
        return connectA;
    }//end getConnectA
        
    /**
     * Sets the tempColor variable
     * @param c The new color
     */
    public void setTempColor(Color c) {
        tempColor = c;
    }//end setTempColor

    /**
     * Returns tempColor
     * @return The value of tempColor
     */
    public Color getTempColor() {
        return tempColor;
    }//end getTempColor
    
    public void setGtiStorage(GraphTupleInfo in) {
        gtiStorage = in;
    }//end setGtiStorage

    public GraphTupleInfo getGtiStorage() {
        return gtiStorage;
    }//end getGtiStorage

    public void setConnectB(GraphNode in) {
        connectB = in;
    }//end setConnectB

    public GraphNode getConnectB() {
        return connectB;
    }//end getConnectB

    public void setActionString(String in) {
        actionString = in;
    }//end setActionString

    public String getActionString() {
        return actionString;
    }//end getActionString
}//end Canvas
