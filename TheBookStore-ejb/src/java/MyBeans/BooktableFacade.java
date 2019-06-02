/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyBeans;

import Entity.Booktable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hanliutong
 */
@Stateless
public class BooktableFacade extends AbstractFacade<Booktable> implements BooktableFacadeLocal {
    @PersistenceContext(unitName = "TheBookStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BooktableFacade() {
        super(Booktable.class);
    }
    
}
