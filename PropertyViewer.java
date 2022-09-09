
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * 
 * @author Michael Kölling and Josh Murphy and Seyed Mohammad Reza Shahrestani  
 * @version 1.1.12
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private Portfolio portfolio;
    private Property property;
    private Property id;
    
    // Creating a new variable for counting the index of the counter for poth next and previous property.
    private int index = 0;
    
    
    // Creating a new variable for counting the total views of properties.
    private int view= 0;
    
    // Creating a new variable to calculate the total price of viewed properties. 
    private int propertySum =0;
    

    /**
     *  it creates a guiPropertyViewer and displays it on screen.
     * 
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        
        //we define a variable to get the property from the Protfolio class, using the portfolio variable.
        property = portfolio.getProperty(0);
        
        //we are calling a method from PropertyViewerGUI class, called showProperty, using gui variable, to get the name of the property.
        gui.showProperty(property);
        
        //we are calling a method from PropertyViewerGUI class called showID, using gui variable, to get the id of the property.
        gui.showID(property);
        
        //we are calling a method from PropertyViewerGUI class called showFavourite, using gui variable, to see if the property is in favourite list or not.
        gui.showFavourite(property);
        
        //we use this to calculate the total price of viewed properties. 
        propertySum += property.getPrice();
        
        //incrementing the total views of properties.
        view++;
    }

    /**
     * it creates a nextProperty methed that shows the next propertie.
     * if we reach last proprerty, the program automaticly resets the counter to 0.
     * 
     */
    public void nextProperty()
    {
        //incrementing the index by one.
        index ++; 
        
        //declaring an if function to see if we have reached the last peopery or not.
        if(index%portfolio.numberOfProperties()==0)
        {
            index=0;
        }
        
        //we define a variable to get the property from the Protfolio class, using the portfolio variable.
        //and we use the variable index to show the exact propery that we want to show.
        property = portfolio.getProperty(index);
        
        //we are calling a method from PropertyViewerGUI class, called showProperty, using gui variable, to get the name of the property.
        gui.showProperty(property);
        
        //we are calling a method from PropertyViewerGUI class called showID, using gui variable, to get the id of the property.
        gui.showID(property);
        
        //we are calling a method from PropertyViewerGUI class called showFavourite, using gui variable, to see if the property is in favourite list or not.
        gui.showFavourite(property);
        
        //we use this to calculate the total price of viewed properties.
        propertySum += property.getPrice();
        
        //incrementing the total views of properties.
        view++;
    }

    /**
     * it creates a previousProperty methed that shows the previous propertie.
     */
    public void previousProperty()
    {
        // reducing the index by one.
        index --;
        
        //declaring an if function to see if we have reached the first peopery or not.
        if(index== -1)
        {
            index= portfolio.numberOfProperties()-1;
        }
        
        //we define a variable to get the property from the Protfolio class, using the portfolio variable.
        //and we use the variable index to show the exact propery that we want to show.
        property = portfolio.getProperty(index);
        
        //we are calling a method from PropertyViewerGUI class, called showProperty, using gui variable, to get the name of the property.
        gui.showProperty(property);
        
        //we are calling a method from PropertyViewerGUI class called showID, using gui variable, to get the id of the property.
        gui.showID(property);
        
        //we are calling a method from PropertyViewerGUI class called showFavourite, using gui variable, to see if the property is in favourite list or not.
        gui.showFavourite(property);
        
        //we use this to calculate the total price of viewed properties.
        propertySum += property.getPrice();
        
        //incrementing the total views of properties.
        view++;
    }

    /**
     * this methed adds your current property to the favourite properties, 
     * by showing "this is one of your favourite properties!" on the down left hand side of the Portfilio Viewr.
     */
    public void toggleFavourite()
    {
        //we are calling toggleFavourite from Property class using property variable.
        property.toggleFavourite();
        
        //we are calling a method from PropertyViewerGUI class called showFavourite, using gui variable, to set/remove the property to favourite.
        gui.showFavourite(property);

    }

    //----- methods for challenge tasks -----
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
        double latitude = 51.512793;
        double longitude = -0.117149;
        
        //declaring latityde variable for the x possiton of the location.
        //then calling the getLatitude from Property class, using property variable.
        latitude = property.getLatitude();
        
        //declaring longitude variable for the y possiton of the location.
        //then calling the getLongitude from Property class, using property variable.
        longitude = property.getLongitude();

        URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
        java.awt.Desktop.getDesktop().browse(uri); 
    }

    /**
     * this methed returns the number of properties viewed by the user.
     */
    public int getNumberOfPropertiesViewed()
    {
        //returns the total view.
        return view;
    }

   
    /**
     * this method returns the average price of the properties viewed by the user.
     */
    public int averagePropertyPrice()
    {
        // declaring an int variable called avg to calculate the average price of the property viewed by the user.
        int avg = propertySum/view;
        
        //returns the avg.
        return avg;
    }
    
    
}
