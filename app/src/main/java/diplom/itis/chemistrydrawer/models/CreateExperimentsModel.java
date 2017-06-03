package diplom.itis.chemistrydrawer.models;


import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Денис on 21.04.2017.
 */

@Parcel
public class CreateExperimentsModel {

    public String name;
    public String description;

    public CreateExperimentsModel() {}

    public CreateExperimentsModel(String aName, String aDescription) {
        name = aName;
        description = aDescription;
    }
}
