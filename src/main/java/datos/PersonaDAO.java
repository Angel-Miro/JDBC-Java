package datos;

import static datos.Conexion.*;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona(nombre, apellido, email, telefono) values (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE test.persona SET telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM test.persona WHERE id_persona = ?";

    public List<Persona> getPersonas() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                persona = new Persona(rs.getInt("id_persona"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(rs);
                close(pstmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }

    public int insertar(Persona persona) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int registros = 0;

        try {
            conn = getConnection();
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getApellido());
            pstm.setString(3, persona.getEmail());
            pstm.setString(4, persona.getTelefono());
            registros = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(pstm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int registrosActualizados = 0;
        try {
            conn = getConnection();
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, persona.getTelefono());
            pstm.setInt(2, persona.getIdPersona());
            registrosActualizados = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(pstm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registrosActualizados;
    }

    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int registrosEliminados = 0;
        try {
            conn = getConnection();
            pstm = conn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, persona.getIdPersona());
            registrosEliminados = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(pstm);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registrosEliminados;
    }
}
