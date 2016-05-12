package com.asso.stamp.controller;

import java.util.Date;
import javax.ejb.Local;
import com.asso.stamp.model.Message;
 
/**
 * Local interface for chat lagic EJB
 * @author Danon
 */
@Local
public interface MessageManagerLocal {
 
    void sendMessage(Message msg);
 
    Message getFirstAfter(Date after);
 
}
