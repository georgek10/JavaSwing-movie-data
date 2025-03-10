public class Movie {
    // declare class public attributes
    public String title;
    public String release;
    public String duration;
    public String genre;

    // declare constructors of class
    public Movie() {
    }

    public Movie(String title, String release) {
        this.title = title;
        this.release = release;
    }

    public Movie(String title, String release, String duration, String genre) {
        this.title = title;
        this.release = release;
        this.duration = duration;
        this.genre = genre;
    }
}