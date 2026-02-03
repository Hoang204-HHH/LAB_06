package lab06.bai5;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrganizationUnitRepository implements OrganizationUnitRepository {
    private final Map<String, OrganizationUnit> store = new HashMap<>();

    @Override
    public boolean existsByUnitId(String unitId) {
        if (unitId == null) return false;
        return store.containsKey(unitId);
    }

    @Override
    public OrganizationUnit save(OrganizationUnit unit) {
        if (unit.getUnitId() != null && !unit.getUnitId().isEmpty()) {
            store.put(unit.getUnitId(), unit);
        }
        return unit;
    }
}
