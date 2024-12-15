package org.thoughtworks.movieBooking.events.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thoughtworks.movieBooking.events.EventService;
import org.thoughtworks.movieBooking.events.view.model.AddEventRequestBody;
import org.thoughtworks.movieBooking.events.view.model.AllEventResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/v1/events")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class EventController {

    @Autowired
    private EventService eventService;
    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody AddEventRequestBody addEventRequestBody) throws Exception {
        eventService.addEvent(addEventRequestBody);
        return ResponseEntity.ok("Event Added");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEvent(@RequestParam String name) throws Exception {
        eventService.deleteEvent(name);
        return ResponseEntity.ok("Event Deleted");
    }

    @GetMapping
    public ResponseEntity<List<AllEventResponseDTO>> getEvents(){
        List<AllEventResponseDTO> responseDTO=eventService.getEvent();
        return ResponseEntity.ok(responseDTO);

    }
}