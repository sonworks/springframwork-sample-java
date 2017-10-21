package net.sonworks.hellospring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sonworks.hellospring.persistent.HomeMapper;

@Service
public class HomeService {

	@Autowired
	private HomeMapper homeMapper;

    public String searchDb() {
        HashMap<String,Object> param = new HashMap<String,Object>(2);
        param.put("employee_id", "E000100001");
        param.put("country", "newzealand");
        ArrayList result = homeMapper.searchDb(param);
        Iterator it = result.iterator();
        it.hasNext();
        Map<String, String> wk = new HashMap<String,String>();
        wk = (Map)it.next();
        return String.valueOf(wk.get("employee_name"));
    }
    
}
