package com.example.project.Dealership.Controller;

import com.example.project.Dealership.Entity.ContactMessages;
import com.example.project.Dealership.Entity.UserEntity;
import com.example.project.Dealership.Entity.Vehicle;
import com.example.project.Dealership.ServiceLayer.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class ContactMessageController {

    @Autowired

    private ContactMessageService contactMessageService;

    // Create a new message
    @PostMapping
    public ResponseEntity<ContactMessages> createMessage(@RequestBody ContactMessages contactMessage) {
        ContactMessages createdMessage = contactMessageService.createMessage(contactMessage);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    // Get all messages for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ContactMessages>> getMessagesByVehicle(@PathVariable int vehicleId) {
        Vehicle vehicle = new Vehicle(); // Assume you retrieve the vehicle by its ID
        vehicle.setVehicleid(vehicleId);
        List<ContactMessages> messages = contactMessageService.getMessagesByVehicle(vehicle);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get all messages for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContactMessages>> getMessagesByUser(@PathVariable int userId) {
        UserEntity user = new UserEntity(); // Assume you retrieve the user by its ID
        user.setUserid(userId);
        List<ContactMessages> messages = contactMessageService.getMessagesByUser(user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Get a specific message by ID
    @GetMapping("/{messageId}")
    public ResponseEntity<ContactMessages> getMessageById(@PathVariable int messageId) {
        Optional<ContactMessages> message = contactMessageService.getMessageById(messageId);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing message
    @PutMapping("/{messageId}")
    public ResponseEntity<ContactMessages> updateMessage(@PathVariable int messageId, @RequestBody ContactMessages updatedMessage) {
        try {
            ContactMessages updated = contactMessageService.updateMessage(messageId, updatedMessage);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a message by ID
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int messageId) {
        contactMessageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }


}
