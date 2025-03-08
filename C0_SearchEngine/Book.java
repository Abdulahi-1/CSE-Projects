
import java.util.*;

// This class represents a book. 
// It implements the Media interface and provides functionality to store media ratings, 
// calculate average ratings, and manage content and authors associated with the book.
public class Book implements Media, Comparable<Book> {
   private String title;
   private List<String> authors;
   private List<String> contents;
   private List<Integer> ratings;

    // Behavior: This method creates our book, taking its characteristics into account.
    // Parameters:
    //   - title: title of the book
    //   - authors: the list of authors
    //   - content: words found in the book
    // Returns:
    //   - N/A
    // Exceptions:
    //   - If the given title, authors, and content are null,
    //  it throws an IllegalArgumentException.
    public Book(String title, List<String> authors, Scanner content){
        if(title == null || authors == null|| content == null){
           throw new IllegalArgumentException();
        }
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.contents = new ArrayList<>();
        this.ratings = new ArrayList<>();
        while (content.hasNext()){
            String word = content.next();
            this.contents.add(word);
        }
    }


    // Behavior: This method provides the title of our book.
    // Parameters:
    //   - N/A
    // Returns:
    //   - String: the title
    // Exceptions:
    //   - N/A
    public String getTitle(){
        return this.title;
    }

    // Behavior: This method provides the authors of our book.
    // Parameters:
    //   - N/A
    // Returns:
    //   - ArrayList: returns the authors
    // Exceptions:
    //   - N/A
    public List<String> getArtists(){
        return this.authors;
    }

    // Behavior: This method allows users to rate a book.
    // Parameters:
    //   - score: the user's rate choice
    // Returns:
    //   - N/A
    // Exceptions:
    //   - if the user's book rating is a negative number, an IllegalArgumentException is thrown.
    public void addRating(int score){
        if(score < 0){
            throw new IllegalArgumentException();
        }
        this.ratings.add(score);
    }

    // Behavior: This method receives the number of ratings the book received.
    // Parameters:
    //   - N/A
    // Returns:
    //   - int: returns the number of ratings given
    // Exceptions:
    //   - N/A
    public int getNumRatings(){
        return this.ratings.size();
    }

    // Behavior: This method receives words within book.
    // Parameters:
    //   - N/A
    // Returns:
    //   - List: returns a list words in a book.
    // Exceptions:
    //   - N/A
    public List<String> getContent(){
        return this.contents;
    }

    // Behavior: This method finds the average rating of the total ratings the book received.
    // Parameters:
    //   - N/A
    // Returns:
    //   - double: returns the average of the total ratings
    // Exceptions:
    //   - N/A
    public double getAverageRating(){
        if(this.ratings.isEmpty()){
            return 0.0;
        }
        double sum = 0.0;
        for(int rating : ratings){
            sum += rating;
        }
        double average = sum / this.ratings.size();
        return average;
    }

    // Behavior: This method is a technical representation of the Book object.
    // Parameters:
    //   - N/A
    // Returns:
    //   - String: If the book has no ratings, it displays only the title and authors.
    // If the book has ratings, it displays the title, authors, average rating,
    // and number of ratings.
    // Exceptions:
    //   - N/A
    public String toString(){
        if(this.ratings.isEmpty()){
            return this.title + " by " + this.authors;
        }
        return this.title + " by " + this.authors + ": " + 
        Math.round(getAverageRating()*100.0) / 100.0 +
         " (" + getNumRatings() + " ratings)";
    }

    // Behavior: This method compares two books together based on the number of ratings
    //  they've received. This book is ordered by the number of rating it has.
    // Parameters:
    //   - Book: users chosen book
    // Returns:
    //   - int: If this book has less ratings than the other book, then it returns -1.
    // If this book has the same number of ratings as the other book, 0 is returned.
    // Otherwise, return 1 to indicate this book has a greater number of ratings than the other.
    // Exceptions:
    //   - N/A
    public int compareTo(Book other){
        if (this.getNumRatings() < other.getNumRatings()){
            return -1;
        }
        else if (this.getNumRatings() == other.getNumRatings()){
            return 0;
        }
        else{
            return 1;
        }
    }


}