package GIS;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Project implements GIS_project
{
    private MetadataOfCollection metadata_of_files;
    Set<GIS_layer> csv_folder;

    public Project()
    {
        metadata_of_files = new MetadataOfCollection();
        csv_folder = new LinkedHashSet<GIS_layer>();
    }

    @Override
    public Meta_data get_Meta_data()
    {
        return metadata_of_files;
    }

    @Override
    public int size()
    {
        return csv_folder.size();
    }

    @Override
    public boolean isEmpty()
    {
        return csv_folder.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return csv_folder.contains(o);
    }

    @Override
    public Iterator<GIS_layer> iterator()
    {
        return csv_folder.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return csv_folder.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return csv_folder.toArray(a);
    }

    @Override
    public boolean add(GIS_layer layers)
    {
        return csv_folder.add(layers);
    }

    @Override
    public boolean remove(Object o)
    {
        return csv_folder.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return csv_folder.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends GIS_layer> c)
    {
        return csv_folder.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return csv_folder.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return csv_folder.removeAll(c);
    }

    @Override
    public void clear()
    {
        csv_folder.clear();
    }
}
