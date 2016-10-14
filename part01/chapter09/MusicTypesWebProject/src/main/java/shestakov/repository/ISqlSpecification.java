package shestakov.repository;

/**
 * The interface Sql specification.
 */
public interface ISqlSpecification extends ISpecification {
    /**
     * To sql query string.
     *
     * @return the string
     */
    String toSqlQuery();
}
