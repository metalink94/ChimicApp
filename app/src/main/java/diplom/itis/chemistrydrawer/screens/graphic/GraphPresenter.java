package diplom.itis.chemistrydrawer.screens.graphic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 15.01.2017.
 */

public class GraphPresenter {

    GraphView mView;

    GraphPresenter(GraphView view) {
        mView = view;
    }


    public void setAngles(int i) {
        mView.setAngles(i);
    }

    public void setAlertDialog(BufferedReader bufferedReader) {
        mView.showAlertDialog(readFile(bufferedReader));
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
