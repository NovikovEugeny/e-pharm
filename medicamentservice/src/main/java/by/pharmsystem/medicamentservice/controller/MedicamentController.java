package by.pharmsystem.medicamentservice.controller;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;

    @PostMapping("/add")
    public void add(@RequestBody Medicament medicament) {
        medicamentService.add(medicament);
    }
}
