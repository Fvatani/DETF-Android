package fourthweek.myapplication.Model;

/**
 * Created by 10 on 23/08/2017.
 */

public class Question {

    private String imageUrl;
    private String title;
    private String tags;
    private Boolean solve;
    private Integer score;
    private String questionText;
    private String link;
    private String Name;

    public Question(String imageUrl, String title, String tags, Boolean solve, Integer score, String questionText, String link, String name) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.tags = tags;
        this.solve = solve;
        this.score = score;
        this.questionText = questionText;
        this.link = link;
        Name = name;
    }

    public String getLink() {return link;}

    public void setLink(String link) {this.link = link;}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String profileImage) { this.imageUrl = profileImage;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String profileImage) {
        this.title = profileImage;
    }

    public String getTags() {return tags;}

    public void setTags(String tags) {this.tags = tags;}

    public Boolean getsolve() {
        return solve;
    }

    public void setsolve(Boolean solve) {
        this.solve = solve;
    }

    public Integer getscore() {
        return score;
    }

    public void setscore(Integer score) {
        this.score = score;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String question) {
        this.questionText = question;
    }

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

}