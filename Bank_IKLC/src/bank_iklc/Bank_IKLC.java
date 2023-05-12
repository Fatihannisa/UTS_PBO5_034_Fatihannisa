/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank_iklc;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Bank_IKLC {

    public static void main(String[] args) {
        // TODO code application logic here
        Bank_IKLC app = new Bank_IKLC();
        app.MenuUtama();
    }
        
    private static ArrayList<AkunBank> rekening = new ArrayList<>();
    String nama, alamat, gender, telp;
    int pilihan;

    public void MenuUtama() {
        Scanner keyboard = new Scanner(System.in);

        do{
            System.out.println("\n");
            System.out.println("Welcome to BANK IKLC ");
            System.out.println("1. Registrasi Akun");
            System.out.println("2. Cek Saldo");
            System.out.println("3. Pengiriman Uang");
            System.out.println("4. Penarikan Uang");
            System.out.println("5. Setoran Uang");
            System.out.println("0. Keluar");
            System.out.print("Silahkan pilih opsi: ");
            int pilihan;
            try{
                pilihan = keyboard.nextInt();
            }catch(Exception e){
                System.out.println("Invalid input! Inputan harus angka!");
                MenuUtama();
                return;
            }
            
            switch (pilihan) {
                case 1:
                    registrasi(keyboard);
                    break;
                case 2:
                    balance(keyboard);
                    break;
                case 3:
                    pengiriman(keyboard);
                    break;
                case 4:
                    penarikan(keyboard);
                    break;
                            
                case 5:
                    penyetoran(keyboard);
                    break;
                case 0:
                    System.out.println("Terima kasih telah bertransaksi di Bank IKLC.");
                    return;
                default:
                    System.out.println("Opsi tidak sesuai. Coba lagi!");
                    break;
            }
            System.out.println();
            
       } while (pilihan != 5);
    }

    private void registrasi(Scanner keyboard) {
        System.out.println("\n          Registrasi Rekening Baru");
        System.out.println("================================================");
                
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss a");
        
        System.out.println("Tanggal : " + sdf.format(now));
        
        nama = Question   ("Masukkan nama anda          : ");
        telp = Question   ("Masukkan nomor telepon      : ");
        alamat = Question ("Masukkan alamat anda        : ");
        gender = Question ("Masukkan jenis kelamin      : ");
        System.out.print  ("Masukkan saldo awal         : ");
        
        try {
            double balance = keyboard.nextDouble();
            System.out.println("Akun berhasil didaftarkan."); 
        AkunBank akun = new AkunBank(nama, balance, alamat, gender, telp);
        rekening.add(akun);
        } catch (Exception e) {
            System.out.println("Inputan harus berupa angka.\n");
            MenuUtama();
        }
    }
        
    private String Question(String pertanyaan){
        String isi = " ";
        Scanner input = new Scanner(System.in);
        System.out.print(pertanyaan);
        isi = input.next();
        return isi;
    }
        
    private static void balance(Scanner scanner) {
        System.out.println("\n          Cek Saldo");
        System.out.println("===============================");
        System.out.print("Masukkan nomor rekening : ");
        String nomor = scanner.next();

        AkunBank akun = find(nomor);
        if (akun != null) {
            System.out.println("\nSaldo akun Sdra/i  " + akun.getNama() + ": " + akun.getBalance());
        } else {
            System.out.println("Akun tidak ditemukan.");
        }
    }

    private static void pengiriman(Scanner scanner) {
        System.out.println("\n          Pengiriman");
        System.out.println("================================");
        System.out.print("Nomor rekening pengirim: ");
        String pengirim = scanner.next();
        System.out.print("Nomor rekening penerima: ");
        String penerima = scanner.next();
        System.out.print("Masukkan jumlah uang yang akan dikirim: ");
        double jumlah;
        try{
            jumlah = scanner.nextDouble();
        }catch(Exception e){
            System.out.println("Invalid input! Inputan harus angka!");
            return;
        }

        AkunBank kirim = find(pengirim);
        AkunBank terima = find(penerima);

        if (kirim != null && terima != null) {
            if (kirim.tarik(jumlah)) {
                terima.deposit(jumlah);
                System.out.println("Transfer berhasil.");
            } else {
                System.out.println("Saldo tidak mencukupi untuk melakukan transfer.");
            }
        } else {
            System.out.println("Akun pengirim atau penerima tidak ditemukan.");
        }
    }
    
    private static void penarikan(Scanner scanner) {
        System.out.println("\n          Penarikan");
        System.out.println("================================");
        System.out.print("Masukkan nomor rekening: ");
        String nomor = scanner.next();
        System.out.print("Masukkan jumlah uang yang akan ditarik: ");
        double jumlah;
        try{
            jumlah = scanner.nextDouble();
        }catch(Exception e){
            System.out.println("Invalid input! Inputan harus angka!");
            return;
        }

        AkunBank akun = find(nomor);
        if (akun != null) {
            if (akun.getBalance() >= jumlah) {
                akun.tarik(jumlah);
                System.out.println("\nPenarikan berhasil. Jumlah yang ditarik: " + jumlah);
                System.out.println("Saldo akun Sdra/i  " + akun.getNama() + " saat ini adalah : " + akun.getBalance());
            } else {
                System.out.println("Saldo tidak mencukupi untuk melakukan penarikan.");
            }
        } else {
            System.out.println("Akun tidak ditemukan.");
        }
    }

    private static void penyetoran(Scanner scanner) {
        System.out.println("\n          Setoran");
        System.out.println("===============================");
        System.out.print("Masukkan nomor rekening : ");
        String nomor = scanner.next();
        System.out.print("Masukkan jumlah uang yang akan disetor: ");
        double setor;
        try{
            setor = scanner.nextDouble();
        }catch(Exception e){
            System.out.println("Invalid input! Inputan harus angka!");
            return;
        }

        AkunBank akun = find(nomor);
        if (akun != null) {
            akun.deposit(setor);
            System.out.println("\nSetoran berhasil. Jumlah yang disetor: " + setor);
            System.out.println("Saldo akun Sdra/i  " + akun.getNama() + " saat ini adalah : " + akun.getBalance());
        } else {
            System.out.println("Akun tidak ditemukan.");
        }
    }

    private static AkunBank find(String noRek) {
        for (AkunBank akun : rekening) {
            if (akun.getNorek().equals(noRek)) {
                return akun;
            }
        }
        return null;
    }
}

