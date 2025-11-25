public class Comment
{
    private String author;
    private String text;
    private int rating;
    private int voteCount;
    private long timestamp;
    private boolean isVerified;

    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.voteCount = 0;
        this.timestamp = System.currentTimeMillis();
        this.isVerified = false;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getText()
    {
        return text;
    }

    public int getRating()
    {
        return rating;
    }

    public int getVoteCount()
    {
        return voteCount;
    }

    public void upvote()
    {
        voteCount++;
    }    public void downvote()
    {
        voteCount--;
    }

    public void setVerified(boolean verified)
    {
        this.isVerified = verified;
    }

    public boolean isVerified()
    {
        return isVerified;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public int getHelpfulnessScore()
    {
        return voteCount + (rating * 2);
    }

    public String getFullDetails()
    {
        return "Author: " + author + " " + (isVerified ? "[✓ Verified]" : "") + "\n" +
               "Rating: " + rating + " stars\n" +
               "Comment: " + text + "\n" +
               "Helpful votes: " + voteCount + "\n" +
               "Helpfulness Score: " + getHelpfulnessScore() + "\n" +
               "Posted: " + new java.util.Date(timestamp);
    }
