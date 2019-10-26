package BankATM;

public class Report {
    private String dailyReport;
    private String latestReport;
    public Report(){
        dailyReport="";
        latestReport="";
    }
    public String addReport(String report){
        dailyReport+=report;
        latestReport+=report;
        return report;
    }

    public String getLatestReport() {
        final String  lr= this.latestReport;
        this.latestReport ="";
        return lr;
    }

    public String getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(String dailyReport) {
        this.dailyReport = dailyReport;
    }

    public void setLatestReport(String latestReport) {
        this.latestReport = latestReport;
    }
}
