/**
 * 
 */
package com.tfe.soundstudio.service;

import java.util.Optional;
import java.util.Set;

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
	 public Instrument findById(Long id) {
		 Instrument result = instrumentRepo.findById(id, 2).orElseThrow(()->new RuntimeException("No such instrument"));
		 return result;
	 }
	
	
	 @Transactional(readOnly = true)
	 public Instrument findByInstName(String instName) {
		 Instrument result = instrumentRepo.findByInstName(instName);
		 return result;
	 }
	 
	 @Transactional
	 public Set<Instrument> findByInstFamilyFamily(String instFamily){
		 Set<Instrument> instList = instrumentRepo.findByInstFamilyFamily(instFamily);
		 
		 return instList;
	 }
	 
	 @Transactional
	 public Iterable<Instrument> findAllInstruments(){
		 Iterable<Instrument> result = instrumentRepo.findAll();
		 return result;
	 }
	
	 @Transactional
	 public void saveInstrument (Instrument instrument) {
		 instrumentRepo.save(instrument);
	 }
	 
	 @Transactional
	 public void saveInstruments(Iterable<Instrument> instruments) {
		 //instrumentRepo.saveAll(instruments);
		 instrumentRepo.save(instruments, 2);
	 }
	 
	 @Transactional
	 public void deleteAllInstruments () {
		 instrumentRepo.deleteAll();
	 }
	 @Transactional
	 public void deleteById(Long id) {
			instrumentRepo.deleteById(id);
			
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
		public Optional<InstFamily> readById(Long id) {
			Optional<InstFamily> result = instFamilyRepo.findById(id, 3);
			
			return result;
		}
	 
	 @Transactional
	 public void deleteAllInstFamily () {
		 instFamilyRepo.deleteAll();
	 }
	 
	 @Transactional
	 public Iterable<InstFamily> findAllInstFamily () {
		 Iterable<InstFamily> result;
		 result = instFamilyRepo.findAll();
		 
		 return result;
		 
		 
	 }

	public Iterable<Instrument> findAll() {
		Iterable<Instrument> instruments = instrumentRepo.findAll();
		
		return instruments;
	}

	public Long deleteByInstName(String name) {
		Long id = instrumentRepo.deleteByInstName(name);
		return id;
	}

	

}
