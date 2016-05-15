package models;

import java.util.ArrayList;

/**
 *
 * @author williamding
 */
public class Hotel {
    private String preview;
    private String city;
    private User   owner;
    private ArrayList<Room> rooms;

    public Hotel() {
    }

    public Hotel(String preview, String city, User owner, ArrayList<Room> rooms) {
        this.preview = preview;
        this.city = city;
        this.owner = owner;
        this.rooms = rooms;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" + "preview=" + preview + ", city=" + city + ", owner=" + owner + ", rooms=" + rooms + '}';
    }
    
}
