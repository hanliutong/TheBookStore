/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hanliutong
 */
@Entity
@Table(name = "BOOKTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booktable.findAll", query = "SELECT b FROM Booktable b"),
    @NamedQuery(name = "Booktable.findByIsbn", query = "SELECT b FROM Booktable b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Booktable.findByTitle", query = "SELECT b FROM Booktable b WHERE Upper(b.title) like :title"),
    @NamedQuery(name = "Booktable.findByAuthor", query = "SELECT b FROM Booktable b WHERE b.author = :author"),
    @NamedQuery(name = "Booktable.findByStock", query = "SELECT b FROM Booktable b WHERE b.stock = :stock"),
    @NamedQuery(name = "Booktable.findByPrice", query = "SELECT b FROM Booktable b WHERE b.price = :price")})
public class Booktable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "AUTHOR")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;

    public Booktable() {
    }

    public Booktable(String isbn) {
        this.isbn = isbn;
    }

    public Booktable(String isbn, String title, String author, int stock, double price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booktable)) {
            return false;
        }
        Booktable other = (Booktable) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Booktable[ isbn=" + isbn + " ]";
    }
    
}
