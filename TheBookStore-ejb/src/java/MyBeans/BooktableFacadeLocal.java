/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyBeans;

import Entity.Booktable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hanliutong
 */
@Local
public interface BooktableFacadeLocal {

    void create(Booktable booktable);

    void edit(Booktable booktable);

    void remove(Booktable booktable);

    Booktable find(Object id);

    List<Booktable> findAll();
    List<Booktable> findByTitle(Object Title);
    List<Booktable> findByISBN(Object isbn);
    List<Booktable> findRange(int[] range);

    int count();
    
}
