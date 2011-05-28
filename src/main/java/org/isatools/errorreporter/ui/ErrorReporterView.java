package org.isatools.errorreporter.ui;

import com.explodingpixels.macwidgets.IAppWidgetFactory;
import org.isatools.errorreporter.model.ISAFileErrorReport;
import org.isatools.errorreporter.model.ISAFileType;
import org.isatools.errorreporter.renderers.ISAFileListItemRenderer;
import org.isatools.errorreporter.renderers.MessageListRenderer;
import org.isatools.errorreporter.ui.borders.RoundedBorder;
import org.isatools.errorreporter.ui.utils.UIHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import org.jdesktop.fuse.*;
import org.jdesktop.fuse.ResourceInjector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 18/03/2011
 *         Time: 15:10
 */
public class ErrorReporterView extends JPanel {

    static {
        ResourceInjector.addModule("org.jdesktop.fuse.swing.SwingModule");

        ResourceInjector.get("error-gui-package.style").load(
                ErrorReporterView.class.getResource("/dependency-injections/error-gui-package.properties"));
    }

    @InjectedResource
    private ImageIcon isatabLogo;

    private JList errorFileList;

    private DefaultListModel messageListModel;
    private JList messagesList;

    private JLabel fileInfoText;

    private List<ISAFileErrorReport> errorReports;

    public ErrorReporterView(List<ISAFileErrorReport> errorReports) {
        ResourceInjector.get("error-gui-package.style").inject(this);

        this.errorReports = errorReports;
        messageListModel = new DefaultListModel();
    }

    public void createGUI() {
        setLayout(new BorderLayout());
        setOpaque(false);


        createList();
        createMessagesPanel();

        if (errorReports.size() > 0) {
            errorFileList.setSelectedIndex(0);
        }

    }

    private void createList() {
        DefaultListModel model = new DefaultListModel();

        for (ISAFileErrorReport report : errorReports) {
            if (report.getMessages().size() > 0) {
                model.addElement(report);
            }
        }

        errorFileList = new JList(model);
        errorFileList.setOpaque(false);
        errorFileList.setAutoscrolls(true);
        errorFileList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        errorFileList.setCellRenderer(new ISAFileListItemRenderer());
        errorFileList.setVisibleRowCount(1);

        errorFileList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {

                if (errorFileList.getSelectedIndex() != -1) {
                    ISAFileErrorReport report = (ISAFileErrorReport) errorFileList.getSelectedValue();
                    updateMessageListContent(report.getMessages());
                }
            }
        });

        JScrollPane scroller = new JScrollPane(errorFileList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroller.getViewport().setOpaque(false);
        scroller.setOpaque(false);
        scroller.setBorder(new EmptyBorder(1, 1, 1, 1));
        scroller.setPreferredSize(new Dimension(400, 110));

        IAppWidgetFactory.makeIAppScrollPane(scroller);

        Box container = Box.createVerticalBox();
        container.setOpaque(false);

        JPanel iconContainer = new JPanel(new BorderLayout());
        iconContainer.setOpaque(false);
        iconContainer.add(new JLabel(isatabLogo), BorderLayout.EAST);

        container.add(iconContainer);
        container.add(scroller);
        container.add(Box.createVerticalGlue());

        add(container, BorderLayout.NORTH);
    }

    private void createMessagesPanel() {
        Box infoBox = Box.createHorizontalBox();
        infoBox.setOpaque(false);

        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.setOpaque(false);

        centralPanel.add(infoBox, BorderLayout.NORTH);

        messagesList = new JList(messageListModel);

        messagesList.setAutoscrolls(true);
        messagesList.setOpaque(false);
        messagesList.setFixedCellHeight(50);
        messagesList.setFixedCellWidth(370);
        messagesList.setCellRenderer(new MessageListRenderer());
        messagesList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                messagesList.setCellRenderer(new MessageListRenderer());
            }
        });

        JScrollPane scroller = new JScrollPane(messagesList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroller.getViewport().setOpaque(false);

        scroller.setOpaque(false);
        scroller.setBorder(new EmptyBorder(1, 1, 1, 1));

        IAppWidgetFactory.makeIAppScrollPane(scroller);

        centralPanel.add(scroller, BorderLayout.CENTER);

        centralPanel.setBorder(new TitledBorder(new RoundedBorder(UIHelper.LIGHT_GREY_COLOR, 3), "",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, UIHelper.VER_10_BOLD, UIHelper.RED_COLOR));

        add(centralPanel, BorderLayout.CENTER);

    }

    private void updateMessageListContent(Set<String> messages) {
        messageListModel.clear();
        for (String s : messages) {
            messageListModel.addElement(s);
        }
    }
}
