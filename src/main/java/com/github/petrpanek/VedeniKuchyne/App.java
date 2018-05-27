package com.github.petrpanek.VedeniKuchyne;

import com.github.petrpanek.VedeniKuchyne.dao.PotravinaDAO;
import com.github.petrpanek.VedeniKuchyne.logika.Potravina;


public class App 
{
	public static void main(String[] args) {
		
		for (Potravina food : PotravinaDAO.getAllPotraviny()) {
			System.out.println(food);
		}
		
        
        
			
		
    }
}
