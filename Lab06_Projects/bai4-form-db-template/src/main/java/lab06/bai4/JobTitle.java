package lab06.bai4;

public class JobTitle {
    private final String jobTitle;
    private final String description;
    private final Integer jobSpecSizeKb; // null = không upload
    private final String note;

    public JobTitle(String jobTitle, String description, Integer jobSpecSizeKb, String note) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.jobSpecSizeKb = jobSpecSizeKb;
        this.note = note;
    }

    public String getJobTitle() { return jobTitle; }
    public String getDescription() { return description; }
    public Integer getJobSpecSizeKb() { return jobSpecSizeKb; }
    public String getNote() { return note; }
}
