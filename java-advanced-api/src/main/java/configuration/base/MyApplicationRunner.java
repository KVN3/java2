package configuration.base;

import nl.inholland.javaadvancedapi.repositories.security.ApiKeyRepository;
import nl.inholland.javaadvancedapi.security.ApiKey;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(prefix = "java-advanced-api.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyApplicationRunner implements ApplicationRunner {

    private ApiKeyRepository apiKeyRepository;

    public MyApplicationRunner(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateRandomAPIKeys(5);
        apiKeyRepository.findAll().forEach(System.out::println);
    }

    private void generateRandomAPIKeys(int amount) {
        for (int i = 0; i < amount; i++) {
            UUID uuid = UUID.randomUUID();
            apiKeyRepository.save(new ApiKey(uuid.toString()));
        }
    }
}
