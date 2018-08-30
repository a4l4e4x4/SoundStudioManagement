package com.TFE.SoundStudio;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Instrument;
import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.MusicianService;



@RunWith(SpringRunner.class)

@SpringBootTest(classes = com.tfe.soundstudio.SoundStudioApplication.class)

public class SoundStudioApplicationTests {
	
	@Autowired
	MusicianService musicianService;
	@Autowired 
	InstrumentService instrumentService;

	@Test
	public void contextLoads() {
	}
	

	@Test
	@Transactional
	public void testMusician() {
		Musician testMus = new Musician();
		testMus.setName("test musician");
		testMus.setSurname("test surname");
		musicianService.saveMusician(testMus);
		Musician result = musicianService.getByName("test musician");
		Assert.assertEquals("test surname", result.getSurname());
		Instrument inst = new Instrument();
		inst.setInstName("testflute");
		testMus.getInstruments().add(inst);
		Set<Instrument> instResult = testMus.getInstruments();
		Assert.assertEquals(instResult.contains(inst), true);
		
		instrumentService.deleteByInstName("testflute");
		musicianService.deleteByName("test musician");
		
	}

}
