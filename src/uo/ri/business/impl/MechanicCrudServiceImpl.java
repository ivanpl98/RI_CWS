package uo.ri.business.impl;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;

import java.util.List;

public class MechanicCrudServiceImpl implements MechanicCrudService {
    @Override
    public void addMechanic(MechanicDto mecanico) throws BusinessException {

    }

    @Override
    public void deleteMechanic(Long idMecanico) throws BusinessException {

    }

    @Override
    public void updateMechanic(MechanicDto mecanico) throws BusinessException {

    }

    @Override
    public MechanicDto findMechanicById(Long id) throws BusinessException {
        return null;
    }

    @Override
    public List<MechanicDto> findAllMechanics() throws BusinessException {
        return null;
    }

    @Override
    public List<MechanicDto> findActiveMechanics() throws BusinessException {
        return null;
    }
}
