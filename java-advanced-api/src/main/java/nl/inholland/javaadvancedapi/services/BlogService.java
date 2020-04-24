package nl.inholland.javaadvancedapi.services;

import nl.inholland.javaadvancedapi.models.BlogPost;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    List<BlogPost> blogPosts;

    public BlogService() {
        initPosts();
    }

    private void initPosts() {



        this.blogPosts = Arrays.asList(
                new BlogPost(1L, new Date(120, 2, 30, 18, 30), "Patch Notes e1.1.2 & Beta Hotfix", "AI is more likely to block when equipped with a shield. Common troops now generally have higher weapon skills.\n" +
                        "Fixed a crash that occurred when the player was a faction ruler and ended a contract with a mercenary or minor faction and refused to settle their debt.\n" +
                        "Pregnancy chance has been lowered.\n" +
                        "An AI bug that reduced performance is fixed.\n" +
                        "Agents no longer block ally attacks in the shield wall formation.\n" +
                        "Multiplayer client crash fixed."),
                new BlogPost(2L, new Date(120, 4, 4, 18, 30), "Generic Title", "Generic post body")
        );
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPosts;
    }
}
