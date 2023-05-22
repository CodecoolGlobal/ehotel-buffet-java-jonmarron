package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestNames;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GuestService {

    Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd);
    // TODO: randomly picked name, guest type, and reservation period - how? guest type and period ok, but name?
    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);
    // TODO - Why Set if the argument is a List?
    // TODO - Filter guests from the list whose date == date?
}
