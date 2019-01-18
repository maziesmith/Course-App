package Model;

//class to hold items in Term Activity

import java.util.List;

public class TermItem {


    private int termId = 0;
    //static used to increment termId
    static int termIdCounter = 0;
    private String termName;
    private String startDate;
    private String endDate;

    public TermItem(String termName, String startDate, String endDate) {

        //increments term id then store in termId
        termIdCounter++;
        this.termId = termIdCounter;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static int getTermIdCounter() {
        return termIdCounter;
    }

    public static void setTermIdCounter(int termIdCounter) {
        TermItem.termIdCounter = termIdCounter;
    }



    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {

        this.startDate = startDate;
    }

    public String getEndDate() {

        return endDate;
    }

    public void setEndDate(String endDate) {

        this.endDate = endDate;
    }
}
