package uo.ri.common;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.impl.MechanicCrudServiceImpl;

public class ServiceFactory {

    public static MechanicCrudService getMechanicCrudService() {
        return new MechanicCrudServiceImpl();
    }
}
