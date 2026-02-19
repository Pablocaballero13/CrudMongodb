package com.mycompany.Main;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    private final MongoCollection<Document> coleccion;

    public EstudianteDAO() {
        MongoDatabase db = Conexion.getDatabase();
        this.coleccion = db.getCollection("si");
    }

    public void insertar(Estudiante e) {
        Document doc = new Document()
                .append("nombre", e.getNombre())
                .append("apellido", e.getApellido())
                .append("edad", e.getEdad())
                .append("carrera", e.getCarrera());
        coleccion.insertOne(doc);
        System.out.println("Estudiante insertado: " + doc.getObjectId("_id"));
    }

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> lista = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            Estudiante e = documentToEstudiante(doc);
            lista.add(e);
        }
        return lista;
    }

    public Estudiante buscarPorId(String id) {
        Document doc = coleccion.find(Filters.eq("_id", new ObjectId(id))).first();
        if (doc != null) {
            return documentToEstudiante(doc);
        }
        return null;
    }

    public void eliminar(String id) {
        DeleteResult result = coleccion.deleteOne(Filters.eq("_id", new ObjectId(id)));
        if (result.getDeletedCount() > 0) {
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("No se encontró el estudiante con ese ID.");
        }
    }

    private Estudiante documentToEstudiante(Document doc) {
        Estudiante e = new Estudiante();
        e.setId(doc.getObjectId("_id").toString());
        e.setNombre(doc.getString("nombre"));
        e.setApellido(doc.getString("apellido"));
        e.setEdad(doc.getInteger("edad"));
        e.setCarrera(doc.getString("carrera"));
        return e;
    }
}