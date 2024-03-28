package com.monapp.monapp.Service;


import com.monapp.monapp.Model.Conge;
import com.monapp.monapp.Model.Notification;
import com.monapp.monapp.Repository.NotRepo;
import com.monapp.monapp.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class NotService {

    @Autowired
    private NotRepo notRepo;
    @Autowired
    private chef_crud chef_crud;
    private JdbcTemplate jdbcTemplate;

    public List<Notification> affichernotifications(){
        return notRepo.findAll();
    }

    public String getSenderEmailFromNotification(int notificationId) {
        return notRepo.findSenderEmailByNotificationId(notificationId);
    }
    public String getRecipEmailFromNotification(int notificationId) {
        return notRepo.findRecipEmailByNotificationId(notificationId);
    }

    @Autowired
    public NotService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Notification getLatestNotification() {
        String query = "SELECT * FROM notification ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(query, new NotificationRowMapper());
    }

    private class NotificationRowMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notification notification = new Notification();
            notification.setId(rs.getInt("id"));
            notification.setSender_id(rs.getInt("sender_id"));
            notification.setRecipient_id(rs.getInt("recipient_id"));
            notification.setSubject(rs.getString("subject"));
            notification.setContent(rs.getString("content"));
            return notification;
        }
    }
}





