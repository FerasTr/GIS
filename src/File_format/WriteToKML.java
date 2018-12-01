package File_format;

import GIS.*;
import Geom.Point3D;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class WriteToKML
{
    private static DocumentBuilderFactory dom_factory;
    private static DocumentBuilder dom_builder;
    private static Document dom_document;
    private static Element xml_element;
    private static GIS_layer file;

    private WriteToKML()
    {
    }

    public static void FromFolder(GIS_project project)
    {
        init();
        try
        {
            Convert(project);
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }

    public static void FromFile(GIS_layer layer)
    {
        file = layer;
        init();
        Convert(layer);
        try
        {
            output(false);
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }

    private static void init()
    {
        dom_factory = DocumentBuilderFactory.newInstance();
        try
        {
            dom_builder = dom_factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        dom_document = dom_builder.newDocument();
        BuildXMLHeader();
    }

    private static void Convert(GIS_project project) throws TransformerException
    {
        Iterator<GIS_layer> iter_folder = project.iterator();
        while (iter_folder.hasNext())
        {
            GIS_layer layer = iter_folder.next();
            Convert(layer);
        }
        output(true);
    }

    private static void Convert(GIS_layer layer)
    {
        Iterator<GIS_element> iter_file = layer.iterator();
        while (iter_file.hasNext())
        {
            create_kml((Element_GIS) iter_file.next());
        }
    }

    private static void create_kml(Element_GIS line)
    {
        Element placemark, name, desc, time, format, point, coor;
        Metadata metadata = (Metadata) line.getData();
        Point3D location = (Point3D) line.getGeom();
        placemark = dom_document.createElement("Placemark");
        xml_element.appendChild(placemark);
        name = dom_document.createElement("name");
        name.appendChild(dom_document.createCDATASection(metadata.getSSID()));
        placemark.appendChild(name);
        desc = dom_document.createElement("description");
        desc.appendChild(dom_document.createCDATASection("MAC: <b>" + metadata.getMAC() + "</b><br/>Capabilities" +
                ": " + "<b>" + metadata.getAuthMode() + "</b><br/>Channel: <b>" + metadata.getChannel() + "</b><br" + "/>Timestamp: " + "<b>" + metadata.getUTC() + "</b><br/>Date: <b>" + metadata.getFirstSeen() + "</b>"));
        placemark.appendChild(desc);
        time = dom_document.createElement("TimeStamp");
        placemark.appendChild(time);
        format = dom_document.createElement("styleUrl");
        format.appendChild(dom_document.createTextNode(colorByChannel(metadata.getChannel())));
        placemark.appendChild(format);
        point = dom_document.createElement("Point");
        placemark.appendChild(point);
        coor = dom_document.createElement("coordinates");
        point.appendChild(coor);
        coor.appendChild(dom_document.createTextNode(location.y() + "," + location.x()));
    }

    private static void BuildXMLHeader()
    {
        Element header, header_doc, header_name;
        header = dom_document.createElement("kml");
        header.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
        dom_document.appendChild(header);
        header_doc = dom_document.createElement("Document");
        header.appendChild(header_doc);
        style("green", "http://maps.google.com/mapfiles/ms/icons/green-dot.png", header_doc);
        style("red", "http://maps.google.com/mapfiles/ms/icons/red-dot.png", header_doc);
        style("blue", "http://maps.google.com/mapfiles/ms/icons/blue-dot.png", header_doc);
        xml_element = dom_document.createElement("Folder");
        header_doc.appendChild(xml_element);
        header_name = dom_document.createElement("name");
        header_name.appendChild(dom_document.createTextNode("Wifi Networks"));
        xml_element.appendChild(header_name);
    }

    private static String colorByChannel(String channel)
    {
        double chan = Double.parseDouble(channel);
        if (chan <= 11)
        {
            return "#red";
        }
        else if (chan <= 50)
        {
            return "#green";
        }
        else
        {
            return "#blue";
        }
    }

    private static void output(boolean folder) throws TransformerException
    {
        TransformerFactory tranFacory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFacory.newTransformer();
        DOMSource src = new DOMSource(dom_document);
        aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = null;
        if (folder)
        {
            String date_name = getDateName();
            result = new StreamResult(new File("WigleWifi_" + date_name + ".kml"));
        }
        else
        {
            String fileName = ((Layer_GIS) file).getFilename().replace(".csv", ".kml");
            result = new StreamResult(new File(fileName));
        }
        aTransformer.transform(src, result);
    }

    private static void style(String name, String iconURL, Element root)
    {
        Element style, icon_look, icon, url;
        style = dom_document.createElement("Style");
        style.setAttribute("id", name);
        root.appendChild(style);
        icon_look = dom_document.createElement("IconStyle");
        style.appendChild(icon_look);
        icon = dom_document.createElement("Icon");
        icon_look.appendChild(icon);
        url = dom_document.createElement("href");
        url.appendChild(dom_document.createTextNode(iconURL));
        icon.appendChild(url);
    }

    private static String getDateName()
    {
        Calendar whole_date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_recorded = new Date();
        whole_date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        whole_date.setTime(date_recorded);
        return whole_date.getTimeInMillis() + "";
    }
}
