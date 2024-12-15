package org.thoughtworks.movieBooking.events.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;
    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody AddEventRequestBody addEventRequestBody) throws Exception {
        log.info("Add event API called");
        System.out.println("Add event API called");

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
        log.info("Get api called");
        List<AllEventResponseDTO> responseDTO=eventService.getEvent();
        return ResponseEntity.ok(responseDTO);

    }
}