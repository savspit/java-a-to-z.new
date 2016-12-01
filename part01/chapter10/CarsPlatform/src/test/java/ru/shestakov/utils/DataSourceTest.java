package ru.shestakov.utils;

import org.apache.commons.fileupload.FileItemFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.shestakov.models.*;
import ru.shestakov.services.AdvertsStorage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DataSourceTest {

    private final AdvertsStorage storage = new AdvertsStorage();

    @Before
    public void init() throws Exception
    {
        String testName = "test";
        User user = new User();
        user.setLogin(testName);
        user.setName(testName);
        storage.updateUser(user);
        Engine engine = new Engine();
        engine.setName(testName);
        storage.updateEngine(engine);
        Gearbox gearbox = new Gearbox();
        gearbox.setName(testName);
        storage.updateGearbox(gearbox);
        Transmission transmission = new Transmission();
        transmission.setName(testName);
        storage.updateTransmission(transmission);
        Car car = new Car();
        car.setName(testName);
        car.setEngine(storage.getEngineByName(testName));
        car.setGearbox(storage.getGearboxByName(testName));
        car.setTransmission(storage.getTransmissionByName(testName));
        storage.updateCar(car);
        Advert advert = new Advert();
        advert.setDescription(testName);
        advert.setUser(storage.getUserByLogin(testName));
        advert.setCar(storage.getCarByName(car.getName()));
        advert.setSold(false);
        storage.updateAdvert(advert);
    }

    @Test
    public void whenAddAdvertShouldAddIt() {
        String desc = storage.getAdvertByDescription("test").getDescription();
        assertThat(desc, is("test"));
    }

    @Test
    public void whenUpdateAdvertShouldChangeIt() {
        Advert advert = storage.getAdvertByDescription("test");
        advert.setDescription("changed");
        storage.updateAdvert(advert);
        Advert changedAdvert = storage.getAdvertById(advert.getId());
        assertThat(changedAdvert.getDescription(), is("changed"));
    }

    @Test
    public void whenAddCarShouldAddIt() {
        String desc = storage.getCarByName("test").getName();
        assertThat(desc, is("test"));
    }

    @Test
    public void whenUpdateCarShouldChangeIt() {
        Car car = storage.getCarByName("test");
        car.setName("changed");
        storage.updateCar(car);
        Car changedCar = storage.getCarByAdvertId(storage.getAdvertByDescription("test").getId());
        assertThat(changedCar.getName(), is("changed"));
    }

    @Test
    public void whenUserIsNotAdminShouldReturnCorrectValue() {
        User user = storage.getUserByLogin("test");
        boolean result = storage.isAdmin(user.getLogin());
        assertFalse(result);
    }

    @Test
    public void whenUserAddFilterShouldDoIt() {
        User user = storage.getUserByLogin("test");
        Filter filter = new Filter();
        filter.setUser(user);
        filter.setField("sold");
        filter.setOperation("=");
        filter.setValue("false");
        storage.saveFilter(user.getLogin(), filter);
        Filter foundedFilter = storage.getFiltersByLogin(user.getLogin()).get(0);
        assertThat(filter.getId(), is(foundedFilter.getId()));
        assertThat(filter.getField(), is(foundedFilter.getField()));
        assertThat(filter.getOperation(), is(foundedFilter.getOperation()));
        assertThat(filter.getValue(), is(foundedFilter.getValue()));
    }

    @Test
    public void whenRemoveFilterShouldDoIt() {
        User user = storage.getUserByLogin("test");
        Filter filter = new Filter();
        filter.setUser(user);
        storage.saveFilter(user.getLogin(), filter);
        assertThat(storage.getFiltersByLogin(user.getLogin()).size(), is(1));
        storage.removeFilter(filter);
        assertThat(storage.getFiltersByLogin(user.getLogin()).size(), is(0));
    }
}