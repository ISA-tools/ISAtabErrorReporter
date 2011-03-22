package org.isatools.errorreporter.renderers;

import org.isatools.errorreporter.ui.utils.UIHelper;

import javax.swing.*;
import java.awt.*;

import org.jdesktop.fuse.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 18/03/2011
 *         Time: 16:35
 */
public class MessageListRenderer extends JComponent
        implements ListCellRenderer {


    private static Color listForeground = UIHelper.GREY_COLOR;

    private Font selectedFont;

    @InjectedResource
    private ImageIcon smallErrorIcon, smallWarningIcon;

    private JLabel text;

    public MessageListRenderer() {
        this(UIHelper.VER_10_PLAIN);
    }


    public MessageListRenderer(Font font) {

        this.selectedFont = font;
        text = new JLabel();

        ResourceInjector.get("error-gui-package.style").inject(this);
        setLayout(new BorderLayout());

        JLabel image = new JLabel(smallErrorIcon);

        add(image, BorderLayout.WEST);
        add(text, BorderLayout.CENTER);
        setBorder(null);
    }

    /**
     * Sets all list values to have a white background and green foreground.
     *
     * @param jList        - List to render
     * @param val          - value of list item being rendered.
     * @param index        - list index for value to be renderered.
     * @param selected     - is the value selected?
     * @param cellGotFocus - has the cell got focus?
     * @return - The CustomListCellRendered Component.
     */
    public Component getListCellRendererComponent(JList jList, Object val,
                                                  int index, boolean selected, boolean cellGotFocus) {
        text.setText("<html>" + val.toString() + "</html>");

        //image.checkIsIdEntered(selected);
        Component[] components = getComponents();

        for (Component c : components) {

            ((JComponent)c).setOpaque(false);

            c.setForeground(listForeground);
            c.setFont(selectedFont);

            if(c instanceof JLabel) {
                ((JLabel)c).setVerticalTextPosition(SwingConstants.TOP);
            }
        }

        return this;
    }

//    private Dimension calculateLabelHeightAndWidth(String value) {
//
//        if (text.getGraphics() != null) {
//            FontMetrics fm = text.getGraphics().getFontMetrics(UIHelper.VER_11_BOLD);
//            int stringWidth = fm.stringWidth(value);
//
//            int numberOfLines = stringWidth / width;
//
//            if (numberOfLines == 0) {
//                return new Dimension(fm.getHeight(), width);
//            } else {
//                return new Dimension((fm.getHeight() * (numberOfLines + 1)), width);
//            }
//
//        }
//
//        return new Dimension(45, width);
//    }
}
