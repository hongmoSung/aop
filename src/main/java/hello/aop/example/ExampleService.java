package hello.aop.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepo;

    public void request(String itemId) {
        exampleRepo.save();
    }
}
