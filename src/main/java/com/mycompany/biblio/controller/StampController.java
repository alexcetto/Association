package com.mycompany.biblio.controller;

import com.mycompany.biblio.business.StampEJB;
import com.mycompany.biblio.model.Stamp;

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
        return "newBook.xhtml";
    }

    public String doCreate() {
        stamp = stampEJB.create(stamp);
        return "listBooks.xhtml";
    }
    
    public String doCancel() {
        return "listBooks.xhtml";
    }

    public String doDelete(Stamp stmp) {
        stampEJB.delete(stmp);
        updateStampList();
        return "listBooks.xhtml";
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
        return "editBook.xhtml";
    }

    public String doSave() {
        stamp = stampEJB.update(stamp);
        return "listBooks.xhtml";
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

    public void setBookList(ListDataModel stampList) {
        this.stampList = stampList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}