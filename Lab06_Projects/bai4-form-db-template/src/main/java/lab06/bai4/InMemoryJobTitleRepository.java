package lab06.bai4;

import java.util.HashSet;
import java.util.Set;

public class InMemoryJobTitleRepository implements JobTitleRepository {
    private final Set<String> titles = new HashSet<>();

    @Override
    public boolean existsByTitle(String jobTitle) {
        return titles.contains(jobTitle);
    }

    @Override
    public JobTitle save(JobTitle jobTitle) {
        titles.add(jobTitle.getJobTitle());
        return jobTitle;
    }
}
