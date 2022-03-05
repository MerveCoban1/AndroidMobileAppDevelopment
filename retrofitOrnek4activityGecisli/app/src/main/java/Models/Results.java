package Models;

public class Results {
    private String name;
    private int postId;
    private int id;
    private String body;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Results{" +
                "name='" + name + '\'' +
                ", postId=" + postId +
                ", id=" + id +
                ", body='" + body + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
