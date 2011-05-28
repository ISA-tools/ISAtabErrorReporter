package org.isatools.errorreporter.renderers;

import org.isatools.errorreporter.ui.utils.UIHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 18/03/2011
 *         Time: 17:13
 */
public class ISAFileItemUI extends JPanel {

    private JLabel image;
    private JLabel fileName;
    private JLabel problemInfo;


    public ISAFileItemUI() {
        instantiateUI();
    }

    private void instantiateUI() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(120, 90));

        image = new JLabel();
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setOpaque(false);

        fileName = new JLabel("", SwingConstants.CENTER);
        fileName.setForeground(UIHelper.LIGHT_GREY_COLOR);
        fileName.setFont(UIHelper.VER_10_PLAIN);
        fileName.setOpaque(false);


        problemInfo = new JLabel("", SwingConstants.CENTER);
        problemInfo.setForeground(UIHelper.LIGHT_GREEN_COLOR);
        problemInfo.setFont(UIHelper.VER_9_PLAIN);
        problemInfo.setOpaque(false);

        add(image, BorderLayout.CENTER);

        Box textBoxes = Box.createVerticalBox();
        textBoxes.setAlignmentX(Box.CENTER_ALIGNMENT);
        textBoxes.setOpaque(false);

        textBoxes.add(fileName);
        textBoxes.add(problemInfo);

        add(textBoxes, BorderLayout.SOUTH);
    }

    public void setFileName(String newFileName) {
        fileName.setText("<html>" + newFileName + "</html>");
    }

    public void setProblemString(String newFileName) {
        problemInfo.setText("<html>" + newFileName + "</html>");
    }

    public void setImage(ImageIcon newImage) {
        image.setIcon(newImage);
    }

    public void setSelected(boolean selected) {
        fileName.setFont(selected ? UIHelper.VER_10_BOLD : UIHelper.VER_10_PLAIN);
        problemInfo.setFont(selected ? UIHelper.VER_9_BOLD : UIHelper.VER_9_PLAIN);
    }

}
