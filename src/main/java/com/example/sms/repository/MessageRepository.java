package com.example.sms.repository;

import com.example.sms.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m.text from Message m where m.receiver.username = ?1 and m.status <> 'READ' order by m.created desc")
    List<String> findAllUnreadMessagesByReceiver(String username);
}
