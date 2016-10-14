package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get users by role id.
 */
public class GetUsersByRoleId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get users by role id.
     *
     * @param id the id
     */
    public GetUsersByRoleId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM users AS u JOIN roles AS r ON u.roleId = r.id AND r.name = %s;", this.id);
    }
}
