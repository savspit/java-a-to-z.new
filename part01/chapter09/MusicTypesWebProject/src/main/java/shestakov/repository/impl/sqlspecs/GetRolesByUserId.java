package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get roles by user id.
 */
public class GetRolesByUserId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get roles by user id.
     *
     * @param id the id
     */
    public GetRolesByUserId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM roles AS r JOIN user AS u ON r.id = u.roleId WHERE u.id = %s;", this.id);
    }
}
