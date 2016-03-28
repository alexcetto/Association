package com.mycompany.biblio.controller;

import java.util.Date;
import javax.ejb.Local;
import com.mycompany.biblio.model.Message;
 
/**
 * Local interface for chat lagic EJB
 * @author Danon
 */
@Local
public interface MessageManagerLocal {
 
    void sendMessage(Message msg);
 
    Message getFirstAfter(Date after);
 
}
