package ru.shestakov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DataTest {
    @Test
    public void whenUserTriangleShouldDrawTriangle() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
        UserDataRepository repository = context.getBean(UserDataRepository.class);
        User user = repository.save(new User("petr"));
        assertThat(repository.findOne(user.getId()), is(user));
    }
}
