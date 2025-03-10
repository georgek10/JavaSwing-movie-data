import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    // declare all UI components as private attributes
    private int intcode;

    private JLabel titleLbl;
    private JLabel releaseLbl;
    private JLabel durationLbl;
    private JLabel genreLbl;
    private JLabel languageLbl;
    private JLabel productionLbl;
    private JLabel starsLbl;

    private JTextField titleTf;
    private JTextField releaseTf;
    private JTextField durationTf;
    private JTextField genreTf;
    private JTextField languageTf;
    private JTextField productionTf;
    private JTextField starsTf;

    private JButton saveBtn;
    private JButton statisticsBtn;
    private JButton aboutBtn;
    private JButton closeBtn;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu infoMenu;
    private JMenuItem saveItem;
    private JMenuItem closeItem;
    private JMenuItem statisticsItem;
    private JMenuItem aboutItem;

    private ArrayList<Movie> movies;
    private Movie current;
    private Movie previous;

    public MainFrame()
    {
        // create components
        titleLbl = new JLabel("Title:");
        releaseLbl = new JLabel("Release date:");
        durationLbl = new JLabel("Duration:");
        genreLbl = new JLabel("Genre:");
        starsLbl = new JLabel("Rating stars:");
        languageLbl = new JLabel("Language:");
        productionLbl = new JLabel("Production:");

        titleTf = new JTextField(10);
        releaseTf = new JTextField(10);
        durationTf = new JTextField(10);
        genreTf = new JTextField(10);
        starsTf = new JTextField(10);
        languageTf = new JTextField(10);
        productionTf = new JTextField(10);

        saveBtn = new JButton("Save");
        statisticsBtn = new JButton("Statistics");
        aboutBtn = new JButton("About");
        closeBtn = new JButton("Close");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        infoMenu = new JMenu("Info");		

        saveItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        statisticsItem = new JMenuItem("Statistics");
        aboutItem = new JMenuItem("About");

        // create array list of registered movies
        movies = new ArrayList<>();
        previous = new Movie();

        // create main panel and side panels
        JPanel mainPanel = new JPanel();
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel row5 = new JPanel();
        JPanel row6 = new JPanel();
        JPanel row7 = new JPanel();
        JPanel btnPanel = new JPanel();

        row1.setLayout(new FlowLayout(FlowLayout.LEFT));
        row2.setLayout(new FlowLayout(FlowLayout.LEFT));
        row3.setLayout(new FlowLayout(FlowLayout.LEFT));
        row4.setLayout(new FlowLayout(FlowLayout.LEFT));
        row5.setLayout(new FlowLayout(FlowLayout.LEFT));
        row6.setLayout(new FlowLayout(FlowLayout.LEFT));
        row7.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        // add components to the panels
        row1.add(titleLbl);
        row1.add(titleTf);

        row2.add(releaseLbl);
        row2.add(releaseTf);

        row3.add(durationLbl);
        row3.add(durationTf);

        row4.add(genreLbl);
        row4.add(genreTf);

        row5.add(languageLbl);
        row5.add(languageTf);

        row6.add(productionLbl);
        row6.add(productionTf);

        row7.add(starsLbl);
        row7.add(starsTf);

        btnPanel.add(saveBtn);
        btnPanel.add(statisticsBtn);
        btnPanel.add(aboutBtn);
        btnPanel.add(closeBtn);

        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);
        mainPanel.add(row5);
        mainPanel.add(row6);
        mainPanel.add(row7);

        this.add(mainPanel);
        this.add(btnPanel, BorderLayout.PAGE_END);

        // create menu
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(closeItem);

        infoMenu.add(statisticsItem);
        infoMenu.addSeparator();
        infoMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(infoMenu);

        this.setJMenuBar(menuBar);

        // set up and show the frame
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Movie data registration");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        // add listeners to button components
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        statisticsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MovieDataExists()) {
                    // create & show StatisticsFrame
                    new StatisticsFrame();
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "MovieData.txt doesn't exist. Save movies first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        statisticsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MovieDataExists()) {
                    // create & show StatisticsFrame
                    new StatisticsFrame();
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "MovieData.txt doesn't exist. Save movies first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create & show AboutFrame
                new AboutFrame();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create & show AboutFrame
                new AboutFrame();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeApp();
            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApp();
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeApp();
            }
        });
    }

    // save all movie data in a text file
    private void save() {
        String title = titleTf.getText();
        String release = releaseTf.getText();
        String duration = durationTf.getText();
        String genre = genreTf.getText();
        String language = languageTf.getText();
        String production = productionTf.getText();
        String stars = starsTf.getText();

        current = new Movie(title, release);
        movies.add(current);

        // check if one or more fields are empty before saving
        if (anyEmptyFields()) {
            JOptionPane.showMessageDialog(MainFrame.this, "Fill all empty fields", "Save warning", JOptionPane.WARNING_MESSAGE);
        // check if movie's title and release date is the same as a previous' ones before saving
        } else if (current.title.equals(previous.title) && current.release.equals(previous.release)) {
            JOptionPane.showMessageDialog(MainFrame.this, "This movie is already registered", "Save Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // assign a code to each movie
            intcode++;
            String code = Integer.toString(intcode);
            // write movie data to text file
            try {
                FileWriter filewriter = new FileWriter("MovieData.txt", true);
                BufferedWriter writer = new BufferedWriter(filewriter);

                writer.write(code);
                writer.write(" ");
                writer.write(title);
                writer.write(";");
                writer.write(" ");
                writer.write(release);
                writer.write(" ");
                writer.write(duration);
                writer.write(" ");
                writer.write(genre);
                writer.write(" ");
                writer.write(language);
                writer.write(" ");
                writer.write(production);
                writer.write(" ");
                writer.write(stars);
                writer.write("\n");
                writer.close();

                JOptionPane.showMessageDialog(MainFrame.this, "Movie data successfully saved to /MovieData.txt", "Save completed", JOptionPane.INFORMATION_MESSAGE);
                
                // clear fields after save
                clearFields();
        
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Can't access file", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        
        previous.title = current.title;
        previous.release = current.release;
    }

    private void closeApp() {
        int i = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to close the app?");
        
        if (i == JOptionPane.YES_OPTION) {
            // check for unsaved data before closing
            if (emptyFields())
                System.exit(0);
            else
                JOptionPane.showMessageDialog(MainFrame.this, "There is unsaved data", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // check if all fields are empty
    private boolean emptyFields() {
        return titleTf.getText().isEmpty() && releaseTf.getText().isEmpty() && 
               durationTf.getText().isEmpty() && genreTf.getText().isEmpty() && 
               languageTf.getText().isEmpty() && productionTf.getText().isEmpty() && 
               starsTf.getText().isEmpty();
    }
    
    // check if any field is empty
    private boolean anyEmptyFields() {
        return titleTf.getText().isEmpty() || releaseTf.getText().isEmpty() || 
               durationTf.getText().isEmpty() || genreTf.getText().isEmpty() || 
               languageTf.getText().isEmpty() || productionTf.getText().isEmpty() || 
               starsTf.getText().isEmpty();
    }

    // clear all fields
    private void clearFields() {
        titleTf.setText("");
        releaseTf.setText("");
        durationTf.setText("");
        genreTf.setText("");
        languageTf.setText("");
        productionTf.setText("");
        starsTf.setText("");
    }
    
    // check if MovieData.txt exists
    private boolean MovieDataExists() {
        File file = new File("MovieData.txt");

        // check if the file exists
        if (file.exists()) {
            return true;
        }

        return false;
    }
}