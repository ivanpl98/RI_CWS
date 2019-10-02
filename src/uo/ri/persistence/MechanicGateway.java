package uo.ri.persistence;

import uo.ri.business.dto.MechanicDto;

import java.util.List;

public interface MechanicGateway {

    void addMechanic(MechanicDto mechanic);

    void deleteMechanic(Long id);

    void updateMechanic(MechanicDto mechanic);

    List<MechanicDto> listMechanic();

    boolean isDeletable(Long id);

    boolean existsId(Long id);

    boolean existsDni(String dni);

}
