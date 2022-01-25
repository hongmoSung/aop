package hello.aop.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExampleTest {

    @Autowired
    ExampleService exampleService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            exampleService.request("test");
        }
    }
}
