package diplom.itis.chemistrydrawer.models.api;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Денис on 06.05.2017.
 */
@Parcel
public class TaskStructureFields {

    public List<ModelsFields> models;
    public boolean todelete;
    public int pressure;
    public String data;
    public int temperature;
    public int status;
    public List<AdditivesFields> additives;
    public int structure;
    public int type;


    public static Map<String, Object> setModelToMap(TaskStructureFields model) {
        Map<String, Object> map = new HashMap<>();
        map.put("models", ModelsFields.setMapList(model.models));
        map.put("todelete", model.todelete);
        map.put("pressure", model.pressure);
        map.put("data", model.data);
        map.put("temperature", model.temperature);
        map.put("status", model.status);
        map.put("additives", AdditivesFields.setMapList(model.additives));
        map.put("structure", model.structure);
        map.put("type", model.type);
        return map;
    }
}
