package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get users by music type id.
 */
public class GetUsersByMusicTypeId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get users by music type id.
     *
     * @param id the id
     */
    public GetUsersByMusicTypeId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM users AS u JOIN musicTypes AS mt ON u.musicTypeId = mt.id AND mt.name = %s;", this.id);
    }
}
