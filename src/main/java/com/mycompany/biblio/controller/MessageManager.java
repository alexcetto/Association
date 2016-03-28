package com.mycompany.biblio.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import com.mycompany.biblio.model.Message;
 
/**
 * Simple chat logic
 * @author Danon
 */
@Singleton
@Startup
public class MessageManager implements MessageManagerLocal {
 
    private final List messages =
            Collections.synchronizedList(new LinkedList());;
 
    @Override
    public void sendMessage(Message msg) {
        messages.add(msg);
        msg.setDateSent(new Date());
    }
 
    @Override
    public Message getFirstAfter(Date after) {
        if(messages.isEmpty())
            return null;
        if(after == null)
            return (Message)messages.get(0);
        /*for(Message m : messages) {
            if(m.getDateSent().after(after))
                return m;
        }*/
        
        Iterator i = messages.iterator();
        
        while(i.hasNext()){
            Message m = (Message)i.next();
            if(m.getDateSent().after(after))
                return m;
        }
        
        return null;
    }
 
}
