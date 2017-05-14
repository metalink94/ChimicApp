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
public class AdditivesFields {

    public int additive;
    public int amount;

    public static List<Map<String, Object>> setMapList(List<AdditivesFields> additives) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AdditivesFields additivesFields : additives) {
            Map<String, Object> map = new HashMap<>();
            map.put("additive", additivesFields.additive);
            map.put("amount", additivesFields.amount);
            list.add(map);
        }
        return list;
    }
}
