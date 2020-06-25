/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ASUS
 */
public class User {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String gender;
    private int user_id;
    private String aranan;
    private String paylasim;

    public String getPaylasim() {
        return paylasim;
    }

    public void setPaylasim(String paylasim) {
        this.paylasim = paylasim;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAranan() {
        return aranan;
    }

    public void setAranan(String aranan) {
        this.aranan = aranan;
    }
    
    public User(int user_id,String name, String surname, String password, String email, String gender) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.user_id = user_id;
    }

    public User() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", surname=" + surname + ", password=" + password + ", email=" + email + ", gender=" + gender + ", user_id=" + user_id + ", aranan=" + aranan + '}';
    }
    
}
