package ru.otus.book.service.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.book.service.model.Author;

public interface AuthorDao extends CrudRepository<Author, Long> {

    @Modifying
    @Query("update Author e set e.name = :name where e.id = :id")
    void updateNameById(Long id, String name);

}
