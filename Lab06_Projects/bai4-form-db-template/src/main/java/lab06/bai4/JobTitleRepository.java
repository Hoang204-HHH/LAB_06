package lab06.bai4;

public interface JobTitleRepository {
    boolean existsByTitle(String jobTitle);
    JobTitle save(JobTitle jobTitle);
}
