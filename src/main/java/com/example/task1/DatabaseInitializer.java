package com.example.task1;

import com.example.task1.domain.Owner;
import com.example.task1.domain.Task;
import com.example.task1.repository.OwnerRepository;
import com.example.task1.repository.TaskRepository;
import com.example.task1.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final TaskRepository taskRepository;

    private final TaskService taskService;


    public DatabaseInitializer(OwnerRepository ownerRepository, TaskRepository taskRepository, TaskService taskService) {
        this.ownerRepository = ownerRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        /*// Creează un proprietar
        Owner owner = new Owner();
        owner.setName("Vlad Ene");

        // Creează câteva task-uri
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description for Task 1");
        task1.setRemainingEffort(5);
        task1.setOwner(owner);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description for Task 2");
        task2.setRemainingEffort(3);
        task2.setOwner(owner);

        // Adaugă task-urile la lista de task-uri a proprietarului
        owner.getTasks().add(task1);
        owner.getTasks().add(task2);

        // Salvează proprietarul (cascading va salva și task-urile)
        ownerRepository.save(owner);

        System.out.println("Datele au fost salvate în baza de date."); */

        Owner owner = ownerRepository.findByName("Andreas Faur");

        owner.setEmail("andreasfaur@gmail.com");
        ownerRepository.save(owner);

        owner = ownerRepository.findByName("Vlad Ene");

        owner.setEmail("vladene@gmail.com");
        ownerRepository.save(owner);
    }
}
