package diplom.itis.chemistrydrawer.models.api;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Денис on 06.05.2017.
 */
@Parcel
public class ModelsFields {

    public int model;
    public String name;

    public static List<Map<String, Object>> setMapList(List<ModelsFields> models) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ModelsFields modelsFields : models) {
            Map<String, Object> map = new HashMap<>();
            map.put("model", modelsFields.model);
            map.put("name", modelsFields.name);
            list.add(map);
        }
        return list;
    }
}
