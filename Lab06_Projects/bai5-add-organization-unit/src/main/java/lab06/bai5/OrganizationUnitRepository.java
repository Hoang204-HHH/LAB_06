package lab06.bai5;

public interface OrganizationUnitRepository {
    boolean existsByUnitId(String unitId);
    OrganizationUnit save(OrganizationUnit unit);
}
