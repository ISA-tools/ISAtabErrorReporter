package org.isatools.errorreporter.ui.borders;

import org.isatools.errorreporter.ui.utils.UIHelper;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * @author Eamonn Maguire
 * @date May 27, 2009
 */


public class RoundedBorder extends AbstractBorder {

    private Color borderColor;
    private Color fillColor;
    private int curveRadius;

    public RoundedBorder() {
        this(UIHelper.GREY_COLOR, 8);
    }

    public RoundedBorder(Color borderColor, int curveRadius) {
        this.borderColor = borderColor;
        this.curveRadius = curveRadius;
    }

    public RoundedBorder(Color borderColor, Color fillColor, int curveRadius) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.curveRadius = curveRadius;
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 12;
        return insets;
    }

    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(x, y);

        if (fillColor != null) {
            g2d.setColor(fillColor);
            g2d.fillRoundRect(0, 0, width + 2, height + 2, curveRadius, curveRadius);
        } else {
            g2d.setColor(borderColor);
            g2d.drawRoundRect(0, 0, width - 2, height - 2, curveRadius, curveRadius);
        }

        g2d.translate(-x, -y);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
