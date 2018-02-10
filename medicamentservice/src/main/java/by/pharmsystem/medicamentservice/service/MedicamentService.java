package by.pharmsystem.medicamentservice.service;

import by.pharmsystem.medicamentservice.entity.Medicament;

import java.util.List;

public interface MedicamentService {

    void add(Medicament medicament);

    Medicament findById();

    List<Medicament> findByGroup(String group);

    List<Medicament> findByName(String name);
}
