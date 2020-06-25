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
public class Yorum {
    private int yorum_id;
    private int paylasim_id;
    private String yorum;
    private Date Date;
    private int user_id;
    private User user;
    public Yorum() {
    }

    public Yorum(int yorum_id, int paylasim_id, String yorum, Date Date, int user_id, User user) {
        this.yorum_id = yorum_id;
        this.paylasim_id = paylasim_id;
        this.yorum = yorum;
        this.Date = Date;
        this.user_id = user_id;
        this.user = user;
    }
    
    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYorum_id() {
        return yorum_id;
    }

    public void setYorum_id(int yorum_id) {
        this.yorum_id = yorum_id;
    }

    public int getPaylasim_id() {
        return paylasim_id;
    }

    public void setPaylasim_id(int paylasim_id) {
        this.paylasim_id = paylasim_id;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}
