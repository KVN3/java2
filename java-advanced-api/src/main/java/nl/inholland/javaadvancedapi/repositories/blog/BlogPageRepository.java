package nl.inholland.javaadvancedapi.repositories.blog;

import nl.inholland.javaadvancedapi.models.BlogPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPageRepository extends CrudRepository<BlogPage, Long> {

    // https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
//    @Query("SELECT koppel.blog_page_id FROM BlogPage blog_page, BlogPost blog_postkoppel, BlogPage.posts WHERE koppel.posts_id = blog_post.id and blog_post.id = ?1")
//    public long getBlogPageIdByBlogPostId(long id);

    @Query( value = "select * from blog_page page " +
            "join blog_page_posts page_posts " +
            "   on page_posts.blog_page_id = page.id " +
            "join blog_post post " +
            "   on post.id = page_posts.posts_id " +
            "where post.id = ?id", nativeQuery = true)
    Iterable<BlogPage> getPageByPostId(@Param("id") long id);
}
