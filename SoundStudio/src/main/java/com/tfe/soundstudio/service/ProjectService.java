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
		projectRepo.save(project, 10);
	}

	@Transactional
	public Iterable<Project> findAll() {
		Iterable<Project> result = projectRepo.findAll();
		return result;
	}

}
