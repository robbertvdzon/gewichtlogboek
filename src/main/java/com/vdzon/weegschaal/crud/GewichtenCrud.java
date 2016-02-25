package com.vdzon.weegschaal.crud;

import com.mongodb.MongoClient;
import com.vdzon.weegschaal.model.Gewicht;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GewichtenCrud {

    private Datastore datastore;

    public GewichtenCrud() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.mongodb.morphia.example");
        String mongoDbPortProperty = System.getProperties().getProperty("mongoDbPort");
        if (mongoDbPortProperty != null) {
            datastore = morphia.createDatastore(new MongoClient("localhost", Integer.parseInt(mongoDbPortProperty)), "morphia_example1");
        } else {
            datastore = morphia.createDatastore(new MongoClient(), "morphia_example1");
        }

        datastore.ensureIndexes();
    }

    public List<Gewicht> getAllGewichten() {
        return this.datastore.createQuery(Gewicht.class).asList();
    }

    public Gewicht getGewicht(String id) {
        return this.datastore.get(Gewicht.class, id);
    }

    public void addGewicht(Gewicht gewicht) {
        this.datastore.save(gewicht);
    }

    public void deleteGewicht(String uuid) {
        this.datastore.delete(Gewicht.class, uuid);
    }

    public void updateGewicht(Gewicht gewicht) {
        this.datastore.save(gewicht);
    }

}
