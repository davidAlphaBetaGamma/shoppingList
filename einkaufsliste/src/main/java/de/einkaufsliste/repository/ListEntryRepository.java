package de.einkaufsliste.repository;

import de.einkaufsliste.model.ListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListEntryRepository extends JpaRepository<ListEntry, Long> {
}

