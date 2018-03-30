package by.pharmsystem.medicamentservice;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.entity.Pharma;
import by.pharmsystem.medicamentservice.repository.PharmaRepository;
import by.pharmsystem.medicamentservice.service.MedicamentService;
import by.pharmsystem.medicamentservice.service.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MedicamentServiceTest {

    /*@Mock
    private MedicamentRepository medicamentRepository;

    @InjectMocks
    private MedicamentServiceImpl medicamentService;*/

   // @Autowired
    private MedicamentService medicamentService;

    //@Autowired
    private PharmaRepository pharmaRepository;

   /* @Test
    public void addTest() {
        Medicament medicament = new Medicament();
        medicament.setName("Ibuclin");
        medicament.setGroup("antipyretics");
        medicament.setForm("pills");
        medicament.setAmount("10 pills");
        medicament.setActiveSubstances("paracetamol - 125 mg, ibuprofen - 325 mg");
        medicament.setCountry("Belarus");
        medicament.setRecipe(true);
        medicament.setPrice(2.5);
        medicament.setQuantity(100);

        medicamentService.add(medicament);

        boolean isValid = true;
        try {
            medicamentService.add(new Medicament());
        } catch (BadRequestException exc) {
            isValid = false;
        }
        assertFalse(isValid);
    }

    @Test(expected = BadRequestException.class)
    public void addQuantityTest() {
        medicamentService.addQuantity(22, 0);
    }*/

    @Test
    public void test() {
        Medicament medicament = new Medicament();
        medicament.setId(1);
        medicament.setName("Ibuclin4");
        medicament.setGroup("antipyretics");
        medicament.setForm("pills");
        medicament.setAmount("10 pills");
        medicament.setActiveSubstances("paracetamol - 125 mg, ibuprofen - 325 mg");
        medicament.setCountry("Belarus");
        medicament.setRecipe(false);
        medicament.setPrice(2.5);
        medicament.setQuantity(100);

        Medicament medicament2 = new Medicament();
        medicament2.setId(2);
        medicament2.setName("Ibuclin4");
        medicament2.setGroup("antipyretics");
        medicament2.setForm("pills");
        medicament2.setAmount("10 pills");
        medicament2.setActiveSubstances("paracetamol - 125 mg, ibuprofen - 325 mg");
        medicament2.setCountry("Belarus");
        medicament2.setRecipe(false);
        medicament2.setPrice(2.5);
        medicament2.setQuantity(100);

        Medicament medicament3 = new Medicament();
        medicament3.setId(3);
        medicament3.setName("Ibuclin4");
        medicament3.setGroup("antipyretics");
        medicament3.setForm("pills");
        medicament3.setAmount("10 pills");
        medicament3.setActiveSubstances("paracetamol - 125 mg, ibuprofen - 325 mg");
        medicament3.setCountry("Belarus");
        medicament3.setRecipe(false);
        medicament3.setPrice(2.5);
        medicament3.setQuantity(100);

        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(medicament);
        medicaments.add(medicament2);
        medicaments.add(medicament3);

        System.out.println(medicaments);

        int id = 2;
        int quantity = 50;
        Medicament medicamentTest = medicaments.stream().filter(m -> m.getId() == 2).findFirst().orElse(null);
        System.out.println(medicamentTest);

        if (medicamentTest != null) {
            int newQuantity = medicament.getQuantity() + quantity;
            medicamentTest.setQuantity(newQuantity);
        }

        System.out.println(medicaments);
    }

    @Test
    public void newLogicTest() {

    }
}
