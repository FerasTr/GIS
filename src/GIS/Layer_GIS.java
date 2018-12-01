package GIS;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Layer_GIS implements GIS_layer
{
    private MetadataOfCollection_GIS metadata_of_file;
    private Set<GIS_element> csv_file;
    public String filename;

    public Layer_GIS(String filename)
    {
        metadata_of_file = new MetadataOfCollection_GIS();
        csv_file = new LinkedHashSet<GIS_element>();
        this.filename = filename;
    }

    @Override
    public Meta_data get_Meta_data()
    {
        return metadata_of_file;
    }

    @Override
    public int size()
    {
        return csv_file.size();
    }

    @Override
    public boolean isEmpty()
    {
        return csv_file.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return csv_file.contains(o);
    }

    @Override
    public Iterator<GIS_element> iterator()
    {
        return csv_file.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return csv_file.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return csv_file.toArray(a);
    }

    @Override
    public boolean add(GIS_element elements)
    {
        return csv_file.add(elements);
    }

    @Override
    public boolean remove(Object o)
    {
        return csv_file.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return csv_file.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends GIS_element> c)
    {
        return csv_file.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return csv_file.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return csv_file.removeAll(c);
    }

    @Override
    public void clear()
    {
        csv_file.clear();
    }

    public String toString()
    {
        StringBuilder out = new StringBuilder();
        for (GIS_element line : this.csv_file)
        {
            out.append(line.toString() + '\n');
        }
        return out.toString();
    }

    public String getFilename()
    {
        return this.filename;
    }
}
