package de.einkaufsliste.repository;

import de.einkaufsliste.model.ListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
@Repository
public interface ListEntryRepository extends JpaRepository<ListEntry, Long> {
}

