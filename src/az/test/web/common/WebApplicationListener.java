package az.test.web.common;

import az.test.domain.FlexibleList;
import az.test.repository.CommonDao;
import az.test.repository.implementation.CommonDaoImpl;
import az.test.service.CommonService;
import az.test.service.implementation.CommonServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener()
public class WebApplicationListener implements ServletContextListener{

    public WebApplicationListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        //TODO mutleq sorus!
        CommonDao commonDao=new CommonDaoImpl();
        CommonService commonService=new CommonServiceImpl(commonDao);
        List<FlexibleList> allList = commonService.getAllFlexibleList();
        Map<String, FlexibleList> flexibleListMap = new HashMap<>();
        for(FlexibleList list : allList) {
            flexibleListMap.put(list.getName(), list);
        }

        sce.getServletContext().setAttribute("flexibleListMap", flexibleListMap);

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

}
