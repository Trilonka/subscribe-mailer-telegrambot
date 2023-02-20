package com.github.trilonka.subscribemailertelegrambot.repository;

import com.github.trilonka.subscribemailertelegrambot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}
