package org.isatools.errorreporter.ui;

import org.isatools.errorreporter.model.ErrorLevel;
import org.isatools.errorreporter.model.ISAFileErrorReport;
import org.isatools.errorreporter.model.FileType;
import org.isatools.errorreporter.ui.borders.RoundedBorder;
import org.isatools.errorreporter.ui.utils.UIHelper;
import org.jdesktop.fuse.InjectedResource;
import org.jdesktop.fuse.ResourceInjector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorInformationPanel extends JPanel {

    private Color hoverColor = new Color(249, 249, 249);
    private Color warningColor = new Color(255, 221, 0);

    @InjectedResource
    private ImageIcon microarray, massNMR, sequencing,
            flowCytometry, gelElectrophoresis, histology, hematology, clinicalChemistry, generic, investigation;

    static {
        ResourceInjector.addModule("org.jdesktop.fuse.swing.SwingModule");

        ResourceInjector.get("error-gui-package.style").load(
                ErrorInformationPanel.class.getResource("/dependency-injections/error-gui-package.properties"));
    }

    private ISAFileErrorReport errorReport;

    public ErrorInformationPanel(ISAFileErrorReport errorReport) {

        ResourceInjector.get("error-gui-package.style").inject(this);
        this.errorReport = errorReport;
        setLayout(new BorderLayout());

        setBorder(new RoundedBorder(UIHelper.LIGHT_GREEN_COLOR, 5));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setBackground(UIHelper.BG_COLOR);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setBackground(hoverColor);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                firePropertyChange("errorSelected", "", getErrorReport());
            }
        });

        createGUI();
    }

    public ISAFileErrorReport getErrorReport() {
        return errorReport;
    }

    public void createGUI() {
        add(createTopSection(), BorderLayout.NORTH);

        add(createAssayInfoSection(), BorderLayout.CENTER);
    }

    private Container createTopSection() {
        Box topSection = Box.createHorizontalBox();

        topSection.add(new JLabel(determineIcon()), BorderLayout.WEST);

        int errorCount = errorReport.getMessageCountAtLevel(ErrorLevel.ERROR);
        int warningCount = errorReport.getMessageCountAtLevel(ErrorLevel.WARNING);

        final JLabel errorInformation = UIHelper.createLabel(" " + errorCount + " errors ", UIHelper.VER_8_BOLD, UIHelper.BG_COLOR, SwingConstants.CENTER);

        Box errorInformationContainer = Box.createHorizontalBox();
        errorInformationContainer.add(errorInformation);

        errorInformationContainer.setBorder(new RoundedBorder(UIHelper.RED_COLOR, UIHelper.RED_COLOR, 4));

        errorInformationContainer.setAlignmentY(10.0f);

        final JLabel warningInformation = UIHelper.createLabel(" " + warningCount + " warnings ", UIHelper.VER_8_BOLD, UIHelper.GREY_COLOR, SwingConstants.CENTER);

        Box warningInformationContainer = Box.createHorizontalBox();
        warningInformationContainer.add(warningInformation);

        warningInformationContainer.setBorder(new RoundedBorder(warningColor, warningColor, 4));

        warningInformationContainer.setAlignmentY(10.0f);

        if (warningCount > 0 && errorCount > 0) {
            topSection.add(Box.createHorizontalStrut(20));
        } else {
            topSection.add(Box.createHorizontalStrut(65));
        }

        if (errorCount > 0) {
            topSection.add(errorInformationContainer);
            topSection.add(Box.createHorizontalStrut(5));
        }

        if (warningCount > 0) {
            topSection.add(warningInformationContainer);
        }

        return topSection;
    }

    private Container createAssayInfoSection() {
        Box infoPane = Box.createVerticalBox();
        infoPane.setPreferredSize(new Dimension(160, 35));
        infoPane.add(UIHelper.createLabel(errorReport.getFileName(), UIHelper.VER_10_BOLD, UIHelper.DARK_GREEN_COLOR, SwingConstants.LEFT));

        infoPane.add(UIHelper.createLabel(errorReport.getAssayDescription(), UIHelper.VER_8_PLAIN, UIHelper.DARK_GREEN_COLOR, SwingConstants.LEFT));

        infoPane.setBorder(new EmptyBorder(2, 1, 2, 1));

        return infoPane;
    }

    private ImageIcon determineIcon() {
        FileType fileType = errorReport.getFileType();

        if (fileType == FileType.MICROARRAY) {
            return microarray;
        } else if (fileType == FileType.MASS_SPECTROMETRY) {
            return massNMR;
        } else if (fileType == FileType.NMR) {
            return massNMR;
        } else if (fileType == FileType.FLOW_CYTOMETRY) {
            return flowCytometry;
        } else if (fileType == FileType.GEL_ELECTROPHORESIS) {
            return gelElectrophoresis;
        } else if (fileType == FileType.SEQUENCING) {
            return sequencing;
        } else if (fileType == FileType.HISTOLOGY) {
            return histology;
        } else if (fileType == FileType.CLINICAL_CHEMISTRY) {
            return clinicalChemistry;
        } else if (fileType == FileType.HEMATOLOGY) {
            return hematology;
        } else if (fileType == FileType.INVESTIGATION) {
            return investigation;
        }

        return generic;
    }

}
