package nl.inholland.javaadvancedapi.models;

import java.util.Date;

public class BlogPost {
    private long id;
    private Date postingDate;
    private String title;
    private String body;

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
}
