package shestakov.repository;

import java.util.List;

/**
 * The interface Repository.
 *
 * @param <E> the type parameter
 */
public interface IRepository<E> {
    /**
     * Add.
     *
     * @param spec the spec
     */
    void add(ISpecification spec);

    /**
     * Get list.
     *
     * @param spec the spec
     * @return the list
     */
    List<E> get(ISpecification spec);
}
