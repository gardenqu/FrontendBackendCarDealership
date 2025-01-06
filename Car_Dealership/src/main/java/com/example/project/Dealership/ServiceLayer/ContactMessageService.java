package com.example.project.Dealership.ServiceLayer;

import com.example.project.Dealership.Entity.ContactMessages;
import com.example.project.Dealership.Entity.UserEntity;
import com.example.project.Dealership.Entity.Vehicle;
import com.example.project.Dealership.Repository.ContactMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ContactMessageService {

    private final ContactMessageRepo contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepo contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    // Create a new message
    public ContactMessages createMessage(ContactMessages contactMessage) {
        return contactMessageRepository.save(contactMessage);
    }

    // Get all messages for a specific vehicle
    public List<ContactMessages> getMessagesByVehicle(Vehicle vehicle) {
        return contactMessageRepository.findByVehicle(vehicle);
    }

    // Get all messages for a specific user
    public List<ContactMessages> getMessagesByUser(UserEntity user) {
        return contactMessageRepository.findByUserEntity(user);
    }

    // Get a specific message by ID
    public Optional<ContactMessages> getMessageById( int messageId) {
        return contactMessageRepository.findById(messageId);
    }

    // Update an existing message
    public ContactMessages updateMessage(int messageId, ContactMessages updatedMessage) {
        Optional<ContactMessages> existingMessageOpt = contactMessageRepository.findById(messageId);
        if (existingMessageOpt.isPresent()) {
            ContactMessages existingMessage = existingMessageOpt.get();
            existingMessage.setName(updatedMessage.getName());
            existingMessage.setEmail(updatedMessage.getEmail());
            existingMessage.setPhone(updatedMessage.getPhone());
            existingMessage.setMessage(updatedMessage.getMessage());
            return contactMessageRepository.save(existingMessage);
        } else {
            throw new RuntimeException("Message not found");
        }
    }

    // Delete a message by ID
    public void deleteMessage(int messageId) {
        contactMessageRepository.deleteById(messageId);
    }
}
