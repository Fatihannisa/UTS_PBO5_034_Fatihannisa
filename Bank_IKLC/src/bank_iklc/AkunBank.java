/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_iklc;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author LENOVO
 */
public class AkunBank {
    private String nama;
    private double balance;
    String noRek = "";
    String tgl, alamat, gender, telp;
    private final Random random = new Random();

    
    Date now = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss a");
 
        
    public AkunBank(String nama, double balance, String alamat, String gender, String telp) {
        this.nama = nama;
        this.balance = balance;
        //this.noRek = noRek;
        this.alamat = alamat;
        this.gender = gender;
        this.telp = telp;
        this.tgl = tgl;
        myAccount();
    }

    public String getNama() {
        return nama;
    }

    public String getNorek(){
        return noRek;
    }
    
    public double getBalance() {
        return balance;
    }

    
    public void deposit(double setor) {
        balance += setor;
    }

    public boolean tarik(double jumlah) {
        if (balance >= jumlah) {
            balance -= jumlah;
            return true;
        }
        return false;
    }

    public void myAccount() {
        
        for(int i = 0; i<6; i++){
            noRek += random.nextInt(10);
        }
        
        System.out.println("\nInformasi akun anda");
        System.out.println("Nomor rekening      : " + noRek);
        System.out.println("Saldo Anda          : " + balance);
        System.out.println("Nama Anda           : " + nama);
        System.out.println("Alamat Anda         : " + alamat);
        System.out.println("Gender              : " + gender);
        System.out.println("Nomor Telepon       : " + telp);
        System.out.println("Tanggal Regstrasi   : " + sdf.format(now));
               
    }
}

