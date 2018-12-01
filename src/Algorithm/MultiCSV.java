package Algorithm;

import File_format.Csv2kml;
import File_format.CsvReader;
import GIS.GIS_project;
import GIS.Project_GIS;

import java.io.File;
import java.io.FileFilter;

public final class MultiCSV
{
    private static GIS_project project_from_folder = new Project_GIS();

    private MultiCSV()
    {
    }

    public static GIS_project readDirectory(String folder)
    {
        scan(folder);
        return project_from_folder;
    }

    private static void scan(String folder_path)
    {
        File dir = new File(folder_path);
        File[] files = dir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.isDirectory();
            }
        });

        if (files != null)
        {
            for (File child : files)
            {
                scan(child.getAbsolutePath());
            }
        }

        files = dir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.getAbsolutePath().endsWith(".csv");
            }
        });

        if (files != null)
        {
            for (File file : files)
            {
                project_from_folder.add(CsvReader.parsing(file.getAbsolutePath()));
            }
        }
    }

    public static void ConverToKML_Folder(String folder_path)
    {
        scan(folder_path);
        Csv2kml.toKML(project_from_folder);
    }
}
