package bds.devweb.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import bds.devweb.model.DateCalendrier;

public class CalendrierDao {
	
	public List<DateCalendrier> calendrier(int annee1, int annee2){
		
		List<DateCalendrier> listecalendrier = new ArrayList<DateCalendrier>();
		GregorianCalendar date = new GregorianCalendar();
		
		for(int a = annee1; a<=annee2; a++ ){
			if(a == annee1){
				for(int m = 8; m<= 11; m++){
					date.set(a, m, 1);
					int maxDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
					for(int j = 1; j <= maxDay; j++){
						date.set(a, m, j);
						int numjour = date.getTime().getDay();
						DateCalendrier day = new DateCalendrier(a, m, j, numjour);
						listecalendrier.add(day);
					}
					
				}
			}
			else if(a == annee2){
				for(int m = 0; m<= 5; m++){
					date.set(a, m, 1);
					int maxDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
					for(int j = 1; j <= maxDay; j++){
						date.set(a, m, j);
						int numjour = date.getTime().getDay();
						DateCalendrier day = new DateCalendrier(a, m, j, numjour);
						listecalendrier.add(day);
					}	
				}
			}
		}
		return listecalendrier;	
	}
	

}
