package LemburanKu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Desktop;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class LemburanKu {

    public static int gpb;
    public static String uname2;
    public static int simpanData;
    DecimalFormat kursIndonesia;
    DecimalFormatSymbols formatRp;

    public static void main(String[] args) throws IOException, URISyntaxException {

        Scanner s = new Scanner(System.in);
        int gajiPerBulan = 0;
        int input = 0;
        int input1 = 0;
        int menu = 0;
        String pilihan = "";

        /* Username dan Password dari Input User */
        String formattedDate;

        do {
            int width = 100;
            int height = 30;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.setFont(new Font("SansSerif", Font.BOLD, 14));
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.drawString("LemburanKu", 4, 13);

            for (int y = 0; y < height; y++) {
                StringBuilder sb = new StringBuilder();
                for (int x = 0; x < width; x++) {
                    sb.append(image.getRGB(x, y) == -16777216 ? " " : "*");
                }
                if (sb.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(sb);
            }

            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("\nE, MMM dd yyyy (HH:mm:ss)");
            formattedDate = date.format(format);
            System.out.println("" + formattedDate);
            System.out.println(" ");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("\nPilih menu : ");
            input1 = s.nextInt();
            System.out.println(" ");

            String uname, passwd;

            switch (input1) {

                case 1: // Register
                    Scanner scanusername = new Scanner(System.in);
                    Scanner scanpassword = new Scanner(System.in);
                    System.out.println("========== Register ========== ");
                    System.out.println("\n(Username --> nama lengkap)");
                    System.out.print("Username : ");
                    uname = scanusername.nextLine();
                    System.out.println("\n(Password --> tanggal lahir format yyyymmdd)");
                    System.out.print("Password : ");
                    passwd = scanpassword.nextLine();
                    boolean usersama = false;
                    System.out.print("\nMasukkan nominal gaji per bulan : Rp ");
                    gajiPerBulan = s.nextInt();
                    boolean isianSalah = false;
                    if (uname.equalsIgnoreCase("") || passwd.equalsIgnoreCase("") || gajiPerBulan <= 0) {
                        isianSalah = true;
                    }
                    if (isianSalah) {
                        if (uname.equalsIgnoreCase("") || passwd.equalsIgnoreCase("")) {
                            System.out.println("\nInput tidak boleh kosong!\n");
                        }
                        if (gajiPerBulan <= 0) {
                            System.out.println("\nNominal gaji harus positif!\n");
                        }
                    } else {
                        if (passwd.length() != 8) {
                            System.out.println("\nPassword harus 8 digit.\n");
                        } else {
                            FileReader baca = new FileReader("account.txt");
                            BufferedReader bufferbaca2 = new BufferedReader(baca);
                            String regis2 = bufferbaca2.readLine();
                            int count1 = 0;
                            while (regis2 != null) {
                                count1++;
                                regis2 = bufferbaca2.readLine();
                            }
                            bufferbaca2.close();
                            String[] dataregisuser = new String[count1];
                            FileReader baca2 = new FileReader("account.txt");
                            BufferedReader bufferbaca = new BufferedReader(baca2);
                            int count = 0;
                            String regis1 = bufferbaca.readLine();
                            while (regis1 != null) {
                                String[] regis3 = regis1.split("_");
                                dataregisuser[count] = regis3[0];
                                regis1 = bufferbaca.readLine();
                                count++;
                            }
                            bufferbaca.close();
                            for (int i = 0; i < dataregisuser.length; i++) {
                                if (uname.equals(dataregisuser[i])) {
                                    usersama = true;
                                }
                            }
                            if (usersama) {
                                System.out.println("\nUser telah terdaftar!\n");
                            } else {
                                FileWriter writer = new FileWriter("account.txt", true);
                                BufferedWriter buffer = new BufferedWriter(writer);
                                buffer.write(uname + "_" + passwd + "_" + gajiPerBulan);
                                buffer.flush();
                                buffer.newLine();
                                buffer.close();
                                System.out.println("\n>> Anda telah berhasil register.\n");
                            }
                        }
                    }
                    break;

                case 2: // Login
                    FileReader reader = new FileReader("account.txt");
                    BufferedReader buffer2 = new BufferedReader(reader);
                    String data2 = buffer2.readLine();
                    int angka1 = 0;
                    while (data2 != null) {
                        angka1++;
                        data2 = buffer2.readLine();
                    }
                    buffer2.close();
                    String[] datauname = new String[angka1];
                    String[] datapasswd = new String[angka1];
                    String[] datagaji = new String[angka1];
                    FileReader reader2 = new FileReader("account.txt");
                    BufferedReader buffer3 = new BufferedReader(reader2);
                    int angka = 0;
                    String data1 = buffer3.readLine();
                    while (data1 != null) {
                        String[] data3 = data1.split("_");
                        datauname[angka] = data3[0];
                        datapasswd[angka] = data3[1];
                        datagaji[angka] = data3[2];
                        data1 = buffer3.readLine();
                        angka++;
                    }
                    buffer3.close();
                    int simpanan = 0;
                    Scanner scanusername1 = new Scanner(System.in);
                    Scanner scanpassword1 = new Scanner(System.in);
                    while (!pilihan.equalsIgnoreCase("n")) {
                        System.out.println("========== Login ==========");
                        System.out.print("\nUsername : ");
                        uname = scanusername1.nextLine();
                        System.out.print("Password : ");
                        passwd = scanpassword1.nextLine();
                        boolean usernamebenar = false;
                        boolean passwordbenar = false;
                        boolean bisalogin = false;

                        for (int i = 0; i < datauname.length; i++) {
                            if (uname.equals(datauname[i])) {
                                usernamebenar = true;
                                if (passwd.equals(datapasswd[i])) {
                                    passwordbenar = true;
                                    bisalogin = true;
                                    simpanan = i;
                                }
                            }
                        }
                        if (bisalogin) {
                            System.out.println("\n********** Selamat Datang di LemburanKu **********\n");
                            for (int i = 0; i < datauname.length; i++) {
                                if (i == simpanan) {
                                    gpb = Integer.parseInt(datagaji[simpanan]);
                                }
                            }
                            uname2 = uname;
                            simpanData = simpanan;
                            while (!pilihan.equalsIgnoreCase("n")) {

                                System.out.println("\n========== Menu LemburanKu ==========");
                                System.out.println("1. Database LemburanKu");
                                System.out.println("2. Lihat ketentuan KEP.102/MEN/VI/2004 Kemnaker");
                                System.out.println("3. Keluar LemburanKu");
                                System.out.print("\nPilih menu : ");
                                input = s.nextInt();

                                switch (input) {

                                    case 1:
                                        // Database LemburanKu
                                        do {
                                            System.out.println("");
                                            System.out.println("========== Database LemburanKu ==========\n");
                                            System.out.println("1. Lihat seluruh laporan lembur kerja");
                                            System.out.println("2. Cari data laporan lembur kerja tertentu");
                                            System.out.println("3. Tambah data laporan lembur kerja");
                                            System.out.println("4. Ubah data laporan lembur kerja");
                                            System.out.println("5. Hapus data laporan lembur kerja");
                                            System.out.println("6. Keluar dari database");

                                            System.out.print("\nPilih menu : ");
                                            menu = s.nextInt();

                                            switch (menu) {
                                                case 1:
                                                    System.out.println("\n====================");
                                                    System.out.println("LIST SELURUH LAPORAN");
                                                    System.out.println("====================");
                                                    tampilkanData();
                                                    break;

                                                case 2:
                                                    System.out.println("\n============");
                                                    System.out.println("CARI LAPORAN");
                                                    System.out.println("============");
                                                    cariData();
                                                    break;

                                                case 3:
                                                    System.out.println("\n===================");
                                                    System.out.println("TAMBAH DATA LAPORAN");
                                                    System.out.println("===================");
                                                    tambahData();
                                                    break;

                                                case 4:
                                                    System.out.println("\n=================");
                                                    System.out.println("UBAH DATA LAPORAN");
                                                    System.out.println("=================");
                                                    updateData();
                                                    break;

                                                case 5:
                                                    System.out.println("\n==================");
                                                    System.out.println("HAPUS DATA LAPORAN");
                                                    System.out.println("==================");
                                                    deleteData();
                                                    break;

                                                case 6:
                                                    break;

                                                default:
                                                    System.out.println("\nInput Anda tidak ditemukan.\nSilahkan pilih nomor [1-6].\n");
                                            }
                                        } while (menu != 6);
                                        break;

                                    case 2:
                                        // Menampilkan ketentuan perhitungan pembayaran lembur
                                        System.out.println("\nSilakan akses link dibawah ini : ");
                                        System.out.println("https://jdih.kemnaker.go.id/data_puu/peraturan_file_186.pdf");
                                        Desktop d = Desktop.getDesktop();
                                        d.browse(new URI("https://jdih.kemnaker.go.id/data_puu/peraturan_file_186.pdf"));
                                        System.out.println(" ");
                                        System.out.print("Apakah Anda ingin melanjutkan? (ketik y/n) : ");
                                        pilihan = s.next();
                                        break;

                                    case 3:
                                        System.out.println("********** Terima kasih telah menggunakan LemburanKu **********");
                                        System.exit(0);

                                    default:
                                        System.out.println("\nInput tidak ditemukan.\nSilakan pilih nomor [1-3].\n");
                                        System.out.print("Apakah Anda ingin melanjutkan? (ketik y/n) : ");
                                        pilihan = s.next();
                                }
                            }
                            System.out.println("********** Terima kasih telah menggunakan LemburanKu **********");
                        } else {
                            System.out.println("\nUsername atau Password yang Anda masukkan salah!\n");
                        }
                    }
                default:
                    System.out.println("\nInput tidak ditemukan.\nSilakan pilih nomor [1-2].\n");
            }
        } while (input1 != 2);
    }

    public static void tampilkanData() throws IOException {

        String uname = uname2;
        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferInput = new BufferedReader(fileInput);

        String data = bufferInput.readLine();
        int baris = 0;
        while (data != null) {
            baris++;
            data = bufferInput.readLine();
        }
        bufferInput.close();
        String[] tampilData = new String[baris];
        String[] jenishari = new String[baris];
        String[] tanggal = new String[baris];
        String[] keterangan = new String[baris];
        String[] jamLembur = new String[baris];
        String[] totalUpah = new String[baris];

        FileReader fileInput2 = new FileReader("database.txt");
        BufferedReader bufferInput2 = new BufferedReader(fileInput2);

        int baris2 = 0;
        String tampilData2 = bufferInput2.readLine();
        while (tampilData2 != null) {
            String[] data2 = tampilData2.split("_");
            tampilData[baris2] = data2[0];
            jenishari[baris2] = data2[1];
            tanggal[baris2] = data2[2];
            keterangan[baris2] = data2[3];
            jamLembur[baris2] = data2[4];
            totalUpah[baris2] = data2[5];
            tampilData2 = bufferInput2.readLine();
            baris2++;
        }
        bufferInput2.close();
        boolean adadata = false;
        for (int i = 0; i < tampilData.length; i++) {
            if (uname.equals(tampilData[i])) {
                adadata = true;
            }
        }
        if (adadata) {
            System.out.print("--------------------------------------------------------------------------------------------------------------");
            System.out.println("\n| No |\tJenis Hari |\tTanggal Lembur  |\tKeterangan       |\tJam Lembur      |\tTotal Upah");
            System.out.println("--------------------------------------------------------------------------------------------------------------");

            int nomorData = 0;
            for (int i = 0; i < tampilData.length; i++) {
                if (uname.equals(tampilData[i])) {
                    nomorData++;
                    System.out.printf("| %2d ", nomorData);
                    System.out.printf("|\t%9s  ", jenishari[i]);
                    System.out.printf("|\t%-13s   ", tanggal[i]);
                    System.out.printf("|\t%-14s   ", keterangan[i]);
                    System.out.printf("|\t%-13s   ", jamLembur[i]);
                    System.out.printf("|\t%-15s   ", totalUpah[i]);
                    System.out.print("\n");
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Data Anda masih kosong. Silakan tambah data dahulu.");
        }
    }

    public static void cariData() throws IOException {

        String uname = uname2;

        Scanner s = new Scanner(System.in);

        FileReader fileInput4 = new FileReader("database.txt");
        BufferedReader bufferInput3 = new BufferedReader(fileInput4);

        String data = bufferInput3.readLine();
        int baris = 0;
        while (data != null) {
            baris++;
            data = bufferInput3.readLine();
        }
        bufferInput3.close();
        String[] tampilData = new String[baris];
        String[] jenishari = new String[baris];
        String[] tanggal = new String[baris];
        String[] keterangan = new String[baris];
        String[] jamLembur = new String[baris];
        String[] totalUpah = new String[baris];

        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferInput4 = new BufferedReader(fileInput);
        int baris3 = 0;
        String tampilData4 = bufferInput4.readLine();
        while (tampilData4 != null) {
            String[] data2 = tampilData4.split("_");
            tampilData[baris3] = data2[0];
            jenishari[baris3] = data2[1];
            tanggal[baris3] = data2[2];
            keterangan[baris3] = data2[3];
            jamLembur[baris3] = data2[4];
            totalUpah[baris3] = data2[5];
            tampilData4 = bufferInput4.readLine();
            baris3++;
        }
        bufferInput4.close();
        boolean adadata = false;
        boolean adatanggal = false;
        for (int i = 0; i < tampilData.length; i++) {
            if (uname.equals(tampilData[i])) {
                adadata = true;
            }
        }
        if (adadata) {
            System.out.print("Masukkan tanggal untuk mencari data : ");
            String cariString = s.nextLine();

            for (int i = 0; i < tampilData.length; i++) {
                if (uname.equals(tampilData[i])) {
                    if (cariString.equals(tanggal[i])) {
                        adatanggal = true;
                    }
                }
            }
            if (adatanggal) {
                System.out.print("--------------------------------------------------------------------------------------------------------------");
                System.out.println("\n| No |\tJenis Hari |\tTanggal Lembur  |\tKeterangan       |\tJam Lembur      |\tTotal Upah");
                System.out.println("--------------------------------------------------------------------------------------------------------------");

                int nomorData = 0;
                for (int i = 0; i < tampilData.length; i++) {
                    if (uname.equals(tampilData[i])) {
                        if (cariString.equalsIgnoreCase(tanggal[i])) {
                            nomorData++;
                            System.out.printf("| %2d ", nomorData);
                            System.out.printf("|\t%9s  ", jenishari[i]);
                            System.out.printf("|\t%-13s   ", tanggal[i]);
                            System.out.printf("|\t%-14s   ", keterangan[i]);
                            System.out.printf("|\t%-13s   ", jamLembur[i]);
                            System.out.printf("|\t%-15s   ", totalUpah[i]);
                            System.out.print("\n");
                        }
                    }
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("\nTanggal yang Anda masukkan tidak ada di database. \n");
            }
        } else {
            System.out.println("Data Anda masih kosong. Silakan tambah data dahulu.");
        }
    }

    public static void tambahData() throws IOException {

        int gajiPerBulan = gpb;
        int jamLembur = 0;
        int input = 0;
        double gajiPerJam = 0.0;
        double gajiLembur = 0.0;
        String tanggal = "";
        String keterangan = "";
        String uname = uname2;

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Scanner s = new Scanner(System.in);
        System.out.println("1. Lembur hari kerja biasa");
        System.out.println("2. Lembur hari libur (hari terpendek)");
        System.out.println("3. Lembur hari libur (5 hari kerja/minggu)");
        System.out.println("4. Lembur hari libur (6 hari kerja/minggu)");
        System.out.print("\nPilih jenis hari lembur : ");
        input = s.nextInt();

        FileWriter fileOutput = new FileWriter("database.txt", true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        switch (input) {
            case 1:
                // Perhitungan lembur hari kerja biasa
                System.out.print("\nMasukkan tanggal lembur : ");
                tanggal = s.next() + s.nextLine();

                System.out.print("\nMasukkan keterangan : ");
                keterangan = s.next() + s.nextLine();

                do {
                    System.out.println("\n(Jam lembur dalam sehari adalah 1 s.d. 3 jam)");
                    System.out.print("Masukkan jumlah jam lembur : ");
                    jamLembur = s.nextInt();
                } while (jamLembur > 3 || jamLembur <= 0);

                gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                if (jamLembur == 1) {
                    gajiLembur = gajiPerJam * (double) 1.5;
                } else if (jamLembur == 2) {
                    gajiLembur = (gajiPerJam * (double) 1.5) + (gajiPerJam * 2);
                } else {
                    gajiLembur = (gajiPerJam * (double) 1.5) + (2 * (gajiPerJam * 2));
                }
                System.out.println("\nTotal upah lembur Anda adalah " + kursIndonesia.format(gajiLembur));
                System.out.println(" ");
                break;

            case 2:
                // Perhitungan lembur hari libur (hari terpendek)
                System.out.print("\nMasukkan tanggal lembur : ");
                tanggal = s.next() + s.nextLine();

                System.out.print("\nMasukkan keterangan : ");
                keterangan = s.next() + s.nextLine();

                do {
                    System.out.println("\n(Jam lembur pada hari terpendek adalah 5 s.d. 8 jam)");
                    System.out.print("Masukkan jumlah jam lembur : ");
                    jamLembur = s.nextInt();
                } while (jamLembur >= 9 || jamLembur < 5);

                gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                if (jamLembur == 5) {
                    gajiLembur = gajiPerJam * 5 * 2;
                } else if (jamLembur == 6) {
                    gajiLembur = (gajiPerJam * 5 * 2) + gajiPerJam * 3;
                } else {
                    gajiLembur = (gajiPerJam * 5 * 2) + gajiPerJam * 4;
                }
                System.out.println("\nTotal upah lembur Anda adalah " + kursIndonesia.format(gajiLembur));
                System.out.println(" ");
                break;

            case 3:
                // Perhitungan lembur hari libur (5 hari kerja/minggu)
                System.out.print("\nMasukkan tanggal lembur : ");
                tanggal = s.next() + s.nextLine();

                System.out.print("\nMasukkan keterangan : ");
                keterangan = s.next() + s.nextLine();

                do {
                    System.out.println("\n(Jam lembur pada 5 hari kerja/minggu adalah 8 s.d. 11 jam)");
                    System.out.print("Masukkan jumlah jam lembur : ");
                    jamLembur = s.nextInt();
                } while (jamLembur >= 12 || jamLembur < 8);

                gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                if (jamLembur == 8) {
                    gajiLembur = gajiPerJam * 8 * 2;
                } else if (jamLembur == 9) {
                    gajiLembur = (gajiPerJam * 8 * 2) + (gajiPerJam * 3);
                } else {
                    gajiLembur = (gajiPerJam * 8 * 2) + (gajiPerJam * 4);
                }
                System.out.println("\nTotal upah lembur Anda adalah " + kursIndonesia.format(gajiLembur));
                System.out.println(" ");
                break;

            case 4:
                // Perhitungan lembur hari libur (6 hari kerja/minggu)
                System.out.print("\nMasukkan tanggal lembur : ");
                tanggal = s.next() + s.nextLine();

                System.out.print("\nMasukkan keterangan : ");
                keterangan = s.next() + s.nextLine();

                do {
                    System.out.println("\n(Jam lembur pada 6 hari kerja/minggu adalah 7 s.d. 10 jam)");
                    System.out.print("Masukkan jumlah jam lembur : ");
                    jamLembur = s.nextInt();
                } while (jamLembur >= 11 || jamLembur < 7);

                gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                if (jamLembur == 7) {
                    gajiLembur = gajiPerJam * 7 * 2;
                } else if (jamLembur == 8) {
                    gajiLembur = (gajiPerJam * 7 * 2) + (gajiPerJam * 3);
                } else {
                    gajiLembur = (gajiPerJam * 7 * 2) + (gajiPerJam * 4);
                }
                System.out.println("\nTotal upah lembur Anda adalah " + kursIndonesia.format(gajiLembur));
                System.out.println(" ");
                break;
        }
        bufferOutput.close();
        FileReader fileInput3 = new FileReader("database.txt");
        BufferedReader bufferInput2 = new BufferedReader(fileInput3);
        String dataa = bufferInput2.readLine();
        int lines = 0;
        while (dataa != null) {
            lines++;
            dataa = bufferInput2.readLine();
        }
        bufferInput2.close();
        String[] tampilData = new String[lines];
        String[] tanggalSama = new String[lines];

        FileReader fileInput4 = new FileReader("database.txt");
        BufferedReader bufferInput3 = new BufferedReader(fileInput4);

        int baris2 = 0;
        String tampilData2 = bufferInput3.readLine();
        while (tampilData2 != null) {
            String[] data2 = tampilData2.split("_");
            tampilData[baris2] = data2[0];
            tanggalSama[baris2] = data2[2];
            tampilData2 = bufferInput3.readLine();
            baris2++;
        }
        bufferInput3.close();
        boolean cekTanggal = false;
        for (int i = 0; i < tampilData.length; i++) {
            if (uname.equals(tampilData[i])) {
                if (tanggal.equalsIgnoreCase(tanggalSama[i])) {
                    cekTanggal = true;
                }
            }
        }
        if (cekTanggal) {
            System.out.println("\nTanggal tidak boleh sama!\n");
        } else {
            FileReader read = new FileReader("database.txt");
            BufferedReader buffer2 = new BufferedReader(read);
            String data = buffer2.readLine();
            while (data != null) {
                data = buffer2.readLine();
            }
            buffer2.close();
            System.out.println("\nData yang akan Anda masukkan adalah");
            System.out.println("----------------------------------------");
            System.out.println("Username        : " + uname);
            System.out.println("Jenis hari      : " + input);
            System.out.println("Tanggal lembur  : " + tanggal);
            System.out.println("Keterangan      : " + keterangan);
            System.out.println("Jam lembur      : " + jamLembur);
            System.out.println("Total upah      : " + kursIndonesia.format(gajiLembur));
            System.out.println(" ");

            boolean isTambah = YesOrNo("Apakah Anda yakin ingin menambah data tersebut? ");

            FileWriter write = new FileWriter("database.txt", true);
            BufferedWriter buffer = new BufferedWriter(write);

            if (isTambah) {
                buffer.write(uname + "_" + input + "_" + tanggal + "_" + keterangan + "_" + jamLembur + "_" + kursIndonesia.format(gajiLembur));
                buffer.flush();
                buffer.newLine();
                buffer.close();
                System.out.println(">> Data Anda telah tersimpan <<");
            }
        }
    }

    public static void updateData() throws IOException {

        String uname = uname2;
        FileReader fileInput9 = new FileReader("database.txt");
        BufferedReader bufferInput9 = new BufferedReader(fileInput9);

        String data1 = bufferInput9.readLine();
        int baris1 = 0;
        while (data1 != null) {
            baris1++;
            data1 = bufferInput9.readLine();
        }
        bufferInput9.close();
        String[] tampilData9 = new String[baris1];

        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferInput4 = new BufferedReader(fileInput);
        int baris9 = 0;
        String tampilData11 = bufferInput4.readLine();
        while (tampilData11 != null) {
            String[] data2 = tampilData11.split("_");
            tampilData9[baris9] = data2[0];
            tampilData11 = bufferInput4.readLine();
            baris9++;
        }
        bufferInput4.close();
        boolean adadata = false;
        for (int i = 0; i < tampilData9.length; i++) {
            if (uname.equals(tampilData9[i])) {
                adadata = true;
            }
        }
        if (adadata) {
            System.out.println("List Data Laporan");
            tampilkanData();

            Scanner s = new Scanner(System.in);

            System.out.print("\nMasukkan tanggal yang akan diubah : ");
            String update = s.next() + s.nextLine();

            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);

            FileReader reader = new FileReader("database.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String data = buffer.readLine();
            int baris = 0;
            while (data != null) {
                baris++;
                data = buffer.readLine();
            }
            buffer.close();
            String[] tampilData = new String[baris];
            String[] jenisHari = new String[baris];
            String[] tanggal = new String[baris];
            String[] keterangan = new String[baris];
            String[] jamLembur = new String[baris];
            String[] totalUpah = new String[baris];

            FileReader reader2 = new FileReader("database.txt");
            BufferedReader buffer2 = new BufferedReader(reader2);
            int baris2 = 0;
            String tampilData2 = buffer2.readLine();
            while (tampilData2 != null) {
                String[] data2 = tampilData2.split("_");
                tampilData[baris2] = data2[0];
                jenisHari[baris2] = data2[1];
                tanggal[baris2] = data2[2];
                keterangan[baris2] = data2[3];
                jamLembur[baris2] = data2[4];
                totalUpah[baris2] = data2[5];
                tampilData2 = buffer2.readLine();
                baris2++;
            }
            buffer2.close();
            boolean adatanggal = false;
            for (int i = 0; i < tampilData.length; i++) {
                if (uname.equals(tampilData[i])) {
                    if (update.equals(tanggal[i])) {
                        adatanggal = true;
                    }
                }
            }
            if (adatanggal) {
                int baris3 = 0;
                for (int i = 0; i < tampilData.length; i++) {
                    if (uname.equals(tampilData[i])) {
                        if (update.equalsIgnoreCase(tanggal[i])) {
                            System.out.println("\nData yang akan diubah adalah");
                            System.out.println("----------------------------------------");
                            System.out.println("Username        : " + tampilData[i]);
                            System.out.println("Jenis hari      : " + jenisHari[i]);
                            System.out.println("Tanggal lembur  : " + tanggal[i]);
                            System.out.println("Keterangan      : " + keterangan[i]);
                            System.out.println("Jam lembur      : " + jamLembur[i]);
                            System.out.println("Total upah      : " + totalUpah[i]);
                            System.out.println(" ");
                            baris3 = i;
                        }
                    }
                }

                int gajiPerBulan = gpb;
                int jamLembur1 = 0;
                int input = 0;
                double gajiPerJam = 0;
                double gajiLembur = 0;
                String tanggal1 = "";
                String keterangan1 = "";

                System.out.println("1. Lembur hari kerja biasa");
                System.out.println("2. Lembur hari libur (hari terpendek)");
                System.out.println("3. Lembur hari libur (5 hari kerja/minggu)");
                System.out.println("4. Lembur hari libur (6 hari kerja/minggu)");
                System.out.print("\nPilih jenis hari lembur : ");
                input = s.nextInt();
                switch (input) {
                    case 1:
                        // Perhitungan lembur hari kerja biasa
                        System.out.print("\nMasukkan tanggal lembur : ");
                        tanggal1 = s.next() + s.nextLine();

                        System.out.print("\nMasukkan keterangan : ");
                        keterangan1 = s.next() + s.nextLine();

                        do {
                            System.out.println("\n(Jam lembur dalam sehari adalah 1 s.d. 3 jam)");
                            System.out.print("Masukkan jumlah jam lembur : ");
                            jamLembur1 = s.nextInt();
                        } while (jamLembur1 > 3 || jamLembur1 <= 0);

                        gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                        if (jamLembur1 == 1) {
                            gajiLembur = gajiPerJam * (double) 1.5;
                        } else if (jamLembur1 == 2) {
                            gajiLembur = (gajiPerJam * (double) 1.5) + (gajiPerJam * 2);
                        } else {
                            gajiLembur = (gajiPerJam * (double) 1.5) + (2 * (gajiPerJam * 2));
                        }
                        System.out.println("\nTotal upah lembur Anda adalah Rp " + kursIndonesia.format(gajiLembur));
                        System.out.println(" ");
                        break;

                    case 2:
                        // Perhitungan lembur hari libur (hari terpendek)
                        System.out.print("\nMasukkan tanggal lembur : ");
                        tanggal1 = s.next() + s.nextLine();

                        System.out.print("\nMasukkan keterangan : ");
                        keterangan1 = s.next() + s.nextLine();

                        do {
                            System.out.println("\n(Jam lembur pada hari terpendek adalah 5 s.d. 8 jam)");
                            System.out.print("Masukkan jumlah jam lembur : ");
                            jamLembur1 = s.nextInt();
                        } while (jamLembur1 >= 9 || jamLembur1 < 5);

                        gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                        if (jamLembur1 == 5) {
                            gajiLembur = gajiPerJam * 5 * 2;
                        } else if (jamLembur1 == 6) {
                            gajiLembur = (gajiPerJam * 5 * 2) + gajiPerJam * 3;
                        } else {
                            gajiLembur = (gajiPerJam * 5 * 2) + gajiPerJam * 4;
                        }
                        System.out.println("\nTotal upah lembur Anda adalah Rp " + kursIndonesia.format(gajiLembur));
                        System.out.println(" ");
                        break;

                    case 3:
                        // Perhitungan lembur hari libur (5 hari kerja/minggu)
                        System.out.print("\nMasukkan tanggal lembur : ");
                        tanggal1 = s.next() + s.nextLine();

                        System.out.print("\nMasukkan keterangan : ");
                        keterangan1 = s.next() + s.nextLine();

                        do {
                            System.out.println("\n(Jam lembur pada 5 hari kerja/minggu adalah 8 s.d. 11 jam)");
                            System.out.print("Masukkan jumlah jam lembur : ");
                            jamLembur1 = s.nextInt();
                        } while (jamLembur1 >= 12 || jamLembur1 < 8);

                        gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                        if (jamLembur1 == 8) {
                            gajiLembur = gajiPerJam * 8 * 2;
                        } else if (jamLembur1 == 9) {
                            gajiLembur = (gajiPerJam * 8 * 2) + (gajiPerJam * 3);
                        } else {
                            gajiLembur = (gajiPerJam * 8 * 2) + (gajiPerJam * 4);
                        }
                        System.out.println("\nTotal upah lembur Anda adalah Rp " + kursIndonesia.format(gajiLembur));
                        System.out.println(" ");
                        break;

                    case 4:
                        // Perhitungan lembur hari libur (6 hari kerja/minggu)
                        System.out.print("\nMasukkan tanggal lembur : ");
                        tanggal1 = s.next() + s.nextLine();

                        System.out.print("\nMasukkan keterangan : ");
                        keterangan1 = s.next() + s.nextLine();

                        do {
                            System.out.println("\n(Jam lembur pada 6 hari kerja/minggu adalah 7 s.d. 10 jam)");
                            System.out.print("Masukkan jumlah jam lembur : ");
                            jamLembur1 = s.nextInt();
                        } while (jamLembur1 >= 11 || jamLembur1 < 7);

                        gajiPerJam = (double) 1 / 173 * gajiPerBulan;

                        if (jamLembur1 == 7) {
                            gajiLembur = gajiPerJam * 7 * 2;
                        } else if (jamLembur1 == 8) {
                            gajiLembur = (gajiPerJam * 7 * 2) + (gajiPerJam * 3);
                        } else {
                            gajiLembur = (gajiPerJam * 7 * 2) + (gajiPerJam * 4);
                        }
                        System.out.println("\nTotal upah lembur Anda adalah Rp " + kursIndonesia.format(gajiLembur));
                        System.out.println(" ");
                        break;
                }
                FileReader fileInput3 = new FileReader("database.txt");
                BufferedReader bufferInput2 = new BufferedReader(fileInput3);
                String dataa = bufferInput2.readLine();
                int lines = 0;
                while (dataa != null) {
                    lines++;
                    dataa = bufferInput2.readLine();
                }
                bufferInput2.close();

                String[] tampilData3 = new String[lines];
                String[] tanggalSama = new String[lines];

                FileReader fileInput4 = new FileReader("database.txt");
                BufferedReader bufferInput3 = new BufferedReader(fileInput4);
                int baris4 = 0;
                String tampilData4 = bufferInput3.readLine();
                while (tampilData4 != null) {
                    String[] data2 = tampilData4.split("_");
                    tampilData3[baris4] = data2[0];
                    tanggalSama[baris4] = data2[2];
                    tampilData4 = bufferInput3.readLine();
                    baris4++;
                }
                bufferInput3.close();
                int pastanggalsama = -1;
                boolean isUpdate = false;
                boolean cekTanggal = false;
                for (int i = 0; i < tampilData3.length; i++) {
                    if (uname.equals(tampilData[i])) {
                        if (tanggal1.equalsIgnoreCase(tanggalSama[i])) {
                            pastanggalsama = i;
                            cekTanggal = true;
                        }
                    }
                }
                if (cekTanggal) {
                    if (pastanggalsama != baris3 || pastanggalsama == -1) {
                        System.out.println("\nTanggal tidak boleh sama!");
                    } else {
                        System.out.println("\nData yang akan Anda masukkan adalah");
                        System.out.println("----------------------------------------");
                        System.out.println("Username        : " + uname);
                        System.out.println("Jenis hari      : " + input);
                        System.out.println("Tanggal lembur  : " + tanggal1);
                        System.out.println("Keterangan      : " + keterangan1);
                        System.out.println("Jam lembur      : " + jamLembur1);
                        System.out.println("Total upah      : " + kursIndonesia.format(gajiLembur));
                        System.out.println(" ");

                        isUpdate = YesOrNo("Apakah anda ingin mengupdate data tersebut? ");
                    }
                } else {
                    System.out.println("\nData yang akan Anda masukkan adalah");
                    System.out.println("----------------------------------------");
                    System.out.println("Username        : " + uname);
                    System.out.println("Jenis hari      : " + input);
                    System.out.println("Tanggal lembur  : " + tanggal1);
                    System.out.println("Keterangan      : " + keterangan1);
                    System.out.println("Jam lembur      : " + jamLembur1);
                    System.out.println("Total upah      : " + kursIndonesia.format(gajiLembur));
                    System.out.println(" ");

                    isUpdate = YesOrNo("Apakah anda ingin mengupdate data tersebut? ");
                }

                if (isUpdate) {
                    String record;
                    File db = new File("database.txt");
                    File tempDB = new File("tempDB.txt");

                    BufferedReader br = new BufferedReader(new FileReader(db));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

                    while ((record = br.readLine()) != null) {
                        if (record.contains(update) && record.contains(uname)) {
                            bw.write(uname + "_" + input + "_" + tanggal1 + "_" + keterangan1 + "_" + jamLembur1 + "_" + kursIndonesia.format(gajiLembur));
                        } else {
                            bw.write(record);
                        }
                        bw.flush();
                        bw.newLine();
                    }
                    bw.close();
                    br.close();
                    db.delete();
                    tempDB.renameTo(db);
                    System.out.println(">> Data Anda telah terubah <<");
                }
            } else {
                System.out.println("\nTanggal yang Anda masukkan tidak ada di database. \n");
            }
        } else {
            System.out.println("Data Anda masih kosong. Silakan tambah data dahulu.");
        }
    }

    public static void deleteData() throws IOException {
        String uname = uname2;
        FileReader fileInput9 = new FileReader("database.txt");
        BufferedReader bufferInput9 = new BufferedReader(fileInput9);

        String data1 = bufferInput9.readLine();
        int baris1 = 0;
        while (data1 != null) {
            baris1++;
            data1 = bufferInput9.readLine();
        }
        bufferInput9.close();
        String[] tampilData9 = new String[baris1];
        String[] tanggal = new String[baris1];

        FileReader fileInput = new FileReader("database.txt");
        BufferedReader bufferInput4 = new BufferedReader(fileInput);
        int baris9 = 0;
        String tampilData11 = bufferInput4.readLine();
        while (tampilData11 != null) {
            String[] data2 = tampilData11.split("_");
            tampilData9[baris9] = data2[0];
            tanggal[baris9] = data2[2];
            tampilData11 = bufferInput4.readLine();
            baris9++;
        }
        bufferInput4.close();
        boolean adadata = false;
        for (int i = 0; i < tampilData9.length; i++) {
            if (uname.equals(tampilData9[i])) {
                adadata = true;
            }
        }
        if (adadata) {
            String record;

            Scanner s = new Scanner(System.in);

            File tempDB = new File("tempDB.txt");
            File db = new File("database.txt");

            BufferedReader br = new BufferedReader(new FileReader(db));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

            System.out.println("List Data Laporan");
            tampilkanData();

            System.out.print("\nMasukkan tanggal yang akan dihapus : ");
            String InputDelete = s.nextLine();

            boolean adatanggal = false;
            for (int i = 0; i < tampilData9.length; i++) {
                if (uname.equals(tampilData9[i])) {
                    if (InputDelete.equals(tanggal[i])) {
                        adatanggal = true;
                    }
                }
            }
            if (adatanggal) {
                boolean isDelete = YesOrNo("Apakah Anda yakin ingin menghapus data tersebut? ");
                if (isDelete) {
                    while ((record = br.readLine()) != null) {

                        if (record.contains(InputDelete) && record.contains(uname)) {
                            continue;
                        }
                        bw.write(record);
                        bw.flush();
                        bw.newLine();
                    }
                    br.close();
                    bw.close();
                    db.delete();
                    tempDB.renameTo(db);
                    System.out.println(">> Data Anda telah terhapus <<");
                }
            } else {
                System.out.println("\nTanggal yang Anda masukkan tidak ada di database.\n");
                bw.close();
                br.close();
                tempDB.delete();
            }
        } else {
            System.out.println("Data Anda masih kosong. Silakan tambah data dahulu.");
        }
    }

    public static boolean YesOrNo(String message) {

        Scanner s = new Scanner(System.in);
        System.out.print("\n" + message + " (ketik y/n) : ");
        String pilihanUser = s.next();

        while (!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.out.println("Pilihan anda bukan y atau n.");
            System.out.print("\n" + message + " (ketik y/n) : ");
            pilihanUser = s.next();
        }
        return pilihanUser.equalsIgnoreCase("y");
    }
}
