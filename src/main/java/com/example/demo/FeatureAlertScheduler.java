package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class FeatureAlertScheduler {
    @Autowired
    private FeatureAlertRepository repository;
    @Autowired
    private JavaMailSender mailSender;

    private static final String[] FEATURES = {"Login", "Signup", "Dashboard", "Reports"};
    private static final String[] TEAMS = {"Alpha", "Beta", "Gamma"};
    // Replace with your own DL emails
    private static final String[] DL_EMAILS = {"<child-team-dl-email>"};
    private final Random random = new Random();

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void insertDummyAlertAndSendEmail() {
        FeatureAlert alert = new FeatureAlert();
        alert.setFeatureName(FEATURES[random.nextInt(FEATURES.length)]);
        alert.setVersion("v" + (random.nextInt(10) + 1) + ".0");
        alert.setTeam(TEAMS[random.nextInt(TEAMS.length)]);
        alert.setChildTeamDlEmail(DL_EMAILS[random.nextInt(DL_EMAILS.length)]);
        alert.setDate(LocalDateTime.now());
        repository.save(alert);
        System.out.println("[SCHEDULER] Inserted and emailed alert: " + alert.getFeatureName() + " to " + alert.getChildTeamDlEmail());
        sendEmail(alert);
    }

    public void sendEmail(FeatureAlert alert) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(alert.getChildTeamDlEmail());
        message.setSubject("Feature Alert: " + alert.getFeatureName());
        message.setText("Feature: " + alert.getFeatureName() + "\nVersion: " + alert.getVersion() + "\nTeam: " + alert.getTeam() + "\nDate: " + alert.getDate());
        mailSender.send(message);
    }
}
