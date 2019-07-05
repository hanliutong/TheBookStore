/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyBeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;

/**
 *
 * @author Hanliutong
 */
@Stateful
public class CartBean implements CartBeanLocal {

    private List<String> ISBN;
    
    private List<Integer> Number;
    private Map<String,Integer> map;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<String> getISBN() {
        if(this.ISBN == null){
            this.ISBN = new ArrayList<>();    
        }
        ISBN.clear();
        ISBN.addAll(map.keySet());
        return ISBN;
    }

    @Override
    public List<Integer> getNumber() {
        if(this.Number == null){
            this.Number = new ArrayList<>();    
        }
        Number.clear();
        Number.addAll(map.values());
        return Number;
    }

    @Override
    public void setISBN(String ISBN) {
        if(this.ISBN == null){
            this.ISBN = new ArrayList<>();    
        }
        this.ISBN.add(ISBN);
    }

    @Override
    public void setNumber(int Number) {
        if(this.Number == null){
            this.Number = new ArrayList<>();    
        }
        this.Number.add(Number);
    }

    @Override
    public void setISBNandNum(String isbn, int Num) {
        if(this.map == null){
            this.map = new HashMap<>();
        }
        if(map.containsKey(isbn)){
            int oldValue = map.put(isbn,Num);
            map.put(isbn,Num+oldValue);
        }
        else{
             map.put(isbn,Num);
        }
    }

    @Override
    public int size() {
        if (map == null){
            return 0;
        }
        return map.size();
    }

//    @Override
//    public HashMap gets() {
//        return (HashMap) map;
//    }

    @Override
    public void updateNum(String isbn, int Num) {
        if(this.map == null){
            this.map = new HashMap<>();
        }
        if (Num < 0){
            Num = 0;
        }
        if(Num == 0){
            map.remove(isbn);
        }
        else{
            map.put(isbn,Num);    
        }
        
        
    }
    
    
    
    
    
}
