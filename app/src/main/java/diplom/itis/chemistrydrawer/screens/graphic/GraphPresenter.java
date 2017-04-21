package diplom.itis.chemistrydrawer.screens.graphic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.utils.Presenter;

/**
 * Created by Денис on 15.01.2017.
 */

public class GraphPresenter extends Presenter<GraphView> {

    GraphPresenter(GraphView view) {
        setView(view);
    }


    public void setAngles(int i) {
        getView().setAngles(i);
    }

    public void setAlertDialog(BufferedReader bufferedReader) {
        getView().showAlertDialog(readFile(bufferedReader));
    }

    public void setAlertDialog(String[] colors) {
        getView().showAlertDialogColor(colors);
    }

    private String[] readFile(BufferedReader bufferedReader) {
        BufferedReader reader = null;
        List<String> temps = new ArrayList<>();
        try {
            reader = bufferedReader;
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                temps.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        String[] tempsArray = temps.toArray(new String[0]);
        return tempsArray;
    }
}
