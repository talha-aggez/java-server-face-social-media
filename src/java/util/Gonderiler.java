/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Gonderiler {
    private int user_id;
    private String paylasim;
    private Date date;
    private int paylasim_id;
    private User user;
    private String paylasim_id1;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Gonderiler() {
        
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPaylasim() {
        return paylasim;
    }

    public Date getDate() {
        return date;
    }

    public int getPaylasim_id() {
        return paylasim_id;
    }
    
    public Gonderiler(int user_id, String paylasim, Date date, int paylasim_id,User user) {
        this.user_id = user_id;
        this.paylasim = paylasim;
        this.date = date;
        this.paylasim_id = paylasim_id;
        this.user = user;
        this.paylasim_id1 = Integer.toString(paylasim_id);
    }

    public String getPaylasim_id1() {
        return paylasim_id1;
    }

    public void setPaylasim_id1(String paylasim_id1) {
        this.paylasim_id1 = paylasim_id1;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPaylasim(String paylasim) {
        this.paylasim = paylasim;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPaylasim_id(int paylasim_id) {
        this.paylasim_id = paylasim_id;
    }

    @Override
    public String toString() {
        return "Gonderiler{" + "paylasim_id=" + paylasim_id + '}';
    }
    
}
