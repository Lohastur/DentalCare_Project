package com.pabloreyes.clinic;

import com.pabloreyes.clinic.exceptions.ResourceNotFoundException;
import com.pabloreyes.clinic.entity.Odontologo;
import com.pabloreyes.clinic.service.impl.OdontologoServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
public class OdontologoServiceImplTests {

    private static OdontologoServiceImpl odontologoService;

    @BeforeClass
    public static void cargarDataSet() {
        odontologoService.registrarOdontologo(new Odontologo("Santiago", "Paz", 3455647));
    }

    @Test
    public void guardarOdontologo() {
        Odontologo odontologo = odontologoService.registrarOdontologo(new Odontologo("Juan", "Ramirez", 348971960));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscar(1L) == null);

    }

    @Test
    public void traerTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);
        System.out.println(odontologos);
    }

}
