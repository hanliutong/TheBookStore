/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MyBeans;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hanliutong
 */
@Local
public interface CartBeanLocal {

    List<String> getISBN();

    List<Integer> getNumber();

    void setISBN(String ISBN);

    void setNumber(int Number);

    void setISBNandNum(String isbn, int Num);

    int size();

    HashMap gets();

    void updateNum(String isbn, int Num);
    
}
