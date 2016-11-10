package ru.shestakov.servlets;

import ru.shestakov.models.Advert;
import ru.shestakov.models.Car;
import ru.shestakov.models.User;
import ru.shestakov.services.AdvertsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Advert save servlet.
 */
public class AdvertSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String description = req.getParameter("description");
        String transmission = req.getParameter("transmission");
        String engine = req.getParameter("engine");
        String gearbox = req.getParameter("gearbox");

        AdvertsStorage storage = new AdvertsStorage();

        Advert advert;
        if (session.getAttribute("advertId") == null) {
            advert = new Advert();
            advert.setDescription(description);
            storage.updateAdvert(advert);
            advert = storage.getAdvertByDescription(description);
        } else {
            advert = storage.getAdvertById(Integer.parseInt(session.getAttribute("advertId").toString()));
        }

        Car car;
        if (session.getAttribute("advertId") == null) {
            car = new Car();
            car.setName(String.valueOf(advert.getId()));
            storage.updateCar(car);
            car = storage.getCarByName(String.valueOf(advert.getId()));
        } else {
            car = storage.getCarByAdvertId(advert.getId());
        }
        car.setEngine(storage.getEngineByName(engine));
        car.setTransmission(storage.getTransmissionByName(transmission));
        car.setGearbox(storage.getGearboxByName(gearbox));

        storage.updateCar(car);

        User user = (User) storage.getUserByLogin(session.getAttribute("login").toString()).get(0);

        advert.setDescription(description);
        advert.setCar(car);
        advert.setUser(user);

        storage.updateAdvert(advert);
    }
}
