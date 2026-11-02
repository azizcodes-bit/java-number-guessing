import java.util.Scanner;
import java.util.Random;
public class ZahlenRaten {
 public static void main(String[] args){
  Scanner scanner=new Scanner(System.in);
  Random rd=new Random();
  int auswahl;
  do{
   System.out.println("Zahlenraten: 1 - Ein Spieler, 2 - Zwei Spieler, 3 - Beenden");
   auswahl=liesZahl(scanner,1,3);
   switch(auswahl){
    case 1:{
     System.out.println("Geben Sie den Namen des Spielers ein :");
     Spieler spieler=new Spieler(scanner.nextLine());
     Spieler computer=new Spieler("Computer");
     int runde=1;
     do{
      int punkteSpieler=0,punkteComputer=0;
      int zahl1=rd.nextInt(101),zahl2=rd.nextInt(101),zahl3=rd.nextInt(101),zahl4=rd.nextInt(5);
      int min=Minimum(zahl1,zahl2),max=Maximum(zahl1,zahl2);
      System.out.println("Runde "+runde+" :");
      System.out.println("Die Zahlen sind "+min+" und "+max);
      if(runde%2==0){
       System.out.println("Computer ist an der Reihe:");
       System.out.println("Die Auswahl des Computers ist "+zahl4);
      }
      System.out.println(spieler.getName()+" ist an der Reihe:");
      System.out.println("Wählen Sie aus. Die nächste Zahl ist ...");
      zeigeOptionen(min,max);
      int wahl=liesZahl(scanner,0,4);
      if(runde%2==1){
       System.out.println("Computer ist an der Reihe:");
       System.out.println("Die Auswahl des Computers ist "+zahl4);
      }
      System.out.println("Die dritte zahl leutet "+zahl3);
      System.out.println("Der aktuelle Punktestand lautet: ");
      punkteSpieler=punkte(wahl,zahl3,min,max);
      punkteComputer=punkte(zahl4,zahl3,min,max);
      if(punkteSpieler>punkteComputer)spieler.addPunkte(punkteSpieler);
      else if(punkteComputer>punkteSpieler)computer.addPunkte(punkteComputer);
      System.out.println(spieler.getName()+" : "+spieler.getPunkte()+" Punkte ");
      System.out.println(computer.getName()+" : "+computer.getPunkte()+" Punkte ");
      runde++;
     }while(spieler.getPunkte()<5&&computer.getPunkte()<5);
     System.out.println((spieler.getPunkte()>=5?spieler.getName():computer.getName())+" hat gewonnen!");
     break;
    }
    case 2:{
     System.out.println("Geben Sie den Namen des ersten Spielers ein:");
     Spieler spieler1=new Spieler(scanner.nextLine());
     System.out.println("Geben Sie den Namen des zweiten Spielers ein:");
     Spieler spieler2=new Spieler(scanner.nextLine());
     int runde=1;
     do{
      int zahl1=rd.nextInt(101),zahl2=rd.nextInt(101);
      int min=Minimum(zahl1,zahl2),max=Maximum(zahl1,zahl2);
      System.out.println("\nRunde "+runde+":");
      System.out.println("Die Zahlen sind "+min+" und "+max);
      System.out.println("Waehlen Sie aus. Die naechste Zahl ist ...");
      zeigeOptionen(min,max);
      int wahl1,wahl2;
      if(runde%2==1){
       System.out.println(spieler1.getName()+" ist an der Reihe:");
       wahl1=liesZahl(scanner,0,4);
       System.out.println(spieler2.getName()+" ist an der Reihe:");
       wahl2=liesZahl(scanner,0,4);
      }else{
       System.out.println(spieler2.getName()+" ist an der Reihe:");
       wahl2=liesZahl(scanner,0,4);
       System.out.println(spieler1.getName()+" ist an der Reihe:");
       wahl1=liesZahl(scanner,0,4);
      }
      int zahl3=rd.nextInt(101);
      System.out.println("Die dritte Zahl lautet "+zahl3);
      int p1=punkte(wahl1,zahl3,min,max),p2=punkte(wahl2,zahl3,min,max);
      if(p1>p2)spieler1.addPunkte(p1);
      else if(p2>p1)spieler2.addPunkte(p2);
      System.out.println("Der aktuelle Punktestand lautet:");
      System.out.println(spieler1.getName()+": "+spieler1.getPunkte()+" Punkte");
      System.out.println(spieler2.getName()+": "+spieler2.getPunkte()+" Punkte");
      runde++;
     }while(spieler1.getPunkte()<5&&spieler2.getPunkte()<5);
     System.out.println((spieler1.getPunkte()>=5?spieler1.getName():spieler2.getName())+" hat gewonnen!");
     break;
    }
   }
  }while(auswahl!=3);
  scanner.close();
 }
 private static void zeigeOptionen(int min,int max){
  System.out.println("0: kleiner als "+min);
  System.out.println("1: genau "+min);
  System.out.println("2: zwischen "+min+" und "+max);
  System.out.println("3: genau "+max);
  System.out.println("4: größer als "+max);
 }
 private static int punkte(int wahl,int zahl,int min,int max){
  if((wahl==0&&zahl<min)||(wahl==2&&zahl>min&&zahl<max)||(wahl==4&&zahl>max))return 1;
  if((wahl==1&&zahl==min)||(wahl==3&&zahl==max))return 3;
  return 0;
 }
 private static int liesZahl(Scanner scanner,int min,int max){
  while(true){
   try{
    int zahl=Integer.parseInt(scanner.nextLine().trim());
    if(zahl>=min&&zahl<=max)return zahl;
   }catch(NumberFormatException e){}
   System.out.println("Bitte eine Zahl von "+min+" bis "+max+" eingeben.");
  }
 }
 public static int Maximum(int n,int m){return m>n?m:n;}
 public static int Minimum(int n,int m){return m<n?m:n;}
}