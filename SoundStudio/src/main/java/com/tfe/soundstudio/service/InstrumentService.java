/**
 * 
 */
package com.tfe.soundstudio.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;
import com.tfe.soundstudio.repository.InstFamilyRepo;
import com.tfe.soundstudio.repository.InstrumentRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class InstrumentService {
	
	private final InstrumentRepo instrumentRepo;
	private final InstFamilyRepo instFamilyRepo;

	public InstrumentService(InstrumentRepo instrumentRepo, InstFamilyRepo instFamilyRepo) {
		super();
		this.instrumentRepo = instrumentRepo;
		this.instFamilyRepo = instFamilyRepo;
	}
	
	//for instrument
	
	/**
	 * @param instName
	 * @return
	 */
	
	 @Transactional(readOnly = true)
	 public Instrument findByInstName(String instName) {
		 Instrument result = instrumentRepo.findByInstName(instName);
		 return result;
	 }
	
	 @Transactional
	 public void saveInstrument (Instrument instrument) {
		 instrumentRepo.save(instrument);
	 }
	 
	 @Transactional
	 public void deleteAllInstruments () {
		 instrumentRepo.deleteAll();
	 }
	 
	 
	 //for InstFamily
	 
	 /**
	  * @param family
	  * @return
	  */
	 
	 @Transactional(readOnly=true)
		public InstFamily findByFamily(String family) {
			InstFamily result = instFamilyRepo.findByFamily(family);
			
			return result;
		}
	 
	 @Transactional(readOnly=true)
		public Optional<InstFamily> readByID(Long id) {
			Optional<InstFamily> result = instFamilyRepo.findById(id);
			
			return result;
		}
	 
	 @Transactional
	 public void deleteAllInstFamily () {
		 instFamilyRepo.deleteAll();
	 }

}
