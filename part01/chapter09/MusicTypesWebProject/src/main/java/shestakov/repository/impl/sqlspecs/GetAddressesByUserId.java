package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get addresses by user id.
 */
public class GetAddressesByUserId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get addresses by user id.
     *
     * @param id the id
     */
    public GetAddressesByUserId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM address AS a JOIN user AS u ON a.id = u.addressId WHERE u.id = %s;", this.id);
    }
}
