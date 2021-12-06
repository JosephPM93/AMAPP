package Libs;

import Modelos.Conexion;
import java.time.LocalDate;

/**
 *
 * @author Javier
 */
public class Estadistica {
    Conexion c;
    LocalDate date;
    Long count;
    int[] datos;
    
    public Estadistica() {
        c = new Conexion();
        date = LocalDate.now();
    }
    
    public int[] casosConfirmados() {
        count = new Long(0);
        datos = new int[3];
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Sintomas = 1")).uniqueResult());
        datos[0] = count.intValue();
        
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Recuperado = 1")).uniqueResult());
        datos[1] = count.intValue();
        
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Fallecido = 1")).uniqueResult());
        datos[2] = count.intValue();
        
        return datos;
    }
    
    public int[] personasFallecidas() {
        count = new Long(0);
        datos = new int[4];
        date = LocalDate.now();
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Fallecido = 1 AND F_nacimiento between '" + date.minusDays(10950) + "' AND '"+ date.minusDays(6570) + "'" )).uniqueResult());
        datos[0] = count.intValue();
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Fallecido = 1 AND F_nacimiento between '" + date.minusDays(16425) + "' AND '"+ date.minusDays(10950) + "'" )).uniqueResult());
        datos[1] = count.intValue();
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Fallecido = 1 AND F_nacimiento between '" + date.minusDays(21900) + "' AND '"+ date.minusDays(16425) + "'" )).uniqueResult());
        datos[2] = count.intValue();
        
        count = ((Long) c.getSession().createQuery(("SELECT COUNT(*) FROM personas WHERE Fallecido = 1 AND F_nacimiento between '" + date.minusDays(27375) + "' AND '"+ date.minusDays(21900) + "'" )).uniqueResult());
        datos[3] = count.intValue();

        return datos;
    }
    
}
