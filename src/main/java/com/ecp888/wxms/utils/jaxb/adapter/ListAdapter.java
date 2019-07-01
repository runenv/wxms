package com.ecp888.wxms.utils.jaxb.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class ListAdapter<T extends Object> extends XmlAdapter<Object[], List<Object>>{

	@Override
	public Object[] marshal(List<Object> objects) throws Exception {
		Object [] dos = new Object[objects.size()];
        int i = 0;
        for (Object domain : objects) {
            dos[i++] = domain;
            System.out.println(domain.getClass().getSimpleName());
        }
        return dos;
	}

	@Override
	public List<Object> unmarshal(Object[] objects) throws Exception {
//		List<Object> list = new ArrayList<Object>(objects);
		List<Object> list =  Arrays.asList(objects);
        for (Object domain : list) {
            list.add(domain);
        }
        return list;
	}

}
