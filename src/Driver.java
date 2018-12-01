import Algorithm.MultiCSV;
import File_format.CsvReader;
import File_format.WriteToKML;
import GIS.Layer_GIS;
import GIS.Project_GIS;

public class Driver
{
    public static void main(String[] args)
    {
        Layer_GIS layer1 = (Layer_GIS) CsvReader.parsing("Files/dir1/dir3/WigleWifi_20171201110209.csv");
        Layer_GIS layer2 = (Layer_GIS) CsvReader.parsing("Files/dir2/WigleWifi_20171203085618.csv");
        Project_GIS project = (Project_GIS) MultiCSV.readFromFolder("/Files");
        WriteToKML.FromFolder(project);
        WriteToKML.FromFile(layer1);
        WriteToKML.FromFile(layer2);
    }
}
