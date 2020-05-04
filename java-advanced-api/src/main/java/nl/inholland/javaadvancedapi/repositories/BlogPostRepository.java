package nl.inholland.javaadvancedapi.repositories;

import nl.inholland.javaadvancedapi.models.BlogPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
    List<BlogPost> findByTitle(String title);
    BlogPost findById(long id);
}
