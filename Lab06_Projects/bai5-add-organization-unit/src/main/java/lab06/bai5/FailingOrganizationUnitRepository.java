package lab06.bai5;

public class FailingOrganizationUnitRepository implements OrganizationUnitRepository {
    @Override
    public boolean existsByUnitId(String unitId) {
        throw new RuntimeException("DB connection error");
    }

    @Override
    public OrganizationUnit save(OrganizationUnit unit) {
        throw new RuntimeException("DB connection error");
    }
}
