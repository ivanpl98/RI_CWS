package uo.ri.ui.administrator.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.common.ServiceFactory;

import javax.xml.ws.Service;

public class AddMechanicAction implements Action {

	@Override
	public void execute() {

		MechanicDto mechanic= new MechanicDto();
		// Get info
		mechanic.dni = Console.readString("Dni");
		mechanic.name = Console.readString("Name");
		mechanic.surname = Console.readString("Surname");

		MechanicCrudService mcs = ServiceFactory.getMechanicCrudService();
		try {
			mcs.addMechanic(mechanic);
		} catch(BusinessException ex){
			Console.println(ex.getMessage());
			return;
		}
		
		// Print result
		Console.println("Mechanic added");
	}

}
