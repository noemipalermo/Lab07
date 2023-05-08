package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<Nerc> nercList;
	private List<PowerOutages> allPwrOutgNerc;
	private List<PowerOutages> best;
	
	private int numPersoneCoinvolte;
	
	public Model() {
		podao = new PowerOutageDAO();
		nercList= podao.getNercList();
	}
	
	public List<Nerc> getNercList() {
		return this.nercList;
	}
	

	public List<PowerOutages> sottoinsiemeBlackout(int mYears, int mHours, Nerc nerc) {
		
		List<PowerOutages> parziale = new ArrayList<>();
		best= new ArrayList<>();
		
		numPersoneCoinvolte =0;
		
		allPwrOutgNerc =podao.getPowerOutagesNercList(nerc);
		Collections.sort(allPwrOutgNerc);
		
		
		
		cerca(parziale,mYears, mHours);
		
		return best;
	}

	private void cerca(List<PowerOutages> parziale, int maxYears, int maxHours) {
		// terminazione
		
		if(numPersoneCoinvolte<calcolaPersoneCoinvolte(parziale)) {
				numPersoneCoinvolte = calcolaPersoneCoinvolte(parziale);
				best = new ArrayList<>(parziale);

		}
		for(PowerOutages p : allPwrOutgNerc) {
			//Non posso avere duplicati nella lista parziale
			if(!parziale.contains(p)) {
				parziale.add(p);
				
				// nella ricorsione aggiungo solo una soluzione valida che non supera le condizioni del problema
				// vale come Terminazione
				if(controllaMaxAnni(parziale,maxYears) && controllaMaxOre(parziale,maxHours)) {
					cerca(parziale,maxYears,maxHours);
				}
				
				parziale.remove(parziale.size()-1);
			}
		
		}
		
		
	}

	private boolean controllaMaxOre(List<PowerOutages> parziale, int maxHours) {

		if(oreBlackOut(parziale)>maxHours)
			return false;
		
		return true;
	}

	private boolean controllaMaxAnni(List<PowerOutages> parziale, int maxYears) {
		
		if (parziale.size() >=2 ) {
			if((parziale.get(parziale.size()-1).getYear()-parziale.get(0).getYear())>maxYears) 
				return false;
		}
			
		return true;
	}

	private int oreBlackOut(List<PowerOutages> parziale) {
		int ore=0;
		for(PowerOutages p: parziale) {
			ore+= p.getOutageDuration();
		}
		return ore;
	}
	
	private Integer calcolaPersoneCoinvolte(List<PowerOutages> parziale) {
		int persone = 0;
		for(PowerOutages p: parziale) {
			persone += p.getCustomersAffected();
		}
		return persone;
	}

}
