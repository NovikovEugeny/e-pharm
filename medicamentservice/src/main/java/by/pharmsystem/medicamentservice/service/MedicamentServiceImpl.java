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
    public void addQuantity(long id, int quantity) {
        MedicamentValidator.validateQuantity(quantity);

        Medicament medicament = medicamentRepository.findOne(id);

        int newQuauntity = medicament.getQuantity() + quantity;
        medicament.setQuantity(quantity);

        medicamentRepository.save(medicament);
    }

    @Override
    public void delete(long id) {
        medicamentRepository.delete(id);
    }

    @Override
    public List<Medicament> findByGroup(String group) {
        return medicamentRepository.findByGroup(group);
    }

    @Override
    public List<Medicament> findByName(String name) {
        return medicamentRepository.findByNameIgnoreCase(name);
    }
}
