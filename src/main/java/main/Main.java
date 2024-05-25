package main;

import java.io.*;

public class Main {
    public static void main(String[] args) {
//        demoByteArrayInputStream();
//        demoFileInputStream();
//        demoSequenceInputStream();
//        demoBufferedInputStream();
//        demoDataInputOutputStream();
        demoPipedInputOutputStream();
    }

    private static void demoByteArrayInputStream() {
        /*
        Source: ByteArrayInputStream from byte[]
        Source: FileInputStream from File
         */
        byte[] bb = {1,2,3,4,5,6};
        System.out.println("### ByteArrayInputStream");
        try (InputStream ais = new ByteArrayInputStream(bb)) {
            int i;
            while ((i = ais.read()) != -1) {
                System.out.print(i + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void demoFileInputStream() {
        System.out.println("### FileInputStream");
        try (InputStream fis = new FileInputStream("C:\\Users\\user\\input.txt")) {
            int i;
            while ((i = fis.read()) != -1) {
                System.out.print((char) i + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demoSequenceInputStream() {
        System.out.println("### SequenceInputStream");
        byte[] bb = {65,66,67,70};
        try (InputStream sis = new SequenceInputStream(
                new ByteArrayInputStream(bb),
                new FileInputStream("C:\\Users\\user\\input.txt"))) {
            int i;
            while ((i = sis.read()) != -1) {
                System.out.print((char) i + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void demoBufferedInputStream() {
        System.out.println("### BufferedInputStream");
        try (InputStream bis = new BufferedInputStream(
                     new FileInputStream("C:\\Users\\user\\input.txt"))) {
            int i;
            while ((i = bis.read()) != -1) {
                System.out.print((char) i + " ");
            }
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void demoDataInputOutputStream() {
        System.out.println("### DataInputOutputStream");
        long n = 1_000_000_000L;
        double d = 13.4133424;
        boolean b1 = false;
        boolean b2 = true;
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(
                        "C:\\Users\\user\\out.dat")))) {
            dos.writeBoolean(b1);
            dos.writeDouble(d);
            dos.writeLong(n);
            dos.writeBoolean(b2);
        } catch (Exception e) {
           e.printStackTrace();
        }
        System.out.println("Done Writing.");
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(
                        "C:\\Users\\user\\out.dat")))) {
            System.out.println("Boolean: " + dis.readBoolean());
            System.out.println("Double: " + dis.readDouble());
            System.out.println("Long: " + dis.readLong());
            System.out.println("Boolean: " + dis.readBoolean());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demoPipedInputOutputStream() {
        System.out.println("### PipedInputOutputStream");
        try (PipedOutputStream pos = new PipedOutputStream();
             PipedInputStream pis = new PipedInputStream();) {
            pos.connect(pis);
            pos.write(67);
            pos.write(70);
            pos.write(75);
            pos.close();
            int i;
            while ((i = pis.read()) != -1) {
                System.out.print((char) i + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
