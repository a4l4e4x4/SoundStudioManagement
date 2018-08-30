package com.TFE.SoundStudio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.service.MusicianService;

@RunWith(SpringRunner.class)

@SpringBootTest
public class SoundStudioApplicationTests {
	
	@Autowired
	MusicianService musicianService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testInsert() {
		Musician testMus = new Musician();
		testMus.setName("test musician");
		testMus.setSurname("test surname");
		musicianService.saveMusician(testMus);
		Musician result = musicianService.getByName("test musician");
		//asserThat(result);
		//asserEquals("test surname", result.getSurname());
	}

}
