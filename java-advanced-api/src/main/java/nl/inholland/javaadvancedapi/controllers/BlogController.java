package nl.inholland.javaadvancedapi.controllers;

import nl.inholland.javaadvancedapi.models.BlogPage;
import nl.inholland.javaadvancedapi.models.BlogPost;
import nl.inholland.javaadvancedapi.models.ErrorMessage;
import nl.inholland.javaadvancedapi.repositories.blog.BlogPostRepository;
import nl.inholland.javaadvancedapi.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("blogs")
public class BlogController {

    @Autowired
    private BlogService service;

    @Autowired
    private BlogPostRepository repository;

    // http://localhost:8080/blog/posts
    // http://localhost:8080/blog/posts?title=Patch%20Notes%20e1.1.2%20%26%20Beta%20Hotfix
    // http://localhost:8080/blog/posts?postingDate=2020-03-30
    // http://localhost:8080/blog/posts?postingDate=2020-03-30&title=Patch%20Notes%20e1.1.2%20%26%20Beta%20Hotfix
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBlogPosts(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "postingDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date postingDate) {

        writeLogs(title, postingDate);
        List<BlogPost> blogPosts = getBlogPosts(title, postingDate);

        return ResponseEntity
                .status(200)
                .body(blogPosts);
    }

    // ID 4 & 5
    @RequestMapping(value = "/pages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBlogPage(@PathVariable("id") long id) {
        BlogPage page = service.getBlogPage(id);

        if (page == null)
            return ResponseEntity
                    .status(404)
                    .body(new ErrorMessage("No page foundda for given ID."));
        else
            return ResponseEntity
                    .status(200)
                    .body(page);
    }

    // /api/blogs/pages/4
    @RequestMapping(value = "/pages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBlogPageByPostId(
            @RequestParam(value = "postId", required = false) long postId
    ) {
//        if (postId == null)
//            return ResponseEntity
//                    .status(400)
//                    .body(new ErrorMessage("No postID passed."));

        BlogPage page = service.getBlogPageByPostId(postId);

        if (page == null)
            return ResponseEntity
                    .status(404)
                    .body(new ErrorMessage("No page foundd for given ID."));
        else
            return ResponseEntity
                    .status(200)
                    .body(page);
    }

    private void writeLogs(String title, Date postingDate) {
        BinaryOperator<String> addStrings = (s1, s2) -> s1 + s2;

        // No input passed, null
        String postingDateText = "null";
        if (postingDate != null)
            postingDateText = postingDate.toString();

        String logs = addStrings.apply(
                addStrings.apply("--- REQUEST MADE ---", "\n"),
                addStrings.apply(
                        addStrings.apply(
                                addStrings.apply("Title Filter: ", title),
                                "\n"),
                        addStrings.apply("PostingDate Filter: ",
                                postingDateText)));

        System.out.println(logs);
    }

    private List<BlogPost> getBlogPosts(String title, Date postingDate) {
        Stream<BlogPost> stream = service.getAllBlogPosts().stream();

        if (title != null)
            stream = stream.filter(b -> b.getTitle().equals(title));
        if (postingDate != null)
            stream = stream.filter(b -> b.getPostingDate().getYear() == postingDate.getYear() &&
                    b.getPostingDate().getMonth() == postingDate.getMonth() &&
                    b.getPostingDate().getDay() == postingDate.getDay());

        return stream.collect(Collectors.toList());
    }
}
