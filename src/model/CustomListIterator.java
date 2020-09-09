package model;

import java.util.List;
import java.util.ListIterator;

/*
* This is a list iterator customized to suite this project
*/
public class CustomListIterator<T> implements ListIterator {

    private List<T> list;
    private int index = -1;
    private int size;

    public CustomListIterator(List<T> list) {
        this.list = list;
        this.size = list.size();
    }

    public boolean hasNext() {//test if iterator has next element
        return index < size - 1;
    }

    public boolean hasPrevious() {//test if iterator has previous element
        return index > 0;
    }

    public T next() {//returns the next element
        if (hasNext()) {
            index++;
            return list.get(index);
        }
        return null;
    }

    public T previous() {//returns the previous element
        if (hasPrevious()) {
            index--;
            return list.get(index);
        }
        return null;
    }

    public T current() {//returns the current element
        if (index > -1 && index < size) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
