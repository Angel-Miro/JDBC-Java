import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestMySQLJDBC {
    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAO();
        int registros = 0;

        /**
         * Insert
         * */
        /*
        Persona insertarPersona = new Persona("Mayra","Ixta","mayra@mail.com","5578998547");
        registros = personaDAO.insertar(insertarPersona);
        System.out.println("Registros insertados: " + registros + " -> " + insertarPersona.toString());
         */

        /**
         * Update
         * */
        /*
        Persona personaActualizada = new Persona(3,"Mayra", "Ixta", "mayra@mail.com", "5578998500");
        registros = personaDAO.actualizar(personaActualizada);
        System.out.println("Registros actualizado: " + registros + " -> " + personaActualizada.toString());
         */

        /**
         * Delete
         * */
        /*
        Persona personaEliminada = new Persona(3);
        registros = personaDAO.eliminar(personaEliminada);
        System.out.println("Registros eliminados: " + registros + " -> " + personaEliminada.toString());
         */

        /**
         * Select
         * */
        List<Persona> personas = personaDAO.getPersonas();
        personas.forEach(persona -> {
            System.out.println(persona.toString());
        });


    }
}
