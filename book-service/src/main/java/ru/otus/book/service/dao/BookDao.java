package ru.otus.book.service.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.book.service.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Long> {

    @Modifying
    @Query("update Book e set e.title = :title where e.id = :id")
    void updateTitleById(Long id, String title);

}
