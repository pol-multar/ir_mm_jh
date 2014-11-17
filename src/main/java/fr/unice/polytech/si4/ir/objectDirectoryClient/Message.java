package fr.unice.polytech.si4.ir.objectDirectoryClient;

import java.io.Serializable;

/**
 * Created by Max on 17/11/2014.
 */
public class Message implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String _contenu;

    public Message(String _contenu) {
        this._contenu = _contenu;
    }

    public Message() {
        this("");
    }

    public String getContenu() {
        return _contenu;
    }

    public String toString() {
        return  _contenu;
    }
}
