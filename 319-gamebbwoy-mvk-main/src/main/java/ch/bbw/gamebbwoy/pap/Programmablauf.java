package ch.bbw.gamebbwoy.pap;

public class Programmablauf {

    public static void main(String[] args) {

        // Java Dateien erkennen:
        boolean isJava = istJavaDatei("Bestellliste.txt");
        System.out.println(isJava);

        isJava = istJavaDatei("Programmablaufplan.java");
        System.out.println(isJava);

        isJava = istJavaDatei("README");
        System.out.println(isJava);

        isJava = istJavaDatei("Java ist auch eine schöne Insel.");
        System.out.println(isJava);


        // Gerade Zahlen aus einer Reihe zählen:
        System.out.println(zaehleGerade(5, 9));

        System.out.println(zaehleGerade(-93, -78));

        System.out.println(zaehleGerade(20, 13));


        // break und continue verstehen
        itIsAboutTime(2);

        itIsAboutTime(50);


        // nested Loops verstehen
        nestedLoop(2, 3);

        nestedLoop(10, 1);


        // Rekursion verstehen
        callFromInsideItself(1, 10); // slow hat einen Vorsprung

        callFromInsideItself(12, 10); // fast ist bereits vor slow


    }

    public static boolean istJavaDatei(String dateiname) {
        int punktPosition = dateiname.lastIndexOf(".");

        if (punktPosition == -1) {
            return false;
        }

        String endung = dateiname.substring(punktPosition + 1);
        return endung.equals("java");
    }

    public static int zaehleGerade(int start, int ende) {
        int zaehler = 0;

        for (int i = start; i <= ende; i++) {
            if (i % 2 == 0) {
                zaehler++;
            }
        }

        return zaehler;
    }


    public static void itIsAboutTime(int alteZahl) {
        int neueZahl = 0;
        while (true) {
            if (neueZahl < 0) {
                System.out.println("Time for a break");
                break;
            }
            if (alteZahl % 2 == 0) {
                alteZahl++;
                neueZahl++;
                continue;
            } else if (alteZahl % 2 == 1) {
                alteZahl++;
                neueZahl-=2;
                System.out.println("Time to continue");
                continue;
            }
            System.out.println("And another one.");
        }
    }

    // Verschachtelte Schleifen
    public static void nestedLoop(int width, int height) {
        System.out.println("Wir brauchen 2 Schleifen.");
        System.out.println("Die 1. Schleife iteriert ueber height mit Schleifenzaehler i.");
        System.out.println("Die 2. Schleife iteriert ueber width mit Schleifenzaehler j.");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println("aktuelles j: " + j);
            }
            System.out.println("aktuelles i: " + i);
        }
    }

    // Rekursion
    public static void callFromInsideItself(int fast, int slow) {
        System.out.println("Fast ist auf Position: " + fast);
        System.out.println("Slow ist auf Position: " + slow);
        if(fast > slow){
            System.out.println("fast hast slow ueberhohlt.");
            return; // das return ist wichtig, sonst haben wir eine Endlosschleife
        }
        callFromInsideItself(fast+=2, slow++);
        System.out.println("Das hier wird erst am Schluss ausgegeben, sobald fast slow ueberhohlt hat. " +
                "Dafuer kommt dieser Satz so oft vor, wie wir callFromInsideItself aufgerufen haben." +
                "Nur fuer den letzten Durchlauf nicht, da das return vorher kommt.");
    }
}

