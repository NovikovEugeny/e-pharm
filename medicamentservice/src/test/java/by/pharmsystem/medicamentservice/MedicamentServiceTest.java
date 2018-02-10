package by.pharmsystem.medicamentservice;

import by.pharmsystem.medicamentservice.entity.Medicament;
import by.pharmsystem.medicamentservice.service.MedicamentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MedicamentServiceTest {

    @Mock
    private MedicamentService medicamentService;

    @Test
    public void addMedicamentTest() {
        medicamentService.add(new Medicament());
    }

}
