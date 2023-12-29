package com.example.sms.repository;

import com.example.sms.MessageStatus;
import com.example.sms.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m.text from Message m where m.receiver.username = ?1 and m.status <> 'READ' order by m.created desc")
    List<String> findAllUnreadMessagesByReceiver(String username);

    List<Message> findByReceiver_usernameAndStatusOrderByCreatedDesc(String username, MessageStatus status);

    @Transactional
    @Modifying
    @Query("update Message m set m.status = ?2 where m in ?1")
    int updateStatus(List<Message> messages, MessageStatus read);
}
