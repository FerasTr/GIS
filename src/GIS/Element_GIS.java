package GIS;

import Coords.*;
import Geom.Geom_element;
import Geom.Point3D;

public class Element_GIS implements GIS_element
{
    private Point3D geo_location;
    private Metadata metadata;

    public Element_GIS(String mac, String ssid, String auth_mode, String first_seen, String channel, String rssi,
                       String current_lat, String current_lon, String current_alt, String accuracy_meters, String type)
    {
        String point = "(" + current_lat + "," + current_lon + "," + current_alt + ")";
        this.geo_location = new Point3D(point);
        this.metadata = new Metadata(mac, ssid, auth_mode, first_seen, channel, rssi, accuracy_meters, type);
    }

    @Override
    public Geom_element getGeom()
    {
        return geo_location;
    }

    @Override
    public Meta_data getData()
    {
        return metadata;
    }

    @Override
    public void translate(Point3D vec)
    {
        this.geo_location = new MyCoords().add(this.geo_location, vec);
    }

    public String toString()
    {
        String point_string = "Location: " + geo_location.toFile();
        String metadata_string = "Metadata: " + metadata.toString();
        return point_string + " " + metadata_string;
    }
}
