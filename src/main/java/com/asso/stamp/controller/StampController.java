package com.asso.stamp.controller;

import com.asso.stamp.business.StampEJB;
import com.asso.stamp.model.Stamp;

import javax.ejb.EJB;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;


@ManagedBean
@SessionScoped
public class StampController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private StampEJB stampEJB;

    private HtmlDataTable dataTable;

    private Stamp stamp = new Stamp();
    private ListDataModel stampList; // j'ai utilisé un ListDataModel et pas List parce que cela permet de retrouver l'élément sélectionné dans la liste (pour l'édition d'un livre)

    private void updateStampList() {
        stampList = new ListDataModel(stampEJB.findAll());
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doNew() {
        stamp = new Stamp();
        return "newStamp.xhtml";
    }

    public String doCreate() {
        stamp = stampEJB.create(stamp);
        return "listStamp.xhtml";
    }
    
    public String doCancel() {
        return "listStamp.xhtml";
    }

    public String doDelete(Stamp stmp) {
        stampEJB.delete(stmp);
        updateStampList();
        return "listStamp.xhtml";
    }

    private List<Stamp> onlySelected(List<Stamp> list) {
        for (Iterator<Stamp> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        stamp = (Stamp)stampList.getRowData(); // Voici comment on trouve le livre sélectionné
        return "editStamp.xhtml";
    }

    public String doSave() {
        stamp = stampEJB.update(stamp);
        return "listStamp.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }

    public ListDataModel getStampList() {
        updateStampList();
        return stampList;
    }

    public void setStampList(ListDataModel stampList) {
        this.stampList = stampList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}