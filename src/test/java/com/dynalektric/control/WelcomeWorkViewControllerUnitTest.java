package com.dynalektric.control;

import com.dynalektric.constants.SystemConstants;
import com.dynalektric.model.Model;
import com.dynalektric.model.repositories.general.GeneralRepo;
import com.dynalektric.model.repositories.general.GeneralRepoJSONImpl;
import com.dynalektric.model.repositories.project.Project;
import com.dynalektric.model.repositories.project.ProjectRepo;
import com.dynalektric.model.repositories.project.ProjectRepoJSONImpl;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WelcomeWorkViewControllerUnitTest {
    @Test
    @Order(1)
    public void shouldCreateProjectFileAndInsertIntoGeneralFile(){
        File createdFile = new File(SystemConstants.DATABASE_DIR + "SampleProject.json");
        GeneralRepo generalRepo = new GeneralRepoJSONImpl();
        try{
            WelcomeWorkViewController controller = new WelcomeWorkViewController();
            controller.createNewProject("SampleProject");
            assertTrue(createdFile.exists());
            List<String> createdProjectNames = generalRepo.getNamesOfAllProjectsCreated();
            assertTrue(createdProjectNames.contains("SampleProject"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            fail();
        }finally {
            createdFile.delete();
            List<String> createdProjectNames = generalRepo.getNamesOfAllProjectsCreated();
            createdProjectNames.remove("SampleProject");
            generalRepo.setNamesOfAllProjectCreated(createdProjectNames);
        }
    }

    @Test
    @Order(2)
    public void shouldDeleteProjectFileAndRemoveFromGeneralFile(){
        //Given
        WelcomeWorkViewController controller = new WelcomeWorkViewController();
        controller.createNewProject("SampleProject");
        File createdFile = new File(SystemConstants.DATABASE_DIR + "SampleProject.json");
        GeneralRepo general = new GeneralRepoJSONImpl();
        try{
            controller.deleteProjectByName("SampleProject");
            assertFalse(createdFile.exists());
            assertFalse(general.getNamesOfAllProjectsCreated().contains("SampleProject"));
        }catch(Exception e){
            fail();
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void shouldSaveProjectWithModifiedValuesFromModel(){
        //Given
        WelcomeWorkViewController welcomeController = new WelcomeWorkViewController();
        Control control = new Control();
        welcomeController.createNewProject("SampleProject");
        ProjectRepo repo = new ProjectRepoJSONImpl();
        Project project = repo.getProjectByName("SampleProject");
        project.inputs.something = "Updated Project Data";
        Model model = Model.getSingleton();
        model.loadNewProject(project);
        try{
            //Then
            control.saveProject();
            //Assert
            Project projectUpdated = repo.getProjectByName("SampleProject");
            assertEquals("Updated Project Data", projectUpdated.inputs.something);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            welcomeController.deleteProjectByName("SampleProject");
            model.dissolveModel();
        }
    }

    @Test
    @Order(4)
    public void openingProjectShouldLoadCorrectDataIntoModel() {
        // Arrange
        WelcomeWorkViewController controller = new WelcomeWorkViewController();
        controller.createNewProject("Project1");
        controller.createNewProject("Project2");

        ProjectRepo repo = new ProjectRepoJSONImpl();
        Project p1 = repo.getProjectByName("Project1");
        p1.inputs.something = "From p1";
        repo.updateProject(p1);

        Project p2 = repo.getProjectByName("Project2");
        p2.inputs.something = "From p2";
        repo.updateProject(p2);

        // Act
        try {
            controller.openProjectWithName("Project1");

            // Assert
            assertEquals("From p1", Model.getSingleton().getLoadedProject().inputs.something);
            assertEquals("Project1", Model.getSingleton().getGeneralRepo().getLoadedProjectName());

            controller.openProjectWithName("Project2");

            // Assert
            assertEquals("From p2", Model.getSingleton().getLoadedProject().inputs.something);
            assertEquals("Project2", Model.getSingleton().getGeneralRepo().getLoadedProjectName());

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {
            // Clean up
            controller.deleteProjectByName("Project1");
            controller.deleteProjectByName("Project2");
        }
    }

}
