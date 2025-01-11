package com.example.Midnight.Snacker.repository;

import com.example.Midnight.Snacker.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
