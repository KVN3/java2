package nl.inholland.javaadvancedapi.models;


import org.springframework.data.annotation.Id;


import javax.persistence.*;
import java.util.Date;

@Entity
public class BlogPost {
    @javax.persistence.Id @GeneratedValue
    private Long id;

    @Column
    private Date postingDate;
    @Column
    private String title;
    @Column(columnDefinition="text")
    private String body;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private BlogPage page;
//
//    public BlogPage getPage() {
//        return page;
//    }
//
//    public void setPage(BlogPage page) {
//        this.page = page;
//    }

    public BlogPost() {
    }

    public BlogPost(long id, Date postingDate, String title, String body) {
        this.id = id;
        this.postingDate = postingDate;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Guitar{");
        sb.append("id=").append(id);
        sb.append(", postingDate='").append(postingDate).append('\'');
        sb.append(", title=").append(title);
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BlogPost )) return false;
//        return id != null && id.equals(((BlogPost) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
}
