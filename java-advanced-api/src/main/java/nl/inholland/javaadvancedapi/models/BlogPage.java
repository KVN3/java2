package nl.inholland.javaadvancedapi.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class BlogPage {
    @javax.persistence.Id @GeneratedValue
    private long id;

//    @OneToMany(
//            mappedBy = "blog_post",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
    @OneToMany
    private List<BlogPost> posts;

    public BlogPage() {
        posts = new ArrayList<BlogPost>();
    }

    public BlogPage(long id) {
        this.id = id;
        posts = new ArrayList<BlogPost>();
    }

    public long getId() {
        return id;
    }

    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(List<BlogPost> posts) {
        this.posts = posts;
    }

    public void AddBlogPost(BlogPost post){
        posts.add(post);
      //  post.setPage(this);
    }

    public void RemoveBlogPost(BlogPost post){
        posts.remove(post);
        //post.setPage(null);
    }
}

// FetchType LAZY, niet alles ophalen. Standaard 'EAGER', alles ophalen.
// cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false
// @Transactional bij ApplicationRunner