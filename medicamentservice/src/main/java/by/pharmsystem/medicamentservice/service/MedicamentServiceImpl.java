package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.repository.MedicamentRepository;
import by.pharmsystem.medicamentservice.service.util.validator.MedicamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentServiceImpl implements MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Override
    public void add(Medicament medicament) {
        MedicamentValidator.validateAddition(medicament);
        medicamentRepository.save(medicament);
    }

    @Override
    public Medicament findById() {
        return null;
    }

    @Override
    public List<Medicament> findByGroup(String group) {
        return null;
    }

    @Override
    public List<Medicament> findByName(String name) {
        return null;
    }
}
