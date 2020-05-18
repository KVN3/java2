package nl.inholland.javaadvancedapi.services;

import lombok.extern.java.Log;
import nl.inholland.javaadvancedapi.models.BlogPage;
import nl.inholland.javaadvancedapi.models.BlogPost;
import nl.inholland.javaadvancedapi.repositories.blog.BlogPageRepository;
import nl.inholland.javaadvancedapi.repositories.blog.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log
public class BlogService {
    private BlogPageRepository blogPageRepository;
    private BlogPostRepository blogPostRepository;

    public BlogService(BlogPageRepository blogPageRepository, BlogPostRepository blogPostRepository) {
        this.blogPageRepository = blogPageRepository;
        this.blogPostRepository = blogPostRepository;
        initDatabase();
    }

    private void initDatabase() {
        log.info("init db");

        // Posts
        BlogPost[] posts = new BlogPost[3];
        posts[0] = new BlogPost(1L, new Date(120, 2, 30, 18, 30), "Patch Notes e1.1.2 & Beta Hotfix", "AI is more likely to block when equipped with a shield. Common troops now generally have higher weapon skills.\n" +
                "Fixed a crash that occurred when the player was a faction ruler and ended a contract with a mercenary or minor faction and refused to settle their debt.\n" +
                "Pregnancy chance has been lowered.\n" +
                "An AI bug that reduced performance is fixed.\n" +
                "Agents no longer block ally attacks in the shield wall formation.\n" +
                "Multiplayer client crash fixed.");
        posts[1] = new BlogPost(2L, new Date(120, 4, 4, 18, 30), "Generic Title2", "Generic post body");
        posts[2] = new BlogPost(3L, new Date(120, 2, 4, 18, 30), "Kaaping bij de Hoorn van Afrika", "2020");

        // Pages
        BlogPage[] pages = new BlogPage[2];
        pages[0] = new BlogPage();
        pages[0].AddBlogPost(posts[0]);
        pages[0].AddBlogPost(posts[1]);

        pages[1] = new BlogPage();
        pages[1].AddBlogPost(posts[2]);

        // Add to memory database
        blogPostRepository.save(posts[0]);
        blogPostRepository.save(posts[1]);
        blogPostRepository.save(posts[2]);

        blogPageRepository.save(pages[0]);
        blogPageRepository.save(pages[1]);

        // Logging
        System.out.println(blogPageRepository.count() + " rows in table 'BlogPage'");
        System.out.println(blogPostRepository.count() + " rows in table 'BlogPost'");
    }

    public List<BlogPost> getAllBlogPosts() {
        Iterable<BlogPost> iterable = blogPostRepository.findAll();

        // To List
        List<BlogPost> blogPosts = new ArrayList<BlogPost>();
        iterable.forEach(blogPosts::add);

        return blogPosts;
    }

    public List<BlogPage> getAllBlogPages() {
        Iterable<BlogPage> iterable = blogPageRepository.findAll();

        // To List
        List<BlogPage> blogPages = new ArrayList<BlogPage>();
        iterable.forEach(blogPages::add);

        return blogPages;
    }

    public BlogPage getBlogPage(long id) {
        Optional<BlogPage> optional = blogPageRepository.findById(id);

        return optional.orElse(null);
    }

    public BlogPage getBlogPageByPostId(long blogPostId) {
        Iterable<BlogPage> iterable = blogPageRepository.getPageByPostId(blogPostId);

        // To List
        List<BlogPage> blogPages = new ArrayList<BlogPage>();
        iterable.forEach(blogPages::add);

        //Optional<BlogPage> optional = blogPageRepository.findById(blogPostId);

        return blogPages.get(0);
    }

}
