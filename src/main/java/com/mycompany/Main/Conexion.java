/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Main;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {

    // URI de conexión — misma que usa MongoDB Compass por defecto
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE = "Estudiantes";

    private static MongoClient mongoClient;

public static MongoDatabase getDatabase() {
    if (mongoClient == null) {
        mongoClient = MongoClients.create(URI);
        System.out.println("Conexión a MongoDB establecida.");
    }
    return mongoClient.getDatabase(DATABASE);
}

    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada.");
        }
    }
}