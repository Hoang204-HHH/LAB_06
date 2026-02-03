package lab06.bai4;

public class JobTitleService {
    // Theo bảng partition trong lab:
    // Job Title: 1..100, Description: <=400, Job Spec: <=1024kB, Note: <=400
    private static final int MAX_JOB_TITLE = 100;
    private static final int MAX_DESC = 400;
    private static final int MAX_NOTE = 400;
    private static final int MAX_JOB_SPEC_KB = 1024;

    private final JobTitleRepository repo;

    public JobTitleService(JobTitleRepository repo) {
        this.repo = repo;
    }

    public JobTitle create(String jobTitle, String description, Integer jobSpecSizeKb, String note) {
        jobTitle = jobTitle == null ? "" : jobTitle.trim();
        description = description == null ? "" : description.trim();
        note = note == null ? "" : note.trim();

        // Job Title thường là required
        if (jobTitle.isEmpty()) throw new IllegalArgumentException("jobTitle is required");
        if (jobTitle.length() > MAX_JOB_TITLE) throw new IllegalArgumentException("jobTitle too long");

        // Optional fields nhưng có giới hạn độ dài
        if (!description.isEmpty() && description.length() > MAX_DESC) throw new IllegalArgumentException("description too long");
        if (!note.isEmpty() && note.length() > MAX_NOTE) throw new IllegalArgumentException("note too long");

        // File optional, nếu có thì check size
        if (jobSpecSizeKb != null) {
            if (jobSpecSizeKb < 0) throw new IllegalArgumentException("file size invalid");
            if (jobSpecSizeKb > MAX_JOB_SPEC_KB) throw new IllegalArgumentException("file too large");
        }

        if (repo.existsByTitle(jobTitle)) {
            throw new IllegalStateException("duplicate jobTitle");
        }

        return repo.save(new JobTitle(
                jobTitle,
                description.isEmpty() ? null : description,
                jobSpecSizeKb,
                note.isEmpty() ? null : note
        ));
    }
}
