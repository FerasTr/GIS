package Algorithm;

import File_format.Csv2kml;
import File_format.CsvReader;
import GIS.GIS_project;
import GIS.Project_GIS;

import java.io.File;
import java.io.FileFilter;

/**
 * This static class scans a folder recursively using the following algorithm:
 * 1) Scan for sub folders until none is found
 * 2) In the current folder look for CSV files
 * 3) Read the file then return
 * <p>
 * NOTE: JAVA 8 LAMBDA FUNCTION IS MORE RECOMMENDED FOR USE (NOT USED DUE TO COMPATIBILITY)
 * See:
 * https://stackoverflow.com/questions/5125242/java-list-only-subdirectories-from-a-directory-not-files
 * https://stackoverflow.com/questions/7486012/static-classes-in-java
 * <p>
 * This class also supports reading from a folder and reading then writing from a folder.
 */
public final class MultiCSV
{
    private static GIS_project project_from_folder = new Project_GIS();

    private MultiCSV()
    {
    }

    /**
     * Scan the folder then return a project object.
     *
     * @param folder
     * @return
     */
    public static GIS_project readFromFolder(String folder)
    {
        scan(folder);
        return project_from_folder;
    }

    /**
     * Scans folder for CSV using the above algorithm.
     *
     * @param folder_path Folder to look into
     */
    private static void scan(String folder_path)
    {
        File dir = new File(folder_path);
        // Look for sub folders in the current level.
        File[] files = dir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.isDirectory();
            }
        });
        // Keep looking until no folders are found
        if (files != null)
        {
            for (File child : files)
            {
                scan(child.getAbsolutePath());
            }
        }
        // Look for CSV files in the current folder.
        files = dir.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File file)
            {
                return file.getAbsolutePath().endsWith(".csv");
            }
        });
        // Read and create project object from all files.
        if (files != null)
        {
            for (File file : files)
            {
                project_from_folder.add(CsvReader.parsing(file.getAbsolutePath()));
            }
        }
    }

    /**
     * Read then write the project object into a KML file.
     *
     * @param folder_path path of the folder
     */
    public static void ConverToKML_Folder(String folder_path)
    {
        scan(folder_path);
        Csv2kml.toKML(project_from_folder);
    }
}
