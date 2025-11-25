import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalesItemTest
{
    private SalesItem laptop;
    private SalesItem smartphone;
    private SalesItem tablet;

    @BeforeEach
    public void setUp()
    {
        laptop = new SalesItem("Lenovo Thinkpad X1 Carbon", 15000000);
        smartphone = new SalesItem("Samsung Galaxy S24", 12000000);
        tablet = new SalesItem("iPad Pro 12.9 inch", 18000000);
    }

    @AfterEach
    public void tearDown()
    {
        laptop = null;
        smartphone = null;
        tablet = null;
    }

    @Test
    public void testAddValidComment()
    {
        assertTrue(laptop.addComment("Reza", "Produk sangat memuaskan!", 5));
        assertEquals(1, laptop.getNumberOfComments());
    }

    @Test
    public void testAddInvalidRating()
    {
        assertFalse(laptop.addComment("Budi", "Produk jelek", 0));
        assertFalse(laptop.addComment("Ahmad", "Produk buruk", 6));
        assertEquals(0, laptop.getNumberOfComments());
    }

    @Test
    public void testDuplicateAuthor()
    {
        assertTrue(smartphone.addComment("Siti", "Bagus sekali!", 5));
        assertFalse(smartphone.addComment("Siti", "Bagus sekali (lagi)", 4));
        assertEquals(1, smartphone.getNumberOfComments());
    }

    @Test
    public void testUpvoteComment()
    {
        tablet.addComment("Andi", "Layar jernih dan responsif", 5);
        tablet.upvoteComment(0);
        tablet.upvoteComment(0);
        assertEquals(2, tablet.findMostHelpfulComment().getVoteCount());
    }

    @Test
    public void testDownvoteComment()
    {
        laptop.addComment("Dina", "Prosesor cepat", 4);
        laptop.downvoteComment(0);
        laptop.downvoteComment(0);
        assertEquals(-2, laptop.findMostHelpfulComment().getVoteCount());
    }

    @Test
    public void testRemoveComment()
    {
        smartphone.addComment("Toni", "Kamera bagus", 5);
        smartphone.addComment("Nina", "Baterai tahan lama", 4);
        smartphone.removeComment(0);
        assertEquals(1, smartphone.getNumberOfComments());
    }    @Test
    public void testFindMostHelpfulComment()
    {
        tablet.addComment("Yuki", "Design elegan", 5);
        tablet.addComment("Hendra", "Performa standar", 3);
        tablet.addComment("Maya", "Harga mahal", 2);
        
        tablet.upvoteComment(0);
        tablet.upvoteComment(0);
        tablet.upvoteComment(0);
        
        Comment mostHelpful = tablet.findMostHelpfulComment();
        assertEquals("Yuki", mostHelpful.getAuthor());
    }

    @Test
    public void testVerifyComment()
    {
        laptop.addComment("Expert", "Professional review", 5);
        assertFalse(laptop.findMostHelpfulComment().isVerified());
        laptop.verifyComment(0);
        assertTrue(laptop.findMostHelpfulComment().isVerified());
    }

    @Test
    public void testAverageRating()
    {
        smartphone.addComment("User1", "Good", 5);
        smartphone.addComment("User2", "Average", 3);
        smartphone.addComment("User3", "Excellent", 5);
        
        double expected = (5.0 + 3.0 + 5.0) / 3.0;
        assertEquals(expected, smartphone.getAverageRating(), 0.01);
    }

    @Test
    public void testGetCommentsByRating()
    {
        tablet.addComment("A", "Best", 5);
        tablet.addComment("B", "Good", 4);
        tablet.addComment("C", "Best", 5);
        tablet.addComment("D", "Good", 4);
        
        assertEquals(2, tablet.getCommentsByRating(5));
        assertEquals(2, tablet.getCommentsByRating(4));
    }

    @Test
    public void testSalesStatistics()
    {
        laptop.addComment("User1", "Excellent product", 5);
        laptop.addComment("User2", "Very good", 4);
        laptop.addComment("User3", "Good", 4);
        
        laptop.recordSale();
        laptop.recordSale();
        laptop.recordSale();
        
        assertEquals(3, laptop.getTotalSold());
        System.out.println("\n=== Test Sales Statistics ===");
        laptop.showSalesStatistics();
    }

    @Test
    public void testShowInfo()
    {
        laptop.addComment("Cecep", "Keyboard nyaman", 5);
        laptop.addComment("Lina", "Layar bagus untuk editing", 4);
        System.out.println("\n=== Test Show Info ===");
        laptop.showInfo();
    }

    @Test
    public void testGetPrice()
    {
        assertEquals(15000000, laptop.getPrice());
        assertEquals(12000000, smartphone.getPrice());
    }

    @Test
    public void testGetName()
    {
        assertEquals("Lenovo Thinkpad X1 Carbon", laptop.getName());
        assertEquals("Samsung Galaxy S24", smartphone.getName());
    }
}
