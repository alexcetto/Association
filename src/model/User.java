package model;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.Scanner.*;
/**
 * Created by alexandrecetto on 18/03/2016.
 */

@Entity
//@NamedQuery(name="showAllUsers", query="SELECT u FROM users u")
public class User {

    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private byte[] password;

    private String completeAddress;
    private short age;

    public User(String name, String email, char[] password, String completeAddress, short age) throws NoSuchAlgorithmException {
        this.name = name;
        this.email = email;
        this.password = hashPassword(password, getSalt(), 10, 100);;

        if(completeAddress != null)
            this.completeAddress = completeAddress;
        else
            this.completeAddress = "None";

        if (age != 0)
            this.age = age;
        else
            this.age = 1;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) throws NoSuchAlgorithmException {
        byte [] salt = getSalt();
        this.password = hashPassword(password, salt, 10, 100);
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        for(int i = 0; i<16; i++) {
            System.out.print(salt[i] & 0x00FF);
            System.out.print(" ");
        }
        return salt;
    }


    /**
     * Renvoie le hash d'un password, il est nécessaire d'enregistrer le nombre de tours et
     * le sel en même temps que le pass.
     * @param password : password à hasher, sous forme de byte array
     * @param salt : le sel à joindre, à générer avec la fonction getSalt()
     * @param iterations : nombre de tours
     * @param keyLength : longueur du pass, minimum 32 bytes
     * @return le passwword hashé avec PBKDF2. C'est un peu costaud pour cet exercice
     * mais au moins c'est bien fait et ça peut être réutilisé plus tard.
     */
    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
}
