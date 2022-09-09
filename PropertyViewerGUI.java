import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * PropertyViewerGUI provides the GUI for the project. It displays the property
 * and strings, and it listens to button clicks.

 * @author Michael Kölling, David J Barnes, and Josh Murphy and Seyed Mohammad Reza Shahrestani 
 * @version 3.2.2
 */
public class PropertyViewerGUI
{
    // fields:
    private JFrame frame;
    private JPanel propertyPanel;
    private JLabel idLabel;
    private JLabel favouriteLabel;

    private JTextField hostIDLabel;
    private JTextField hostNameLabel;
    private JTextField neighbourhoodLabel;
    private JTextField roomTypeLabel;
    private JTextField priceLabel;
    private JTextField minNightsLabel;
    private JTextArea descriptionLabel;

    // Creating new variabes for the Statstics tab fields.
    private JTextField numberOfViewers;
    private JTextField totalSum;
    private String statisticsButton;

    private Property currentProperty;
    private PropertyViewer viewer;
    private boolean fixedSize;
    

    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewerGUI(PropertyViewer viewer)
    {
        currentProperty = null;
        this.viewer = viewer;
        fixedSize = false;
        makeFrame();
        this.setPropertyViewSize(400, 250);
    }

    // ---- public view functions ----
    /**
     * Display a given property
     */
    public void showProperty(Property property)
    {
        hostIDLabel.setText(property.getHostID());
        hostNameLabel.setText(property.getHostName());
        neighbourhoodLabel.setText(property.getNeighbourhood());
        roomTypeLabel.setText(property.getRoomType());
        priceLabel.setText("£" + property.getPrice());
        minNightsLabel.setText(property.getMinNights());

        //descriptionLabel.setText(property.getDescription());
    }

    /**
     * Set a fixed size for the property display. If set, this size will be used for all properties.
     * If not set, the GUI will resize for each property.
     */
    public void setPropertyViewSize(int width, int height)
    {
        propertyPanel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        fixedSize = true;
    }

    /**
     * Show a message in the status bar at the bottom of the screen.
     */
    public void showFavourite(Property property)
    {
        String favouriteText = " ";
        if (property.isFavourite()){
            favouriteText += "This is one of your favourite properties!";
        }
        favouriteLabel.setText(favouriteText);
    }

    /**
     * Show the ID in the top of the screen.
     */
    public void showID(Property property){
        idLabel.setText("Current Property ID:" + property.getID());
    }

    // ---- implementation of button functions ----

    /**
     * Called when the 'Next' button was clicked.
     */
    private void nextButton()
    {
        viewer.nextProperty();
    }

    /**
     * Called when the 'Previous' button was clicked.
     */
    private void previousButton()
    {
        viewer.previousProperty();
    }

    /**
     * Called when the 'View on Map' button was clicked.
     */
    private void viewOnMapsButton()
    {
        try{
            viewer.viewMap();
        }
        catch(Exception e){
            System.out.println("URL INVALID");
        }

    }

    /**
     * Called when the 'Toggle Favourite' button was clicked.
     */
    private void toggleFavouriteButton(){
        viewer.toggleFavourite();     
    }

    /**
     * by clicking the "Statistics" button in the makeFrame field, this methed will be called.
     * this methed creates a new window called "Statistics ..." that shows:
     * 1. the average price of the properties viewed by the user.
     * 2. the number of properties viewed by the user.
     */
    private void statisticsButton()
    {
        //crating a new frame and naming it to "Statistics ,,,"
        frame = new JFrame("Statistics ...");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the property pane in the center
        propertyPanel = new JPanel();
        propertyPanel.setLayout(new GridLayout(6,2));
        
        //creating the first field inside the Statistics windows to show the Viewed Properties.
        propertyPanel.add(new JLabel("Viewed Properties: "));
        numberOfViewers = new JTextField("" + viewer.getNumberOfPropertiesViewed());
        numberOfViewers.setEditable(false);
        propertyPanel.add(numberOfViewers);

        //creating the second field inside the Statistics windows to show the Average Price of Properties.
        propertyPanel.add(new JLabel("Average Price of Properties: "));
        totalSum = new JTextField("" + viewer.averagePropertyPrice());
        totalSum.setEditable(false);
        propertyPanel.add(totalSum);
        
        
        propertyPanel.setBorder(new EtchedBorder());
        contentPane.add(propertyPanel, BorderLayout.CENTER);

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1));
        

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);

        contentPane.add(flow, BorderLayout.WEST);

        // building is done - arrange the components 
        //makes the new windows frame to the size that should be.
        frame.pack();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }

    // ---- swing stuff to build the frame and all its components ----

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Portfolio Viewer Application");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the property pane in the center
        propertyPanel = new JPanel();
        propertyPanel.setLayout(new GridLayout(6,2));

        propertyPanel.add(new JLabel("HostID: "));
        hostIDLabel = new JTextField("default");
        hostIDLabel.setEditable(false);
        propertyPanel.add(hostIDLabel);

        propertyPanel.add(new JLabel("Host Name: "));
        hostNameLabel = new JTextField("default");
        hostNameLabel.setEditable(false);
        propertyPanel.add(hostNameLabel);

        propertyPanel.add(new JLabel("Neighbourhood: "));
        neighbourhoodLabel = new JTextField("default");
        neighbourhoodLabel.setEditable(false);
        propertyPanel.add(neighbourhoodLabel);

        propertyPanel.add(new JLabel("Room type: "));
        roomTypeLabel = new JTextField("default");
        roomTypeLabel.setEditable(false);
        propertyPanel.add(roomTypeLabel);

        propertyPanel.add(new JLabel("Price: "));
        priceLabel = new JTextField("default");
        priceLabel.setEditable(false);
        propertyPanel.add(priceLabel);

        propertyPanel.add(new JLabel("Minimum nights: "));
        minNightsLabel = new JTextField("default");
        minNightsLabel.setEditable(false);
        propertyPanel.add(minNightsLabel);

        propertyPanel.setBorder(new EtchedBorder());
        contentPane.add(propertyPanel, BorderLayout.CENTER);

        // Create two labels at top and bottom for the file name and status message
        idLabel = new JLabel("default");
        contentPane.add(idLabel, BorderLayout.NORTH);

        favouriteLabel = new JLabel(" ");
        contentPane.add(favouriteLabel, BorderLayout.SOUTH);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0, 1));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { nextButton(); }
            });
        toolbar.add(nextButton);

        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { previousButton(); }
            });
        toolbar.add(previousButton);

        JButton mapButton = new JButton("View Property on Map");
        mapButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { viewOnMapsButton(); }
            });
        toolbar.add(mapButton);

        JButton favouriteButton = new JButton("Toggle Favourite");
        favouriteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { toggleFavouriteButton(); }
            });
        toolbar.add(favouriteButton);

        // adding a new button that is called "Statistics" to the Portfolio Viewer Application.

        JButton statisticsButton = new JButton("Statistics");
        statisticsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { statisticsButton(); }
            });
        toolbar.add(statisticsButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);

        contentPane.add(flow, BorderLayout.WEST);

        // building is done - arrange the components     
        frame.pack();

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }    
}
