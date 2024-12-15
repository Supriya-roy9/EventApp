package org.thoughtworks.movieBooking.events;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thoughtworks.movieBooking.events.repository.Event;
import org.thoughtworks.movieBooking.events.repository.EventRepository;
import org.thoughtworks.movieBooking.events.view.model.AddEventRequestBody;
import org.thoughtworks.movieBooking.events.view.model.AllEventResponseDTO;
import org.thoughtworks.movieBooking.exceptions.EventAlreadyExistsException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);
    @Autowired
    private EventRepository eventRepository;

    public void addEvent(AddEventRequestBody addEventRequestBody) throws Exception {
        try {
            log.info("Service 1");
            Optional<Event> alreadyAnEvent = eventRepository.findByName(addEventRequestBody.getName());
            log.info(alreadyAnEvent.isEmpty() ?"true":"false");
            if (alreadyAnEvent.isPresent()) {
                log.info("1");
                throw new EventAlreadyExistsException();

            }
            log.info("2");
            Event event = new Event(addEventRequestBody.getName(), addEventRequestBody.getLocation(), parseDate(addEventRequestBody.getEvent_date()), Integer.parseInt(addEventRequestBody.getCapacity()), Integer.parseInt(addEventRequestBody.getCapacity()), Double.parseDouble(addEventRequestBody.getPrice()));
            log.info("3");
            eventRepository.save(event);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    private LocalDate parseDate(String dateStr) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(dateStr, formatter);

        return localDate;
    }

    public void deleteEvent(String name) throws Exception {
        Optional<Event> event = eventRepository.findByName(name);

        if (event.isEmpty()) {
            throw new NoSuchElementException();
        }
        try {
            eventRepository.delete(event.get());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    public List<AllEventResponseDTO> getEvent() {

        List<Event> li = eventRepository.findAll();
        List<AllEventResponseDTO> responseDTOList = new ArrayList<>();
        for (Event e : li) {
            AllEventResponseDTO responseDTO = new AllEventResponseDTO(e.getName(), e.getLocation(), e.getEvent_date(), e.getCapacity(), e.getPrice(), e.getAvailability());
            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }
}