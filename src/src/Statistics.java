package src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Statistics implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int yarida=0;
	private int basarisizOyunSayisi=0;
	private int satirSayisi=0;
	private int sure=0;
	private int basariliOyunSayisi=0;
	
	public Statistics(int yarida, int basari, int satirSayisi, int sure,boolean set) {
		System.out.println("\n------------\n basari "+basari+"\n satir sayisi  "+satirSayisi+"\n sure "+sure+" \n");
		deSerialize();
		System.out.println("\nyarida "+this.yarida+"\n basarisiz oyun sayisi "+this.basarisizOyunSayisi+"\n satir sayisi  "+this.satirSayisi+"\n sure "+this.sure+" \n");
		if(set) {
			setStatistics(yarida, basari, satirSayisi, sure);
		}
		System.out.println("yarida "+this.yarida+"\n basarisiz oyun sayisi "+this.basarisizOyunSayisi+"\n satir sayisi  "+this.satirSayisi+"\n sure "+this.sure+"\n  basarili Oyun sayisi"+this.basariliOyunSayisi+ " \n");
	}
	public void setStatistics(int a,int b,int c,int d) {
		if(a==1) {
			yarida++;
			serialize();
		}
		else if(b==0) {
			basarisizOyunSayisi++;
			serialize();
		}
		else{
			newSatirSayisi(c);
			newSure(d);
			basariliOyunSayisi++;
			serialize();
		}
	}
	private void serialize() {
		try {
			int []values = new int[6];
			values[0]=yarida;
			values[1]=basarisizOyunSayisi;
			values[2]=basariliOyunSayisi;
			values[3]=satirSayisi;
			values[4]=sure;
			
			String fileName = "statistics.dat";
			ObjectOutputStream wrtr = new ObjectOutputStream( new FileOutputStream( fileName )  );
			wrtr.writeObject(values);
			
			wrtr.close();
			System.out.println("The information you have entered has "
					+ "been successfully saved in file " + fileName);
		} 
		catch( IOException rr ) {
			System.out.println("STATÝSTÝCS :An exception has occured during writing to file.");
			//rr.printStackTrace();
		} 
	}
	private void deSerialize() {
		
		try {
			String fileName = "statistics.dat";
			ObjectInputStream reader = new ObjectInputStream( new FileInputStream( fileName ) );
			int []values =  (int[]) reader.readObject();
			yarida=values[0];
			basarisizOyunSayisi=values[1];
			basariliOyunSayisi=values[2];
			satirSayisi=values[3];
			sure=values[4];

			reader.close();
		} 
		catch( IOException e ) {
			System.out.println("STATÝSTÝCS :An exception has occured during file reading.");
			//e.printStackTrace();
		} 
		catch( ClassNotFoundException e ) {
			System.out.println("STATÝSTÝCS :An exception has occured while processing read records.");
			//e.printStackTrace();
		}
	}
	private void newSure(int deger) {
		sure = ((this.sure*this.basariliOyunSayisi)+deger)/(basariliOyunSayisi+1);
	}
	private void newSatirSayisi(int deger) {
		satirSayisi = ((satirSayisi*basariliOyunSayisi)+deger)/(basariliOyunSayisi+1);
	}
	public int getYarida() {
		return yarida;
	}
	public void setYarida(int yarida) {
		this.yarida = yarida;
	}
	public int getBasarisizlik() {
		return basarisizOyunSayisi;
	}
	public void setBasarisizlik(int basarisilik) {
		this.basarisizOyunSayisi = basarisilik;
	}
	public int getSatirSayisi() {
		return satirSayisi;
	}
	public void setSatirSayisi(int satirSayisi) {
		this.satirSayisi = satirSayisi;
	}
	public int getSure() {
		return sure;
	}
	public void setSure(int sure) {
		this.sure = sure;
	}
	public int getbasariliOyunSayisi() {
		return basariliOyunSayisi;
	}
	public void setbasariliOyunSayisi(int oyunSayisi) {
		this.basariliOyunSayisi = oyunSayisi;
	}
	


}
