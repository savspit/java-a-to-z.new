package shestakov.dao.impl;

import shestakov.dao.IAddress;
import shestakov.templates.AddressTemplate;
import shestakov.templates.Template;
import shestakov.models.Address;
import shestakov.db.DataSource;
import shestakov.models.Entity;

import java.util.List;

/**
 * The type Address.
 */
public class AddressImpl implements IAddress {
    private static final String SQL_CREATE = "INSERT INTO addresses(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE addresses SET text=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM addresses WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT a.id, a.text FROM addresses AS a WHERE a.id = ?";
    private static final String SQL_GET_ALL = "SELECT a.id, a.text FROM addresses AS a";
    private static final DataSource instance = DataSource.getInstance();

    @Override
    public void create(Address address) {
        Template addressTemplate = new AddressTemplate();
        addressTemplate.execute(instance, SQL_CREATE, address.getText());
    }

    @Override
    public List<Entity> getById(int id) {
        Template addressTemplate = new AddressTemplate();
        return addressTemplate.executeAndReturn(instance, SQL_GET_BY_ID, id);
    }

    @Override
    public void update(Address address) {
        Template addressTemplate = new AddressTemplate();
        addressTemplate.execute(instance, SQL_UPDATE, address.getText(), address.getId());
    }

    @Override
    public void delete(Address address) {
        Template addressTemplate = new AddressTemplate();
        addressTemplate.execute(instance, SQL_DELETE, address.getId());
    }

    @Override
    public List<Entity> getAll() {
        Template addressTemplate = new AddressTemplate();
        return addressTemplate.executeAndReturn(instance, SQL_GET_ALL);
    }
}
