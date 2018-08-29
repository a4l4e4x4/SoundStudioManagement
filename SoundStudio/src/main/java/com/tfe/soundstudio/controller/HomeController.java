/**
 * 
 */
package com.tfe.soundstudio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfe.soundstudio.model.Client;
import com.tfe.soundstudio.model.Contact;
import com.tfe.soundstudio.model.Engineer;
import com.tfe.soundstudio.model.InstFamily;
import com.tfe.soundstudio.model.Instrument;
import com.tfe.soundstudio.model.Musician;
import com.tfe.soundstudio.model.ReadFile;
import com.tfe.soundstudio.model.Track;
import com.tfe.soundstudio.model.TrackObject;
import com.tfe.soundstudio.service.ClientService;
import com.tfe.soundstudio.service.EngineerService;
import com.tfe.soundstudio.service.InstrumentService;
import com.tfe.soundstudio.service.MusicianService;
import com.tfe.soundstudio.service.TrackService;

/**
 * @author alex tolkmitt
 *
 */
@Controller
public class HomeController {

	private final InstrumentService instService;
	private final TrackService trackService;
	private final ClientService clientService;
	private final MusicianService musicianService;
	private final EngineerService engineerService;

	

	public HomeController(InstrumentService instService, TrackService trackService, ClientService clientService,
			MusicianService musicianService, EngineerService engineerService) {
		super();
		this.instService = instService;
		this.trackService = trackService;
		this.clientService = clientService;
		this.musicianService = musicianService;
		this.engineerService = engineerService;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String index(Model model) {

		//model.addAttribute("message", message);

		return "home";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save() {
		Instrument instrument = new Instrument();
		instrument.setInstName("flute");
		InstFamily instFamily = new InstFamily();
		instFamily.setFamily("woodwinds");
		instrument.setInstFamily(instFamily);

		/*
		 * Set<Instrument> instruments = new HashSet<Instrument>();
		 * instruments.add(instrument);
		 * 
		 * InstFamily instFamily = new InstFamily(); instFamily.setFamily("woodwinds");
		 * instFamily.setInstruments(instruments);
		 */

		instService.saveInstrument(instrument);

		return "home";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read() {
		Optional<InstFamily> instFamily = instService.readById(1L);
		System.out.println(instFamily.get().getFamily());
		return "home";
	}

	@RequestMapping(value = "/deleteInstruments")
	public String deleteInstruments() {
		instService.deleteAllInstruments();

		return "home";
	}

	@RequestMapping(value = "/deleteInstFamily")
	public String deleteInstFamily() {
		instService.deleteAllInstFamily();

		return "home";
	}

	@RequestMapping(value = "addTracks")
	public String addTracks() {
		Track track01 = new Track();
		track01.setNumber(1);
		track01.setName("new_track");

		trackService.saveTrack(track01);

		// System.out.println(track01.getName());

		return "home";
	}

	@RequestMapping(value = "addTrackObjects")
	public String addTrackObjects() {
		/*
		 * TrackObject tobj01 = new TrackObject(); tobj01.setName("new_object");
		 * List<Track> trackList = new ArrayList<Track>(); Track track01 =
		 * trackService.findByName("new_track"); trackList.add(track01);
		 * System.out.println(track01.getName()); tobj01.setTrackList(trackList);
		 * trackService.saveTrackObject(tobj01);
		 */

		return "home";
	}

	@RequestMapping(value = "deleteTandTob")
	public String deleteTandTob() {
		trackService.deleteAll();

		return "home";
	}

	@RequestMapping(value = "scanner")
	public String scanner() {
		ReadFile newImport = new ReadFile();
		newImport.ScanIt("/home/a4l4e4x4/TFE_ISFCE/TFE_Project_Spring/tracktest.txt");

		/*
		 * System.out.println(newImport.getTrackObjects().get(0).getName());
		 * System.out.println(newImport.getTrackObjects().get(1).getName());
		 * System.out.println(newImport.getTrackObjects().get(2).getName());
		 */
		List<Track> tracks = new ArrayList<>();
		tracks = newImport.getTracks();
		for (Track onetrack : tracks) {
			// System.out.println(onetrack.getName());
			for (TrackObject tobj : onetrack.getObjectList()) {
				System.out.println(tobj.getStarttime());
			}

		}

		return "index";
	}

	@RequestMapping(value = "addAllInstruments")
	public String addAllInstruments() {

		List<Instrument> instruments = new ArrayList<>();

		// Instrument Families

		InstFamily woodwinds = new InstFamily();
		woodwinds.setFamily("woodwinds");

		InstFamily strings = new InstFamily();
		strings.setFamily("strings");

		InstFamily brass = new InstFamily();
		brass.setFamily("brass");

		InstFamily percussion = new InstFamily();
		percussion.setFamily("percussion");

		InstFamily keyboards = new InstFamily();
		keyboards.setFamily("keyboards");

		InstFamily voices = new InstFamily();
		voices.setFamily("voices");

		// Woodwinds

		Instrument flute = new Instrument();
		flute.setInstName("flute");
		flute.setInstFamily(woodwinds);
		instruments.add(flute);

		Instrument piccolo = new Instrument();
		piccolo.setInstName("piccolo");
		piccolo.setInstFamily(woodwinds);
		instruments.add(piccolo);

		Instrument oboe = new Instrument();
		oboe.setInstName("oboe");
		oboe.setInstFamily(woodwinds);
		instruments.add(oboe);

		Instrument bassoon = new Instrument();
		bassoon.setInstName("bassoon");
		bassoon.setInstFamily(woodwinds);
		instruments.add(bassoon);

		Instrument clarinet = new Instrument();
		clarinet.setInstName("clarinet");
		clarinet.setInstFamily(woodwinds);
		instruments.add(clarinet);

		// Brass

		Instrument trumpet = new Instrument();
		trumpet.setInstName("trumpet");
		trumpet.setInstFamily(brass);
		instruments.add(trumpet);

		Instrument french_horn = new Instrument();
		french_horn.setInstName("french horn");
		french_horn.setInstFamily(brass);
		instruments.add(french_horn);

		Instrument trombone = new Instrument();
		trombone.setInstName("trombone");
		trombone.setInstFamily(brass);
		instruments.add(trombone);

		Instrument tuba = new Instrument();
		tuba.setInstName("tuba");
		tuba.setInstFamily(brass);
		instruments.add(tuba);

		// Strings

		Instrument violin = new Instrument();
		violin.setInstName("violin");
		violin.setInstFamily(strings);
		instruments.add(violin);

		Instrument viola = new Instrument();
		viola.setInstName("viola");
		viola.setInstFamily(strings);
		instruments.add(viola);

		Instrument cello = new Instrument();
		cello.setInstName("cello");
		cello.setInstFamily(strings);
		instruments.add(cello);

		Instrument double_bass = new Instrument();
		double_bass.setInstName("double bass");
		double_bass.setInstFamily(strings);
		instruments.add(double_bass);

		Instrument ac_guitar = new Instrument();
		ac_guitar.setInstName("acoustic guitar");
		ac_guitar.setInstFamily(strings);
		instruments.add(ac_guitar);

		Instrument el_guitar = new Instrument();
		el_guitar.setInstName("electric guitar");
		el_guitar.setInstFamily(strings);
		instruments.add(el_guitar);

		Instrument harp = new Instrument();
		harp.setInstName("harp");
		harp.setInstFamily(strings);
		instruments.add(harp);

		// Keyboards

		Instrument piano = new Instrument();
		piano.setInstName("piano");
		piano.setInstFamily(keyboards);
		instruments.add(piano);

		Instrument celesta = new Instrument();
		celesta.setInstName("piano");
		celesta.setInstFamily(keyboards);
		instruments.add(celesta);

		Instrument clavichord = new Instrument();
		clavichord.setInstName("clavichord");
		clavichord.setInstFamily(keyboards);
		instruments.add(clavichord);

		Instrument accordion = new Instrument();
		accordion.setInstName("accordion");
		accordion.setInstFamily(keyboards);
		instruments.add(accordion);

		// Percussion

		Instrument timpani = new Instrument();
		timpani.setInstName("timpani");
		timpani.setInstFamily(percussion);
		instruments.add(timpani);

		Instrument cymbals = new Instrument();
		cymbals.setInstName("cymbals");
		cymbals.setInstFamily(percussion);
		instruments.add(cymbals);

		Instrument snare = new Instrument();
		snare.setInstName("snare");
		snare.setInstFamily(percussion);
		instruments.add(snare);

		Instrument xylophone = new Instrument();
		xylophone.setInstName("xylophone");
		xylophone.setInstFamily(percussion);
		instruments.add(xylophone);

		Instrument drums = new Instrument();
		drums.setInstName("drums");
		drums.setInstFamily(percussion);
		instruments.add(drums);

		// Voices

		Instrument soprano = new Instrument();
		soprano.setInstName("soprano");
		soprano.setInstFamily(voices);
		instruments.add(soprano);

		Instrument alto = new Instrument();
		alto.setInstName("alto");
		alto.setInstFamily(voices);
		instruments.add(alto);

		Instrument tenor = new Instrument();
		tenor.setInstName("tenor");
		tenor.setInstFamily(voices);
		instruments.add(tenor);

		Instrument baritone = new Instrument();
		baritone.setInstName("baritone");
		baritone.setInstFamily(voices);
		instruments.add(baritone);

		Instrument basso = new Instrument();
		basso.setInstName("basso");
		basso.setInstFamily(voices);
		instruments.add(basso);

		Instrument voice_over = new Instrument();
		voice_over.setInstName("voice_over");
		voice_over.setInstFamily(voices);
		instruments.add(voice_over);

		Iterable<Instrument> instsToSave = instruments;
		instService.saveInstruments(instsToSave);

		return "home";
	}

	@RequestMapping(value = "addAllClients")
	public String addAllClients() {
		List<Client> clients = new ArrayList<>();

		Client me = new Client();
		me.setName("me");
		me.setDescription("MyProjects");
		me.setWebsite("www.alex.com");
		Contact mec = new Contact();
		mec.setContactname("alex");
		mec.setContactsurname("tolkmitt");
		mec.setAddress("Rue Artan, 110");
		mec.setCity("Brussels");
		mec.setPostcode("1030");
		mec.setCity("Brussels");
		mec.setEmail("alex.tolkmitt@gmail.com");
		mec.setPhone("0484 919413");

		me.setContact(mec);
		clients.add(me);

		Client laVita = new Client();
		laVita.setName("La Vita e Media");
		laVita.setDescription("Advertisement");
		laVita.setWebsite("www.lavitaemedia.be");
		Contact laVitacont = new Contact();
		laVitacont.setContactname("Christian");
		laVitacont.setContactsurname("Rodrigues");
		laVitacont.setAddress("Rue Neuve, 11");
		laVitacont.setCity("Brussels");
		laVitacont.setCountry("Belgium");
		laVitacont.setEmail("christian@lavitaemedia.be");
		laVitacont.setPhone("+32 475 707 369");

		laVita.setContact(laVitacont);
		clients.add(laVita);

		Client fred = new Client();
		fred.setName("Frederic");
		fred.setDescription("Film");
		fred.setWebsite("www.inguz.be");
		Contact fredc = new Contact();
		fredc.setContactname("Frederic");
		fredc.setContactsurname("Fournelle");
		fredc.setAddress("Rue de la Paix, 21");
		fredc.setCity("Brussels");
		fredc.setPostcode("1030");
		fredc.setCountry("Belgium");
		fredc.setEmail("fred@inguz.be");
		fredc.setPhone("+32 487 455663");

		fred.setContact(fredc);
		clients.add(fred);

		Client isfce = new Client();
		isfce.setName("ISFCE");
		isfce.setDescription("Institut Supérieur de Formation Continue d'Etterbeek");
		isfce.setWebsite("www.isfce.org");
		Contact isfc = new Contact();
		isfc.setContactname("Didier");
		isfc.setContactsurname("Van Oudenhove");
		isfc.setAddress("Rue Joseph Buedts 14");
		isfc.setPostcode("1040");
		isfc.setCity("Brussels");
		isfc.setPhone("02.647.25.69");
		isfc.setEmail("didier@isfce.org");

		isfce.setContact(isfc);
		clients.add(isfce);

		Iterable<Client> clientsToSave = clients;
		clientService.saveAllClients(clientsToSave);
		return "home";
	}

	@RequestMapping(value = "addAllMusicians")
	public String addAllMusicians() {

		List<Musician> musicians = new ArrayList<>();

		Musician a = new Musician();
		a.setName("Edmundo");
		a.setSurname("Carneiro");
		Contact b = new Contact();
		b.setContactname("Edmundo");
		b.setContactsurname("Carneiro");
		b.setCity("Paris");
		b.setCountry("France");

		a.setContact(b);
		musicians.add(a);

		Musician c = new Musician();
		c.setName("Alex");
		c.setSurname("Tolkmitt");
		Contact d = new Contact();
		d.setContactname("Alex");
		d.setContactsurname("Tolkmitt");
		d.setCity("Brussels");
		d.setCountry("Belgium");

		c.setContact(d);
		musicians.add(c);

		Musician e = new Musician();
		e.setName("Clovis");
		e.setSurname("Camargo");
		Contact f = new Contact();
		f.setContactname("Clovis");
		f.setContactsurname("Camargo");
		f.setCity("Sao Paulo");
		f.setCountry("Brazil");

		e.setContact(f);
		musicians.add(e);

		Musician g = new Musician();
		g.setName("Cacau");
		g.setSurname("de Queiroz");
		Contact h = new Contact();
		h.setContactname("Cláudio");
		h.setContactsurname("de Queiroz");
		h.setCity("Paris");
		h.setCountry("France");

		g.setContact(h);
		musicians.add(g);

		Musician i = new Musician();
		i.setName("Klaus");
		i.setSurname("Schmidt");
		Contact j = new Contact();
		j.setContactname("Klaus");
		j.setContactsurname("Schmidt");
		j.setCity("New York");
		j.setCountry("USA");

		i.setContact(j);
		musicians.add(i);

		Musician k = new Musician();
		k.setName("Didier");
		k.setSurname("Van Oudenhove");
		Contact l = new Contact();
		l.setContactname("Didier");
		l.setContactsurname("Van Oudenhove");
		l.setCity("Brussels");
		l.setCountry("Belgium");

		k.setContact(l);
		musicians.add(k);

		Iterable<Musician> musToSave = musicians;
		musicianService.saveAllMusicians(musToSave);
		return "home";
	}

	@RequestMapping(value = "addAll")
	public String addAll() {
		List<Instrument> instruments = new ArrayList<>();

		// Instrument Families

		InstFamily woodwinds = new InstFamily();
		woodwinds.setFamily("woodwinds");

		InstFamily strings = new InstFamily();
		strings.setFamily("strings");

		InstFamily brass = new InstFamily();
		brass.setFamily("brass");

		InstFamily percussion = new InstFamily();
		percussion.setFamily("percussion");

		InstFamily keyboards = new InstFamily();
		keyboards.setFamily("keyboards");

		InstFamily voices = new InstFamily();
		voices.setFamily("voices");

		// Woodwinds

		Instrument flute = new Instrument();
		flute.setInstName("flute");
		flute.setInstFamily(woodwinds);
		instruments.add(flute);

		Instrument piccolo = new Instrument();
		piccolo.setInstName("piccolo");
		piccolo.setInstFamily(woodwinds);
		instruments.add(piccolo);

		Instrument oboe = new Instrument();
		oboe.setInstName("oboe");
		oboe.setInstFamily(woodwinds);
		instruments.add(oboe);

		Instrument bassoon = new Instrument();
		bassoon.setInstName("bassoon");
		bassoon.setInstFamily(woodwinds);
		instruments.add(bassoon);

		Instrument clarinet = new Instrument();
		clarinet.setInstName("clarinet");
		clarinet.setInstFamily(woodwinds);
		instruments.add(clarinet);

		// Brass

		Instrument trumpet = new Instrument();
		trumpet.setInstName("trumpet");
		trumpet.setInstFamily(brass);
		instruments.add(trumpet);

		Instrument french_horn = new Instrument();
		french_horn.setInstName("french horn");
		french_horn.setInstFamily(brass);
		instruments.add(french_horn);

		Instrument trombone = new Instrument();
		trombone.setInstName("trombone");
		trombone.setInstFamily(brass);
		instruments.add(trombone);

		Instrument tuba = new Instrument();
		tuba.setInstName("tuba");
		tuba.setInstFamily(brass);
		instruments.add(tuba);

		// Strings

		Instrument violin = new Instrument();
		violin.setInstName("violin");
		violin.setInstFamily(strings);
		instruments.add(violin);

		Instrument viola = new Instrument();
		viola.setInstName("viola");
		viola.setInstFamily(strings);
		instruments.add(viola);

		Instrument cello = new Instrument();
		cello.setInstName("cello");
		cello.setInstFamily(strings);
		instruments.add(cello);

		Instrument double_bass = new Instrument();
		double_bass.setInstName("double bass");
		double_bass.setInstFamily(strings);
		instruments.add(double_bass);

		Instrument ac_guitar = new Instrument();
		ac_guitar.setInstName("acoustic guitar");
		ac_guitar.setInstFamily(strings);
		instruments.add(ac_guitar);

		Instrument el_guitar = new Instrument();
		el_guitar.setInstName("electric guitar");
		el_guitar.setInstFamily(strings);
		instruments.add(el_guitar);

		Instrument harp = new Instrument();
		harp.setInstName("harp");
		harp.setInstFamily(strings);
		instruments.add(harp);

		// Keyboards

		Instrument piano = new Instrument();
		piano.setInstName("piano");
		piano.setInstFamily(keyboards);
		instruments.add(piano);

		Instrument celesta = new Instrument();
		celesta.setInstName("piano");
		celesta.setInstFamily(keyboards);
		instruments.add(celesta);

		Instrument clavichord = new Instrument();
		clavichord.setInstName("clavichord");
		clavichord.setInstFamily(keyboards);
		instruments.add(clavichord);

		Instrument accordion = new Instrument();
		accordion.setInstName("accordion");
		accordion.setInstFamily(keyboards);
		instruments.add(accordion);

		// Percussion

		Instrument timpani = new Instrument();
		timpani.setInstName("timpani");
		timpani.setInstFamily(percussion);
		instruments.add(timpani);

		Instrument cymbals = new Instrument();
		cymbals.setInstName("cymbals");
		cymbals.setInstFamily(percussion);
		instruments.add(cymbals);

		Instrument snare = new Instrument();
		snare.setInstName("snare");
		snare.setInstFamily(percussion);
		instruments.add(snare);

		Instrument xylophone = new Instrument();
		xylophone.setInstName("xylophone");
		xylophone.setInstFamily(percussion);
		instruments.add(xylophone);

		Instrument drums = new Instrument();
		drums.setInstName("drums");
		drums.setInstFamily(percussion);
		instruments.add(drums);

		// Voices

		Instrument soprano = new Instrument();
		soprano.setInstName("soprano");
		soprano.setInstFamily(voices);
		instruments.add(soprano);

		Instrument alto = new Instrument();
		alto.setInstName("alto");
		alto.setInstFamily(voices);
		instruments.add(alto);

		Instrument tenor = new Instrument();
		tenor.setInstName("tenor");
		tenor.setInstFamily(voices);
		instruments.add(tenor);

		Instrument baritone = new Instrument();
		baritone.setInstName("baritone");
		baritone.setInstFamily(voices);
		instruments.add(baritone);

		Instrument basso = new Instrument();
		basso.setInstName("basso");
		basso.setInstFamily(voices);
		instruments.add(basso);

		Instrument voice_over = new Instrument();
		voice_over.setInstName("voice_over");
		voice_over.setInstFamily(voices);
		instruments.add(voice_over);

		Iterable<Instrument> instsToSave = instruments;
		instService.saveInstruments(instsToSave);

		List<Client> clients = new ArrayList<>();

		Client me = new Client();
		me.setName("me");
		me.setDescription("MyProjects");
		me.setWebsite("www.alex.com");
		Contact mec = new Contact();
		mec.setContactname("alex");
		mec.setContactsurname("tolkmitt");
		mec.setAddress("Rue Artan, 110");
		mec.setCity("Brussels");
		mec.setPostcode("1030");
		mec.setCity("Brussels");
		mec.setEmail("alex.tolkmitt@gmail.com");
		mec.setPhone("0484 919413");

		me.setContact(mec);
		clients.add(me);

		Client laVita = new Client();
		laVita.setName("La Vita e Media");
		laVita.setDescription("Advertisement");
		laVita.setWebsite("www.lavitaemedia.be");
		Contact laVitacont = new Contact();
		laVitacont.setContactname("Christian");
		laVitacont.setContactsurname("Rodrigues");
		laVitacont.setAddress("Rue Neuve, 11");
		laVitacont.setCity("Brussels");
		laVitacont.setCountry("Belgium");
		laVitacont.setEmail("christian@lavitaemedia.be");
		laVitacont.setPhone("+32 475 707 369");

		laVita.setContact(laVitacont);
		clients.add(laVita);

		Client fred = new Client();
		fred.setName("Frederic");
		fred.setDescription("Film");
		fred.setWebsite("www.inguz.be");
		Contact fredc = new Contact();
		fredc.setContactname("Frederic");
		fredc.setContactsurname("Fournelle");
		fredc.setAddress("Rue de la Paix, 21");
		fredc.setCity("Brussels");
		fredc.setPostcode("1030");
		fredc.setCountry("Belgium");
		fredc.setEmail("fred@inguz.be");
		fredc.setPhone("+32 487 455663");

		fred.setContact(fredc);
		clients.add(fred);

		Client isfce = new Client();
		isfce.setName("ISFCE");
		isfce.setDescription("Institut Supérieur de Formation Continue d'Etterbeek");
		isfce.setWebsite("www.isfce.org");
		Contact isfc = new Contact();
		isfc.setContactname("Didier");
		isfc.setContactsurname("Van Oudenhove");
		isfc.setAddress("Rue Joseph Buedts 14");
		isfc.setPostcode("1040");
		isfc.setCity("Brussels");
		isfc.setPhone("02.647.25.69");
		isfc.setEmail("didier@isfce.org");

		isfce.setContact(isfc);
		clients.add(isfce);

		Iterable<Client> clientsToSave = clients;
		clientService.saveAllClients(clientsToSave);

		List<Musician> musicians = new ArrayList<>();

		Musician a = new Musician();
		a.setName("Edmundo");
		a.setSurname("Carneiro");
		Contact b = new Contact();
		b.setContactname("Edmundo");
		b.setContactsurname("Carneiro");
		b.setCity("Paris");
		b.setCountry("France");

		a.setContact(b);
		musicians.add(a);

		Musician c = new Musician();
		c.setName("Alex");
		c.setSurname("Tolkmitt");
		Contact d = new Contact();
		d.setContactname("Alex");
		d.setContactsurname("Tolkmitt");
		d.setCity("Brussels");
		d.setCountry("Belgium");

		c.setContact(d);
		musicians.add(c);

		Musician e = new Musician();
		e.setName("Clovis");
		e.setSurname("Camargo");
		Contact f = new Contact();
		f.setContactname("Clovis");
		f.setContactsurname("Camargo");
		f.setCity("Sao Paulo");
		f.setCountry("Brazil");

		e.setContact(f);
		musicians.add(e);

		Musician g = new Musician();
		g.setName("Cacau");
		g.setSurname("de Queiroz");
		Contact h = new Contact();
		h.setContactname("Cláudio");
		h.setContactsurname("de Queiroz");
		h.setCity("Paris");
		h.setCountry("France");

		g.setContact(h);
		musicians.add(g);

		Musician i = new Musician();
		i.setName("Klaus");
		i.setSurname("Schmidt");
		Contact j = new Contact();
		j.setContactname("Klaus");
		j.setContactsurname("Schmidt");
		j.setCity("New York");
		j.setCountry("USA");

		i.setContact(j);
		musicians.add(i);

		Musician k = new Musician();
		k.setName("Didier");
		k.setSurname("Van Oudenhove");
		Contact l = new Contact();
		l.setContactname("Didier");
		l.setContactsurname("Van Oudenhove");
		l.setCity("Brussels");
		l.setCountry("Belgium");

		k.setContact(l);
		musicians.add(k);

		Iterable<Musician> musToSave = musicians;
		musicianService.saveAllMusicians(musToSave);
		
		List <Engineer> engineers = new ArrayList<>();
		
		Engineer e1 = new Engineer();
		e1.setName("me");
		e1.setSurname("tolkmitt");
		Contact e1c = new Contact();
		e1c.setContactname("alex");
		e1c.setContactsurname("tolkmitt");
		e1c.setAddress("Rue Artan, 110");
		e1c.setCity("Brussels");
		e1c.setPostcode("1030");
		e1c.setCity("Brussels");
		e1c.setEmail("alex.tolkmitt@gmail.com");
		e1c.setPhone("0484 919413");

		e1.setContact(e1c);
		engineers.add(e1);
		
		Engineer fredE = new Engineer();
		fredE.setName("Frederic");
		fredE.setSurname("Fournelle");
		Contact fredEc = new Contact();
		fredEc.setContactname("Frederic");
		fredEc.setContactsurname("Fournelle");
		fredEc.setAddress("Rue de la Paix, 21");
		fredEc.setCity("Brussels");
		fredEc.setPostcode("1030");
		fredEc.setCountry("Belgium");
		fredEc.setEmail("fred@inguz.be");
		fredEc.setPhone("+32 487 455663");

		fredE.setContact(fredEc);
		engineers.add(fredE);
		
		Iterable<Engineer> engToSave = engineers;
		engineerService.saveAllEngineers(engToSave);

		return "home";
	}

}
