package com.ecp888.wxms.utils.jaxb.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapListAdapter extends XmlAdapter<MapEntity[], Map<String, List<Object>>>{

	@Override
	public MapEntity[] marshal(Map<String, List<Object>> map) throws Exception {
		MapEntity[] list = new MapEntity[map.size()];
        Set<String> keyset = map.keySet();
        int index =0;
        for(Iterator<String> itor=keyset.iterator();itor.hasNext();){
            MapEntity item = new MapEntity();
            item.key = itor.next();
            item.value = map.get(item.key);
            list[index++] = item;            
        }
        return list;
	}

	@Override
	public Map<String, List<Object>> unmarshal(MapEntity[] list) throws Exception {
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		for (int i = 0; i < list.length; i++) {
			MapEntity item = list[i];
			//map.put(item.key, item.value);
		}
		return map;
	}

}
