package lab06.bai5;

public class OrganizationUnitService {
    private final OrganizationUnitRepository repo;

    // Giới hạn gợi ý (có thể chỉnh theo yêu cầu môn/hệ thống)
    private static final int MAX_NAME = 100;
    private static final int MAX_DESC = 400;

    public OrganizationUnitService(OrganizationUnitRepository repo) {
        this.repo = repo;
    }

    public OrganizationUnit create(String unitId, String name, String description) {
        unitId = unitId == null ? "" : unitId.trim();
        name = name == null ? "" : name.trim();
        description = description == null ? "" : description.trim();

        if (name.isEmpty()) throw new IllegalArgumentException("name is required");
        if (name.length() > MAX_NAME) throw new IllegalArgumentException("name too long");
        if (description.length() > MAX_DESC) throw new IllegalArgumentException("description too long");

        if (!unitId.isEmpty() && repo.existsByUnitId(unitId)) {
            throw new IllegalStateException("duplicate unitId");
        }

        String savedId = unitId.isEmpty() ? null : unitId;
        return repo.save(new OrganizationUnit(savedId, name, description.isEmpty() ? null : description));
    }
}
