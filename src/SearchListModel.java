import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class SearchListModel implements ListModel<String> {
    private ArrayList<String> array;


    public SearchListModel(){
        array = new ArrayList<String>();
    }

    @Override
    public int getSize() {
        return array.size();
    }

    @Override
    public String getElementAt(int index) {
        return array.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    public void addElement(String s){
        array.add(s);
    }

    public void removeAllElements(){
        array.clear();
    }

    public void removeElement(int index){
        array.remove(index);
    }

    public void addAllElements(ArrayList<String> arr){
        array.addAll(arr);
    }
}
