package nl.inholland.javaadvancedapi.repositories.security;

import nl.inholland.javaadvancedapi.models.BlogPage;
import nl.inholland.javaadvancedapi.security.ApiKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, String> {
}
