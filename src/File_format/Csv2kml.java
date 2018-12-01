package File_format;

import GIS.GIS_layer;
import GIS.GIS_project;

public final class Csv2kml
{
    private Csv2kml()
    {
    }

    public static void readCSVFile(String CSVPathName)
    {
        toKML(CsvReader.parsing(CSVPathName));
    }

    public static void toKML(GIS_project project)
    {
        WriteToKML.FromFolder(project);
    }

    public static void toKML(GIS_layer layer)
    {
        WriteToKML.FromFile(layer);
    }
}
