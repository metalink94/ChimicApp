package diplom.itis.chemistrydrawer.models;


import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.drawer.DrawerModel;

/**
 * Created by Денис on 21.04.2017.
 */

@Parcel
public class CreateExperimentsModel {

    public String name;
    public String description;
    public List<DrawerModel> chartElements;

    public CreateExperimentsModel() {}

    public CreateExperimentsModel(String aName, String aDescription) {
        name = aName;
        description = aDescription;
        chartElements = new ArrayList<>();
    }

    public void addElements(List<DrawerModel> aMPointList) {
        chartElements.addAll(aMPointList);
    }

    public void addElement(DrawerModel aModel){
        chartElements.add(aModel);
    }
}
