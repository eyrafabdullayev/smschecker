package com.lsim.smschecker.repository;

import com.lsim.smschecker.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SmsRepository extends JpaRepository<Sms, Integer> {

    @Query(value = "select * from sms where Is_sent = ?1 and Insert_date >= DATE(NOW()) - INTERVAL 7 DAY", nativeQuery = true)
    List<Sms> specificUnAnsweredMessages(int status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update sms set Is_sent = 1 where Msisdn =:msisdn and Message_body =:messageBody", nativeQuery = true)
    void updateSpecificUnAnsweredMessage(@Param("msisdn") String msisdn, @Param("messageBody") String messageBody);

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into sms (Msisdn, Sender_name ,Message_body) values (:msisdn, :senderName, :messageBody)", nativeQuery = true)
    void insertUnAnsweredMessage(@Param("msisdn") String msisdn, @Param("senderName") String senderName, @Param("messageBody") String messageBody);
}
