package tasker.utilities;

import javafx.collections.ObservableList;

public class ListUtilities {


    public static <T> void toggleElem(ObservableList<T> list, T o){
        if(list.contains(o)){
            list.remove(o);
        }else{
            list.add(o);
        }

    }
}
