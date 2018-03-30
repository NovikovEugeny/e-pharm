package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;

import java.util.List;
import java.util.Map;

public interface MedicamentService {

    void add(Medicament medicament, String city);

    void addQuantity(long id, int quantity, String city);

    void delete(long id, String city);

    List<Medicament> findByGroup(String group, String city);

    List<Medicament> findByName(String name, String city);

    Map<Long, Double> getPrices(List<Long> identifiers, String city);

    Map<Long, Boolean> getRecipeRequirements(List<Long> identifiers);
}
