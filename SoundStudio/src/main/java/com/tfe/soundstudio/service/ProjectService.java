/**
 * 
 */
package com.tfe.soundstudio.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfe.soundstudio.model.Project;
import com.tfe.soundstudio.repository.ProjectRepo;

/**
 * @author alex tolkmitt
 *
 */
@Service
public class ProjectService {
	
	private final ProjectRepo projectRepo;

	public ProjectService(ProjectRepo projectRepo) {
		super();
		this.projectRepo = projectRepo;
	}
	
	@Transactional
	public void saveProject(Project project) {
		projectRepo.save(project, 5);
	}

	@Transactional
	public Iterable<Project> findAll() {
		Iterable<Project> result = projectRepo.findAll(4);
		return result;
	}

	@Transactional
	public Project findById(Long id) {
		Project project = projectRepo.findById(id).orElseThrow(()->new RuntimeException("No such project"));
				
		return project;
	}

	@Transactional
	public void deleteById(Long id) {

		projectRepo.deleteById(id);
		
	}

	public void saveAllProjects(Iterable<Project> projToSave) {
		projectRepo.saveAll(projToSave);
		
	}

}
