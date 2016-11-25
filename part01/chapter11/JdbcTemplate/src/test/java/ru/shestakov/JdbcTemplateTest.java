package ru.shestakov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JdbcTemplateTest {
    @Test
    public void whenUserTriangleShouldDrawTriangle() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-context.xml");
        UserRepository repository = context.getBean(UserRepository.class);
        User user = repository.save(new User("petr"));
        assertThat(repository.getAll().contains(user), is(true));
    }
}
