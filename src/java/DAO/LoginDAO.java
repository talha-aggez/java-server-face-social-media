package DAO;




import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;
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
public class LoginDAO {
    public static boolean validate(String user,String password){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("SELECT EMAIL,PASSWORD FROM USER_TABLE WHERE EMAIL=? AND PASSWORD=? ");
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            
        }
        catch(Exception ex){
            System.out.println("Hata Bulundu");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return false;
    }
    public static boolean kayitYap(String name,String surname,String email,String password,String gender){
        int i = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("INSERT INTO USER_TABLE(NAME,SURNAME,EMAIL,PASSWORD,GENDER) VALUES(?,?,?,?,?)");
            ps.setString(1,name);
            ps.setString(2, surname);
            ps.setString(3,email);
            ps.setString(4, password);
            ps.setString(5, gender);
            i = ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("kayıt yapılamadı..");
        }
         finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        if(i>0){
            return true;
        }
        else
            return false;
    }
    public static User setting(String email){
        Connection con = null;
        PreparedStatement ps = null;
        User temp = new User();
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("SELECT * FROM USER_TABLE WHERE EMAIL=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               temp =  new User(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("PASSWORD"),rs.getString("EMAIL"),rs.getString("GENDER"));
            }
            
        }
        catch(Exception ex){
            System.out.println("Hata Bulundu");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return temp;
    }
    public static boolean kayit_guncelle(User user1 ,User user){
        /*UPDATE Customers
        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
        WHERE CustomerID = 1;*/
        Connection con = null;
        PreparedStatement ps = null;
        int i = 0;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("UPDATE USER_TABLE SET NAME=? ,SURNAME = ?,EMAIL= ?,PASSWORD = ? WHERE EMAIL = ?");
            ps.setString(1, user1.getName());
            ps.setString(2, user1.getSurname());
            ps.setString(3, user1.getEmail());
            ps.setString(4, user1.getPassword());
            ps.setString(5, user.getEmail());
            i = ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Hata Bulundu");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
          if(i>0){
            return true;
        }
        else
            return false;
    }
    public static List<User> ara(User user1){
        List<User> user_list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        User temp = new User();
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("SELECT * FROM USER_TABLE WHERE NAME LIKE ?");
            ps.setString(1, "%"+user1.getAranan()+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               if(user1.getUser_id() != rs.getInt("USER_ID")){
                  temp =  new User(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("PASSWORD"),rs.getString("EMAIL"),rs.getString("GENDER"));
                  user_list.add(temp);
               }
            }
            
        }
        catch(Exception ex){
            System.out.println("Hata Bulundu");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return user_list;
    }
    public static boolean ekle(User user1,User user){
        int i = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("INSERT INTO FRIEND_TABLE(USER_ID,FRIEND_ID) VALUES(?,?)");
            ps.setInt(1,user1.getUser_id());
            ps.setInt(2, user.getUser_id());
            i = ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("kayıt yapılamadı..");
        }
         finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        if(i>0){
            return true;
        }
        else
            return false;
    }
    public static int arkadas_sayisi(User user1){
        Connection con = null;
        DBConnection db = new DBConnection();
        con = db.connect();
        PreparedStatement ps = null;
        int sayac = 0;
        User user = new User();
        try{
            ps = con.prepareStatement("SELECT * FROM FRIEND_TABLE WHERE USER_ID = ?");
            ps.setInt(1, user1.getUser_id());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user.setUser_id(rs.getInt("USER_ID"));
                sayac++;
            }
        }
        catch(Exception ex){
            System.out.println();
        }
        return sayac;
    }
    public static void gonder1(User user1,String paylasim){
        int i = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("INSERT INTO PAYLASIM_TABLE(USER_ID,PAYLASIM,DATE) VALUES(?,?,CURRENT_DATE)");
            ps.setInt(1,user1.getUser_id());
            ps.setString(2,paylasim); 
            //ps.setInt(3, gonderi.getPaylasim_id());
            i = ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("kayıt yapılamadı..");
        }
         finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
    }
    public  static List<Gonderiler>  gonderiler(User user1){
        Connection con = null;
        List<Gonderiler> gonderiler_list = new ArrayList();
        PreparedStatement ps = null;
        Gonderiler temp = new Gonderiler();
        ResultSet rs = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("SELECT * FROM PAYLASIM_TABLE WHERE USER_ID = ?");
            ps.setInt(1, user1.getUser_id());
            rs = ps.executeQuery();
                while(rs.next()){
                   temp = new Gonderiler(rs.getInt("USER_ID"),rs.getString("PAYLASIM"),rs.getDate("DATE"),rs.getInt("PAYLASIM_ID"),user1);
                   gonderiler_list.add(temp);
                }
        }
        catch(Exception ex){
            System.out.println("Hata Bulundu");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return gonderiler_list;
    }
    public static  ArrayList<Integer> friend_id(User user1){
         ArrayList<Integer> friend_id = new ArrayList<>();
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         DBConnection db = new DBConnection();
         con = db.connect();
         try{
            ps = con.prepareStatement("SELECT * FROM FRIEND_TABLE WHERE USER_ID=(SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?)");
            ps.setInt(1, user1.getUser_id());
            rs  = ps.executeQuery();
            while(rs.next()){
                friend_id.add(rs.getInt("FRIEND_ID"));
            }
         }
         catch(Exception ex){
             System.out.println("Hata Bulundu..");
         }
         return friend_id;
    }
    public static User getUser(int i){
        User user1 = new User();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnection db = new DBConnection();
        con = db.connect();
         try{
            ps = con.prepareStatement("SELECT * FROM USER_TABLE WHERE USER_ID = ?");
            ps.setInt(1, i);
            rs  = ps.executeQuery();
            if(rs.next()){
                user1.setName(rs.getString("Name"));
                user1.setSurname(rs.getString("Surname"));
            }
         }
         catch(Exception ex){
             System.out.println("Hata Bulundu..");
         }
        return user1;
    }
    public static List<Gonderiler> anasayfa_gonderiler(User user1){
        ArrayList<Integer> friend_id = friend_id(user1);
        List<Gonderiler> gonderiler_list2 = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        Gonderiler temp = new Gonderiler();
        try{
           DBConnection db = new DBConnection();
            con = db.connect();
            for(int i = 0 ; i < friend_id.size() ; i++){
                ps = con.prepareStatement("SELECT * FROM PAYLASIM_TABLE WHERE USER_ID = ?");
                //ps = con.prepareStatement("SELECT * FROM PAYLASIM_TABLE WHERE USER_ID = (SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?);");
                ps.setInt(1,friend_id.get(i));
                rs = ps.executeQuery();
                  while(rs.next()){
                    temp = new Gonderiler(rs.getInt("USER_ID"),rs.getString("PAYLASIM"),rs.getDate("DATE"),rs.getInt("PAYLASIM_ID"),getUser(friend_id.get(i)));
                    gonderiler_list2.add(temp);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Veri çekilemedi..");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return gonderiler_list2;
    }
    public static List<User> arkadas_listesi(User user1){
        ArrayList<Integer> friend_id = friend_id(user1);
        List<User> arkadas_listesi = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User temp = new User();
        try{
            DBConnection db  = new DBConnection();
            con = db.connect();
            for(int i = 0 ; i < friend_id.size() ; i++){
                ps = con.prepareStatement("SELECT * FROM USER_TABLE WHERE USER_ID = ?");
                ps.setInt(1, friend_id.get(i));
                rs = ps.executeQuery();
                while(rs.next()){
                    temp = new User(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("PASSWORD"),rs.getString("EMAIL"),rs.getString("GENDER"));
                    arkadas_listesi.add(temp);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Hata bulundu..");
        }
        return arkadas_listesi;
    }
    public static boolean ekli_mi(User user1,User user){
        Connection con  = null;
        PreparedStatement ps = null;
        ResultSet rs  = null;
        try{
            DBConnection db  = new DBConnection();
            con = db.connect();
            //ps = con.prepareStatement("SELECT * FROM FRIEND_TABLE WHERE FRIEND_ID= ? AND USER_ID = ?");
            ps = con.prepareStatement("SELECT * FROM FRIEND_TABLE WHERE FRIEND_ID= ? AND USER_ID = (SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?)");
            ps.setInt(1,user.getUser_id());
            ps.setInt(2, user1.getUser_id());
            rs = ps.executeQuery();
            if(rs.next()){
                return false;
            }
        }
        catch(Exception ex){
            System.out.println("Hata bulundu..");
        }
        return true;
    }
     public static void yorum_gonder(User user1,Gonderiler gonderi,String yorum){
        int i = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("INSERT INTO YORUM_TABLE(USER_ID,YORUM,PAYLASIM_ID,DATE) VALUES(?,?,?,CURRENT_DATE)");
            ps.setInt(1,user1.getUser_id());
            ps.setString(2,yorum); 
            ps.setInt(3, gonderi.getPaylasim_id());
            i = ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("kayıt yapılamadı..");
        }
         finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
    }
    public static List<Yorum> yorum_gonderiler(Gonderiler gonderi){
        List<Yorum> gonderiler_list2 = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        Yorum temp = new Yorum();
        try{
           DBConnection db = new DBConnection();
            con = db.connect();
            ps = con.prepareStatement("SELECT * FROM YORUM_TABLE WHERE PAYLASIM_ID = ?");
            ps.setInt(1,gonderi.getPaylasim_id());
            rs = ps.executeQuery();
            while(rs.next()){
                temp = new Yorum(rs.getInt("YORUM_ID"),rs.getInt("PAYLASIM_ID"),rs.getString("YORUM"),rs.getDate("DATE"),rs.getInt("USER_ID"),getUser(rs.getInt("USER_ID")));
                 gonderiler_list2.add(temp);
             }
        }
        catch(Exception ex){
            System.out.println("Veri çekilemedi..");
        }
        finally{
            try{
            con.close();
            }
            catch(Exception ex){
                System.out.println("Hata Bulundu");
            }
        }
        return gonderiler_list2;
    }
    public static List<Document> findAll(){
        List<Document> dlist = new ArrayList();
        DBConnection db = new DBConnection();
        Connection con = null;
        try{
            con = db.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DOCUMENT_TABLE");
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                Document d = new Document();
                d.setId(rs.getInt("ID"));
                d.setFilePath(rs.getString("FILEPATH"));
                d.setFileName(rs.getString("FILENAME"));
                d.setFileType(rs.getString("FILETYPE"));
                dlist.add(d);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return dlist;
    }
    public static void insert(Document d){
        DBConnection db = new DBConnection();
        Connection con = null;
        String query = "INSERT INTO  DOCUMENT_TABLE(FILEPATH,FILENAME,FILETYPE,USER_ID) values(?,?,?,?)";
         try{
            con = db.connect();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,d.getFilePath());
            ps.setString(2,d.getFileName());
            ps.setString(3,d.getFileType());
            ps.setInt(4, d.getUser_id());
            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static Document profil_fotosu(User user1){
        Document temp = new Document();
         Connection con  = null;
        PreparedStatement ps = null;
        ResultSet rs  = null;
        try{
            DBConnection db  = new DBConnection();
            con = db.connect();
            //ps = con.prepareStatement("SELECT * FROM DOCUMENT_TABLE WHERE USER_ID=?");
            ps = con.prepareStatement("SELECT * FROM DOCUMENT_TABLE WHERE USER_ID=(SELECT USER_ID FROM USER_TABLE WHERE USER_ID = ?)");
            ps.setInt(1,user1.getUser_id());
            rs = ps.executeQuery();
            if(rs.next()){
                temp.setFileName(rs.getString("FILENAME"));
                temp.setFilePath(rs.getString("FILEPATH"));
                temp.setFileType(rs.getString("FILETYPE"));
            }
        }
        catch(Exception ex){
            System.out.println("Hata bulundu..");
        }
        return temp;
    }
}
