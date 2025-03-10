import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class StatisticsFrame extends JFrame {
    // declare all UI components as private attributes
    private JButton closeBtn;

    private JLabel moviesum;
    private JLabel sum;
    private JLabel averageduration;
    private JLabel average;
    private JLabel frequentgenre;
    private JLabel frequent;
    private JLabel oldestmovie;
    private JLabel oldest;
    private JLabel newestmovie;
    private JLabel newest;

    private String[] genres;
    private ArrayList<Movie> movies;
    private Movie x;

    public StatisticsFrame() {
        // create components
        closeBtn = new JButton("Close");

        moviesum = new JLabel("Total number of movies registered: ");
        averageduration = new JLabel("Average movie duration (in minutes): ");
        frequentgenre = new JLabel("Most frequent movie genre and count of movies: ");
        oldestmovie = new JLabel("Oldest movie and its release year: ");
        newestmovie = new JLabel("Newest movie and its release year: ");

        // create array list of registered movies
        movies = new ArrayList<>();

        // create panels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // set up the frame
        this.setSize(425, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Statistics");
        this.setVisible(true);

        // add listener to component
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseFrame();
            }
        });

        // call the methods that calculate the statistics of the movie data
        load();

        genres = new String[movies.size()];
        initgenres(genres);

        sum = new JLabel(overallmovies());
        average = new JLabel(averageduration());
        frequent = new JLabel(frequentgenre(genres));
        oldest = new JLabel(oldestmovie());
        newest = new JLabel(newestmovie());

        // add components to panels and panels to frame
        topPanel.add(closeBtn);
        bottomPanel.add(moviesum);
        bottomPanel.add(sum);
        bottomPanel.add(averageduration);
        bottomPanel.add(average);
        bottomPanel.add(frequentgenre);
        bottomPanel.add(frequent);
        bottomPanel.add(oldestmovie);
        bottomPanel.add(oldest);
        bottomPanel.add(newestmovie);
        bottomPanel.add(newest);

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    // private method to close window
    private void CloseFrame() {
        this.dispose();
    }

    // load data from text file
    private void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MovieData.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                // remove the line number from the start of the line
                String lineWithoutNumber = line.substring(line.indexOf(' ') + 1).trim();  // Remove the first part before the space
                
                // split the line into two parts: the movie name and the rest of the data
                String[] parts = lineWithoutNumber.split(";", 2);  // split into two parts by the first semicolon
                
                if (parts.length == 2) {
                    String movieName = parts[0].trim();  // movie name before the semicolon
                    String[] data = parts[1].trim().split("\\s+");  // remaining data split by whitespace
                    
                    String release = data[0].trim();
                    String duration = data[1].trim();
                    String genre = data[2].trim();

                    // create a movie object from the data
                    Movie x = new Movie(movieName, release, duration, genre);
                    movies.add(x);
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(StatisticsFrame.this, "Can't access data file", "File not found error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(StatisticsFrame.this, "Can't read from", "File I/O error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    // calculate total number of movies registered
    private String overallmovies() {
        String count = Integer.toString(movies.size());
        return count;
    }

    // calculate average duration of all movies registered
    private String averageduration() {
        int sum = 0;
        for (Movie x : movies) {
            int duration = Integer.parseInt(x.duration);
            sum += duration;
        }
        float average = sum/movies.size();
        String averaged = Float.toString(average);
        return averaged;
    }

    // initialize genres array
    private void initgenres(String[] genres) {
        int i=0;
        for (Movie x : movies) {
            genres[i] = x.genre.toLowerCase();
            i++;
        }
    }

    // calculate most frequent genre of all movies registered
    private String frequentgenre(String[] genres) {
        // insert all unique genres and update count if a genre is not unique
        HashMap<String, Integer> hashmap = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            // if it already exists then update count
            if (hashmap.containsKey(genres[i])) {
                hashmap.put(genres[i], hashmap.get(genres[i]) + 1);
            // else insert it in the map
            } else {
                hashmap.put(genres[i], 1);
            }
        }

        // transverse the map for the most frequent genre
        String maxgenre = "";
        int movies = 0;
        for (Map.Entry<String,Integer> entry : hashmap.entrySet()) {
            String genre = entry.getKey();
            Integer count = entry.getValue();

            if (count > movies) {
                maxgenre = genre;
                movies = count;
            } else if (count == movies) {
                if (genre.length() < maxgenre.length())
                    maxgenre = genre;
            }
        }
        
        return maxgenre + " - " + movies;
    }

    // calculate oldest movie registered
    private String oldestmovie() {
        int minReleaseYear = Integer.MAX_VALUE;
        String oldestTitle = "";
        String oldestRelease = "";

        for (Movie x : movies) {
            int releaseYear = Integer.parseInt(x.release);

            if (releaseYear < minReleaseYear) {
                minReleaseYear = releaseYear;
                oldestRelease = x.release;
                oldestTitle = x.title;
            }
        }
        
        return oldestTitle + " (" + oldestRelease + ")";
    }

    // calculate newest movie registered
    private String newestmovie() {
        int maxReleaseYear = Integer.MIN_VALUE;
        String newestTitle = "";
        String newestRelease = "";

        for (Movie x : movies) {
            int releaseYear = Integer.parseInt(x.release);

            if (releaseYear > maxReleaseYear) {
                maxReleaseYear = releaseYear;
                newestRelease = x.release;
                newestTitle = x.title;
            }
        }
        
        return newestTitle + " (" + newestRelease + ")";
    }
}