package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get users by address id.
 */
public class GetUsersByAddressId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get users by address id.
     *
     * @param id the id
     */
    public GetUsersByAddressId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM users AS u JOIN addresses AS a ON u.addressId = a.id AND a.text = %s;", this.id);
    }
}
