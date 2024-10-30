package com.example.blog.dao;


import com.example.blog.po.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {
}
