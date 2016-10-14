package shestakov.repository.impl.sqlspecs;

import shestakov.models.User;
import shestakov.repository.ISqlSpecification;

/**
 * The type Add user.
 */
public class AddUser implements ISqlSpecification {
    private final User user;

    /**
     * Instantiates a new Add user.
     *
     * @param user the user
     */
    public AddUser(final User user) {
        this.user = user;
    }

    @Override
    public String toSqlQuery() {
        String sqlQuery;

        return String.format(
                "INSERT INTO roles(name) VALUES (%s);" + "\n" +
                "INSERT INTO addresses(text) VALUES (%s);" + "\n" +
                "INSERT INTO musicTypes(name) VALUES (%s);" + "\n" +
                "INSERT INTO users(login, name, address, roleId) VALUES (%s, %s, (SELECT a.id FROM address AS a WHERE a.text = %s), (SELECT r.id FROM roles AS r WHERE r.name = %s));" + "\n" +
                "INSERT INTO usersAndMusicTypes(userId,musicTypeId) VALUES ((SELECT u.id FROM users AS u WHERE u.login = %s), (SELECT mt.id FROM musicTypes AS mt WHERE mt.name = %s))",
                this.user.getRole().getName(),
                this.user.getAddress().getText(),
                this.user.getMusicType().getName(),
                this.user.getLogin(),
                this.user.getName(),
                this.user.getRole().getName(),
                this.user.getLogin(),
                this.user.getMusicType().getName());
    }
}
