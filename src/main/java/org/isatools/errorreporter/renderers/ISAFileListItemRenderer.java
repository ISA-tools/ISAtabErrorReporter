package org.isatools.errorreporter.renderers;

import org.isatools.errorreporter.model.ISAFileErrorReport;
import org.isatools.errorreporter.model.ISAFileType;
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
 *         Time: 15:12
 */
public class ISAFileListItemRenderer extends JComponent
        implements ListCellRenderer {

    private static Color listForeground = UIHelper.GREY_COLOR;
    private static Color listBackground = Color.WHITE;

    @InjectedResource
    private ImageIcon flowCytIcon, flowCytUnselectedIcon, gelIcon, gelUnselectedIcon, investigationIcon, investigationUnselectedIcon,
            microarrayIcon, microarrayUnselectedIcon, nmrIcon, nmrUnselectedIcon, sampleIcon, sampleUnselectedIcon, sequencingIcon, sequencingUnselectedIcon;

    public ISAFileListItemRenderer() {
        ResourceInjector.get("error-gui-package.style").inject(this);
        setLayout(new BorderLayout());

        ISAFileItemUI isaFileItem = new ISAFileItemUI();

        add(isaFileItem, BorderLayout.CENTER);
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

        //image.checkIsIdEntered(selected);
        Component[] components = getComponents();

        for (Component c : components) {

            if (c instanceof ISAFileItemUI) {

                if (val instanceof ISAFileErrorReport) {
                    ISAFileItemUI ui = (ISAFileItemUI) c;
                    ISAFileErrorReport report = (ISAFileErrorReport) val;

                    ui.setFileName(report.getFileName());
                    ui.setProblemString(report.getProblemSummary());
                    ui.setImage(getRequiredImageIcon(report, selected));
                    ui.setSelected(selected);

                }
            }

        }

        return this;
    }

    private ImageIcon getRequiredImageIcon(ISAFileErrorReport report, boolean selected) {

        if (report.getFileType() == ISAFileType.FLOW_CYT) {
            return selected ? flowCytIcon : flowCytUnselectedIcon;
        } else if (report.getFileType() == ISAFileType.NMR) {
            return selected ? nmrIcon : nmrUnselectedIcon;
        } else if (report.getFileType() == ISAFileType.MICROARRAY) {
            return selected ? microarrayIcon : microarrayUnselectedIcon;
        } else if (report.getFileType() == ISAFileType.SEQUENCING) {
            return selected ? sequencingIcon : sequencingUnselectedIcon;
        } else if (report.getFileType() == ISAFileType.STUDY_SAMPLE) {
            return selected ? sampleIcon : sampleUnselectedIcon;
        } else {
            return selected ? investigationIcon : investigationUnselectedIcon;
        }
    }
}