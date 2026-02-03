package lab06.bai4;

public class FailingJobTitleRepository implements JobTitleRepository {
    @Override
    public boolean existsByTitle(String jobTitle) {
        throw new RuntimeException("DB connection error");
    }

    @Override
    public JobTitle save(JobTitle jobTitle) {
        throw new RuntimeException("DB connection error");
    }
}
