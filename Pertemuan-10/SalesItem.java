import java.util.ArrayList;
import java.util.Iterator;

public class SalesItem
{
    private String name;
    private int price;
    private ArrayList<Comment> comments;
    private int totalSold;
    private double averageRating;

    public SalesItem(String name, int price)
    {
        this.name = name;
        this.price = price;
        comments = new ArrayList<Comment>();
        this.totalSold = 0;
        this.averageRating = 0.0;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public int getNumberOfComments()
    {
        return comments.size();
    }    public boolean addComment(String author, String text, int rating)
    {
        if(ratingInvalid(rating)) {
            return false;
        }
        if(findCommentByAuthor(author) != null) {
            return false;
        }
        comments.add(new Comment(author, text, rating));
        updateAverageRating();
        return true;
    }

    public void removeComment(int index)
    {
        if(index >= 0 && index < comments.size()) {
            comments.remove(index);
        }
    }

    public void upvoteComment(int index)
    {
        if(index >= 0 && index < comments.size()) {
            comments.get(index).upvote();
        }
    }

    public void downvoteComment(int index)
    {
        if(index >= 0 && index < comments.size()) {
            comments.get(index).downvote();
        }
    }

    public void showInfo()
    {
        System.out.println("*** " + name + " ***");
        System.out.println("Price: " + priceString(price));
        System.out.println();
        System.out.println("Customer comments:");
        for(Comment comment : comments) {
            System.out.println("-----------------------------------");
            System.out.println(comment.getFullDetails());
        }
        System.out.println();
        System.out.println("=====================================");
    }    public Comment findMostHelpfulComment()
    {
        if(comments.isEmpty()) {
            return null;
        }
        Iterator<Comment> it = comments.iterator();
        Comment best = it.next();
        while(it.hasNext()) {
            Comment current = it.next();
            if(current.getHelpfulnessScore() > best.getHelpfulnessScore()) {
                best = current;
            }
        }
        return best;
    }

    private boolean ratingInvalid(int rating)
    {
        return rating < 1 || rating > 5;
    }

    private Comment findCommentByAuthor(String author)
    {
        for(Comment comment : comments) {
            if(comment.getAuthor().equals(author)) {
                return comment;
            }
        }
        return null;
    }    private String priceString(int price)
    {
        int dollars = price / 100;
        int cents = price - (dollars * 100);
        if(cents <= 9) {
            return "$" + dollars + ".0" + cents;
        }
        else {
            return "$" + dollars + "." + cents;
        }
    }

    public void recordSale()
    {
        totalSold++;
    }

    public int getTotalSold()
    {
        return totalSold;
    }

    public double getAverageRating()
    {
        return averageRating;
    }

    public int getCommentsByRating(int rating)
    {
        int count = 0;
        for(Comment c : comments) {
            if(c.getRating() == rating) {
                count++;
            }
        }
        return count;
    }

    public void verifyComment(int index)
    {
        if(index >= 0 && index < comments.size()) {
            comments.get(index).setVerified(true);
        }
    }

    private void updateAverageRating()
    {
        if(comments.isEmpty()) {
            averageRating = 0.0;
            return;
        }
        int sum = 0;
        for(Comment c : comments) {
            sum += c.getRating();
        }
        averageRating = (double) sum / comments.size();
    }

    public void showSalesStatistics()
    {
        System.out.println("\n=== Sales Statistics for " + name + " ===");
        System.out.println("Total Sold: " + totalSold + " units");
        System.out.println("Average Rating: " + String.format("%.2f", averageRating) + " / 5.0");
        System.out.println("Total Comments: " + comments.size());
        System.out.println("5-star comments: " + getCommentsByRating(5));
        System.out.println("4-star comments: " + getCommentsByRating(4));
        System.out.println("3-star comments: " + getCommentsByRating(3));
        System.out.println("2-star comments: " + getCommentsByRating(2));
        System.out.println("1-star comments: " + getCommentsByRating(1));
        System.out.println("=====================================\n");
    }
}
