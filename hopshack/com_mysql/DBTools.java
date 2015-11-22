package hopshack.com_mysql;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTools extends SQLiteOpenHelper{
	//import classes
	BeerTypes beerTypes = new BeerTypes();
	
	public DBTools (Context applicationcontext){
		super(applicationcontext, "beers.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		//create table
		
		String query = "CREATE TABLE shack (id INTEGER PRIMARY KEY, company TEXT, name TEXT, "+
		"city TEXT, state Text, type INTEGER, abv INTEGER, rating INTEGER, raters INTEGER, notes TEXT)";
		database.execSQL(query);
		
		/*
		String insert1 ="INSERT INTO beers VALUES(1,'Sierra Nevada Torpedo','Chico, CA','Double IPA')";
		String insert2 ="INSERT INTO beers VALUES(2,'Sierra Nevada Porter','Chico, CA','Porter')";
		String insert3 ="INSERT INTO beers VALUES(3,'Sierra Nevada Kellerweis','Chico, CA','Hefeweizen')";
		String insert4 ="INSERT INTO beers VALUES(4,'Sierra Nevada Stout','Chico, CA','Stout')";
		String insert5 ="INSERT INTO beers VALUES(5,'Sierra Nevada Old Chico','Chico, CA','Wheat')";
		String insert6 ="INSERT INTO beers VALUES(6,'Sierra Nevada Ruthless','Chico, CA','Rye')";
		String insert7 ="INSERT INTO beers VALUES(7,'Sierra Nevada Summerfest','Chico, CA','Lager')";
		
		database.execSQL(query);
		database.execSQL(insert1);
		database.execSQL(insert2);
		database.execSQL(insert3);
		database.execSQL(insert4);
		database.execSQL(insert5);
		database.execSQL(insert6);
		database.execSQL(insert7);
		
		//??database.insert("beers", null, values);
		*/
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		//drop table if exists
		String query = "DROP TABLE IF EXISTS shack";
		database.execSQL(query);
		//create new table
		onCreate(database);
	}
	//NewBeer
	public void insertBeer(HashMap<String, String> queryValues){	

			SQLiteDatabase database = this.getWritableDatabase();
		
			
			ContentValues values = new ContentValues();
			values.put("id", queryValues.get("id"));
			values.put("company", queryValues.get("company"));
			values.put("name", queryValues.get("name"));
			values.put("city", queryValues.get("city"));
			values.put("state", queryValues.get("state"));
			values.put("type", queryValues.get("type"));
			values.put("abv", queryValues.get("abv"));
			values.put("rating", queryValues.get("rating"));
			values.put("raters", queryValues.get("raters"));
			values.put("notes", queryValues.get("notes"));
			
			database.insert("shack", null, values);
			database.close();
			
			Log.e("sqlite", "beer added to shack");			
		
	}
	 //---deletes a beer with id = ID---
	public void deleteBeer(String ID) 
	{
		SQLiteDatabase database = this.getWritableDatabase();
	    database.delete("shack", "id =" + ID, null);
	}
	
	/*
	//EditBeer
	//notice int here
	public int updateBeer(HashMap<String, String> queryValues){
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name", queryValues.get("name"));
		values.put("location", queryValues.get("location"));
		values.put("type", queryValues.get("type"));
		
		return database.update("beers", values, 
				"beerId" + " = ?", new String[] {queryValues.get("beerId")});
		
	}
	*/
	
	//array of beers from db
	public ArrayList<HashMap<String, String>> getAllBeer(){
		
		//necessary line of code here...
		ArrayList<HashMap<String, String>> beerArrayList = new ArrayList<HashMap<String, String>>();
		//SQL code
		String selectQuery = "SELECT * FROM shack";
		
		SQLiteDatabase database = this.getWritableDatabase();
		//create cursor
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> beerMap = new HashMap<String, String>();
				//get image id
				int nType = Integer.parseInt(cursor.getString(5));
                int nImage = beerTypes.idToImage(nType);
                String image = String.valueOf(nImage);
                
				//hashmap from cursor moving through table
				beerMap.put("id", cursor.getString(0));
				beerMap.put("company", cursor.getString(1));
				beerMap.put("name", cursor.getString(2));
				beerMap.put("city", cursor.getString(3));
				beerMap.put("state", cursor.getString(4));
				beerMap.put("type", cursor.getString(5));
				beerMap.put("image", image);
				//add map to array!
				
				beerArrayList.add(beerMap);
			} while(cursor.moveToNext());
		}
		return beerArrayList;
			
		}
	
	//now just to get one beer's info (same process)
	public HashMap<String, String> getBeerInfo(String id){
				
			HashMap<String, String> beerMap = new HashMap<String, String>();
			SQLiteDatabase database = this.getReadableDatabase();
			
			//grabbing info of desired beer from sql
			String selectQuery = "SELECT * FROM shack WHERE id='" + id + "'";
			
			//create cursor
			Cursor cursor = database.rawQuery(selectQuery, null);
			
			if(cursor.moveToFirst()){
				do{
					
					//cursor moving through table
					beerMap.put("id", cursor.getString(0));
					beerMap.put("company", cursor.getString(1));
					beerMap.put("name", cursor.getString(2));
					beerMap.put("city", cursor.getString(3));
					beerMap.put("state", cursor.getString(4));
					beerMap.put("type", cursor.getString(5));
					beerMap.put("abv", cursor.getString(6));
					beerMap.put("rating", cursor.getString(7));
					beerMap.put("raters", cursor.getString(8));
					beerMap.put("notes", cursor.getString(9));
					
					
				} while(cursor.moveToNext());
			}
			return beerMap;	
	}
	//Random Beer
	public HashMap<String, String> getRandomBeer(){
				
			HashMap<String, String> beerMap = new HashMap<String, String>();
			SQLiteDatabase database = this.getReadableDatabase();
			
			//grabbing info of desired beer from sql
			String selectQuery = "SELECT state, type FROM shack ORDER BY RANDOM() LIMIT 1";
			
			//create cursor
			Cursor cursor = database.rawQuery(selectQuery, null);
			
			if(cursor.moveToFirst()){
				do{
					
					//cursor moving through table
					beerMap.put("state", cursor.getString(0));
					beerMap.put("type", cursor.getString(1));
					
				} while(cursor.moveToNext());
			}
			Log.e("sqlite", "random beer taken from shack");	
			return beerMap;	
	}
	
}
