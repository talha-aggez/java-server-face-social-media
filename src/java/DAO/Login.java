package DAO;


import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import util.Document;
import util.Gonderiler;
import util.User;
import util.Yorum;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class Login implements Serializable{
    private Document document = new Document();
    private List<Document> documentList;
    User user;
    private List<User> user_list,friend_list;
    public static User user1;
    private String paylasim;
    private String yorum;
    private List<Gonderiler> gonderiler_list,gonderiler_list2;
    private List<Yorum> yorum_list;
    private Part doc;
    private final String uploadTo = "C:/Users/ASUS/Desktop/proje/PROJE_DUZGUN/web/resources/pp/";
    private static String getSubmittedFileName(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
        }
    }
    return null;
}
    public String getUploadTo() {
        return uploadTo;
    }
    public String upload(){
        try{
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo+Paths.get(getSubmittedFileName(doc)));
            Files.copy(input,f.toPath());
            document = this.getDocument();
            document.setFilePath(f.getParent());
            document.setFileName(f.getName());
            document.setFileType(doc.getContentType());
            document.setUser_id(user1.getUser_id());
            LoginDAO.insert(document);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "resim-yukle";
    }
    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Document> getDocumentList() {
        this.documentList = LoginDAO.findAll();
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public List<Yorum> getYorum_list(Gonderiler gonderi) {
        yorum_list = LoginDAO.yorum_gonderiler(gonderi);
        return yorum_list;
    }

    public void setYorum_list(List<Yorum> yorum_list) {
        this.yorum_list = yorum_list;
    }
    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
    
     public List<User> getFriend_list() {
        friend_list = LoginDAO.arkadas_listesi(user1);
        return friend_list;
    }

    public void setFriend_list(List<User> friend_list) {
        this.friend_list = friend_list;
    }
    public List<Gonderiler> getGonderiler_list2() {
        gonderiler_list2 = LoginDAO.anasayfa_gonderiler(user1);
        return gonderiler_list2;
    }

    public void setGonderiler_list2(List<Gonderiler> gonderiler_list2) {
        this.gonderiler_list2 = gonderiler_list2;
    }

    public List<Gonderiler> getGonderiler_list() {
        gonderiler_list = LoginDAO.gonderiler(user1);
        return gonderiler_list;
    }

    public void setGonderiler_list(List<Gonderiler> gonderiler_list) {
        this.gonderiler_list = gonderiler_list;
    }
    
    public String getPaylasim() {
        return paylasim;
    }
    public void setPaylasim(String paylasim) {
        this.paylasim = paylasim;
    }
    public List<User> getUser_list(){
        user_list = LoginDAO.ara(user1);
        return user_list;
    }
    public void setUser_list(List<User> user_list) {
        this.user_list = user_list;
    }
    public  User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }
    
    public User getUser() {
        if(user == null)
            user = new User();
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    //Validate Login
    public String validateUsernamePassword(){
        boolean valid = LoginDAO.validate(user.getEmail(),user.getPassword());
        user1 = this.user;
        if(valid){
             user1 = LoginDAO.setting(user1.getEmail());
            return "admin";
        }
        else{
            return "login";
        }
    }
    public String kayit(){
       boolean validate = LoginDAO.kayitYap(user.getName(),user.getSurname(), user.getEmail(),user.getPassword(),user.getGender());
        if(validate)
            return "success";
        else
            return "unsuccess";
    }
    public String logout(){
        return "login";
    }
    public String anaSayfa(String email){
        this.user.setEmail(email);
        return "anasayfa";
    }
    public String setting(){
        user1 = LoginDAO.setting(user1.getEmail());
        return "setting";
    }
    public String kayit_güncelle(){
        user = user1;
        LoginDAO.kayit_guncelle(user1,user);
        return "setting";
    }
    public String ekle(User user){
        boolean validate = LoginDAO.ekle(user1,user);
        if(validate)
        return "arkadas_ekle";
        else
            return "admin";
    }
    public String profile(){
        user1 = LoginDAO.setting(user1.getEmail());
        return "profile";
    }
    public int arkadas_sayisi(){
        int a = LoginDAO.arkadas_sayisi(user1);
        return a;
    }
    public String gonder(){
        LoginDAO.gonder1(user1,paylasim);
        return "admin";
    }
    public boolean ekli_mi(User user){
        boolean validate = LoginDAO.ekli_mi(user1,user);
        return validate;
    }
     public boolean ekli_mi2(User user){
        boolean validate = LoginDAO.ekli_mi(user1,user);
        return !validate;
    }
     public String yorum_gonder(Gonderiler gonderi){
        LoginDAO.yorum_gonder(user1,gonderi,yorum);
        return "admin";
    }
    public void profil_fotosu(){
        document = LoginDAO.profil_fotosu(user1);
    }
}
