package org.isatools.errorreporter.model;

import java.util.Set;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 18/03/2011
 *         Time: 15:13
 */
public class ISAFileErrorReport {

    private String fileName;
    private ISAFileType fileType;
    private Set<String> messages;

    public ISAFileErrorReport(String fileName, ISAFileType fileType, Set<String> messages) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.messages = messages;
    }

    public String getFileName() {
        return fileName;
    }

    public ISAFileType getFileType() {
        return fileType;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public String getProblemSummary() {
        if(messages == null || messages.size() == 0) {
            return "0 problems found";
        } else {
            return messages.size() + " problem" + (messages.size() == 1 ? "" : "s") + " found";
        }
    }
}
