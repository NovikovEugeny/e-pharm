package by.pharmsystem.medicamentservice;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.repository.MedicamentRepository;
import by.pharmsystem.medicamentservice.service.MedicamentServiceImpl;
import by.pharmsystem.medicamentservice.service.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MedicamentServiceTest {

    @Mock
    private MedicamentRepository medicamentRepository;

    @InjectMocks
    private MedicamentServiceImpl medicamentService;

    @Test
    public void addTest() {
        Medicament medicament = new Medicament();
        medicament.setName("Ibuclin");
        medicament.setGroup("antipyretics");
        medicament.setForm("pills");
        medicament.setAmount("10 pills");
        medicament.setActiveSubstances("paracetamol - 125 mg, ibuprofen - 325 mg");
        medicament.setCountry("Belarus");
        medicament.setPrescription(true);
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
    }

}
