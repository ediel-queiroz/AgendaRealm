package br.edu.ifspsaocarlos.agenda.data;


import java.util.List;
import java.util.UUID;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import io.realm.Realm;
import io.realm.RealmResults;


public class ContatoDAO {

    private Realm realm;

    public void openConnection() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
    }

    public void closeConnection() {
        if (realm != null) {
            realm.close();
        }
    }

    public List<Contato> buscaTodosContatos() {
        RealmResults<Contato> query = realm.where(Contato.class).findAll();
        return query;
    }

    public Contato buscaContatoByUuid(final String uuid) {
        return realm.where(Contato.class).equalTo("uuid", uuid).findFirst();
    }

    public List<Contato> buscaContatoByNome(final String nome) {
        RealmResults<Contato> query = realm.where(Contato.class).contains("nome", nome).findAll();
        return query;
    }

    public void salvaContato(final Contato c) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (c.getUuid() == null) {
                    c.setUuid(UUID.randomUUID().toString());
                }
                realm.copyToRealmOrUpdate(c);
            }
        });
    }


    public void apagaContato(final Contato c) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                c.deleteFromRealm();
            }
        });
    }
}
