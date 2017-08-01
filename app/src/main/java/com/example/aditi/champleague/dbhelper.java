package com.example.aditi.champleague;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME="mydb.db";
	public static final String TABLE_NAME="userinfo";
	//public static final String TABLE_2="userinfo2";
	public static final String COLI1="player1";
	public static final String COLI2="player2";
	public static final String COLI3="overs";
	public static final String COLI4="score";
	//public static final String COLI5="gender";
	//public static final String COLI6="namee";
	//public static final String COLI7="emaill";
	//public static final String COLI8="phonee";
	//public static final String COLI9="name1";
	//public static final String COLI10="email1";
	//public static final String COLI11="phone1";
	
	
	
	
	public dbhelper(Context context)

	{
		super(context,DATABASE_NAME,null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		
		// TODO Auto-generated method stub
		db.execSQL("create table userinfo"+"(player1 text not null,player2 text not null,overs text not null,score text null) ");
	//	db.execSQL("create table userinfo2"+"(namee text not null,emaill text primary key,phonee integer not null,name1 text not null,email1 text not null,phone1 integer not null) ");
		
		//db.execSQL("create table moneyinfo"+"(sno integer not null,date integer not null,expenditure text not null,type text not null,afterexpenditure text not null) ");
	}
		public boolean insertcontact(String p1,String p2,String o,String pp)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put("player1",p1);
		cv.put("player2",p2);
		cv.put("overs",o);
		cv.put("score",pp);
		//cv.put("gender", gender);
		db.insert("userinfo", null, cv);
		return true;
	}
	/*	public boolean insertcontact1(String namee,String emaill,String phonee,String name1,String email1,String phone1)
		{
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put("namee",namee);
			cv.put("emaill",emaill);
			cv.put("phonee",phonee);
			cv.put("name1",name1);
			cv.put("phone1",phone1);
			cv.put("email1",email1);
			db.insert("userinfo2", null, cv);
			return true;
		} */
	/*	public boolean updatecontact(String name,String pass,String phone,String email)
		{
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put("name",name);
			cv.put("pass",pass);
			cv.put("phone",phone);
			cv.put("email",email);
			db.update("userinfo", cv, " email=?", new String[]{email});
			return true;
		}
		public boolean updatecontact1(String namee,String emaill,String phonee,String name1,String email1,String phone1)
		{
			SQLiteDatabase db=this.getWritableDatabase();
			ContentValues cv=new ContentValues();
			cv.put("namee",namee);
			cv.put("emaill",emaill);
			cv.put("phonee",phonee);
			cv.put("name1",name1);
			cv.put("phone1",phone1);
			cv.put("email1",email1);
			db.update("userinfo2", cv, " emaill=?", new String[]{emaill});
			return true;
		}

		*/


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// TODO Auto-generated method stub
			
		}
		
	
	

	
	

}

