package shestakov.repository.impl.sqlspecs;

import shestakov.repository.ISqlSpecification;

/**
 * The type Get music types by user id.
 */
public class GetMusicTypesByUserId implements ISqlSpecification {
    private final int id;

    /**
     * Instantiates a new Get music types by user id.
     *
     * @param id the id
     */
    public GetMusicTypesByUserId(final int id) {
        this.id = id;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM users AS u JOIN musicTypes AS mt ON u.musicTypeId = mt.id AND u.id = %s;", this.id);
    }
}
