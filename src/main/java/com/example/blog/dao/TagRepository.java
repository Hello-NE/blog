package com.example.blog.dao;

import com.example.blog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

    @Query("select t from Tag t where t.id <> ?1 and t.name = ?2")
    List<Tag> findByNameExceptSelf(Long id, String name);


}