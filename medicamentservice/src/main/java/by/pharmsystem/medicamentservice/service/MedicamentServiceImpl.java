package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.entity.Pharma;
import by.pharmsystem.medicamentservice.repository.MedicamentRepository;
import by.pharmsystem.medicamentservice.repository.PharmaRepository;
import by.pharmsystem.medicamentservice.service.util.validator.MedicamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicamentServiceImpl implements MedicamentService {

    @Autowired
    private PharmaRepository pharmaRepository;

    @Override
    public void add(Medicament medicament, String city) {
        MedicamentValidator.validateAddition(medicament);

        Pharma pharma = pharmaRepository.findOne(city);
        List<Medicament> medicaments = pharma.getMedicaments();

        medicament.setId(medicaments.size() + 1);
        medicaments.add(medicament);

        pharmaRepository.save(pharma);
    }

    @Override
    public void addQuantity(long id, int quantity, String city) {
        MedicamentValidator.validateQuantity(quantity);

        Pharma pharma = pharmaRepository.findOne(city);
        List<Medicament> medicaments = pharma.getMedicaments();
        Medicament medicament = medicaments.stream().filter(m -> m.getId() == id).findFirst().orElse(null);

        if (medicament != null) {
            int newQuauntity = medicament.getQuantity() + quantity;
            medicament.setQuantity(quantity);
        }

        pharmaRepository.save(pharma);
    }

    @Override
    public void delete(long id, String city) {
        //medicamentRepository.delete(id);
    }

    @Override
    public List<Medicament> findByGroup(String group, String city) {
        return /*medicamentRepository.findByGroup(group);*/null;
    }

    @Override
    public List<Medicament> findByName(String name, String city) {
        return medicamentRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Map<Long, Double> getPrices(List<Long> identifiers) {
        Map<Long, Double> idPrice = new HashMap<>();
        identifiers.forEach(id -> idPrice.put(id, medicamentRepository.findOne(id).getPrice()));
        return idPrice;
    }

    @Override
    public Map<Long, Boolean> getRecipeRequirements(List<Long> identifiers) {
        Map<Long, Boolean> idRequirement = new HashMap<>();
        identifiers.forEach(id -> idRequirement.put(id, medicamentRepository.findOne(id).isRecipe()));
        return idRequirement;
    }
}
