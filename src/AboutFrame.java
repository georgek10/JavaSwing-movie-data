import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class AboutFrame extends JFrame {
    // declare all UI components as private attributes
    private JButton closeBtn;
    private JLabel aboutLbl;

    public AboutFrame() {
        // create components
        closeBtn = new JButton("Close");

        aboutLbl = new JLabel();

        aboutLbl.setBorder(new EmptyBorder(0,10,0,10));

        aboutLbl.setText("<html><h3>Movie Registration App</h3><br>"
                + "Developer: Georgios Koutoulakos,<br> "
                + "AM: 20390121,<br>"
                + "Project Construction Time: 1/6/2022-8/6/2022<br><br>"
                + "Athens, Greece</html>");

        // create panels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(closeBtn);
        bottomPanel.add(aboutLbl);

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(bottomPanel, BorderLayout.CENTER);

        // set up the frame
        this.setSize(300, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("About");
        this.setVisible(true);

        // add listener to component
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseFrame();
            }
        });
    }

    // private method to close window
    private void CloseFrame() {
        this.dispose();
    }
}