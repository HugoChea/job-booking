package com.jobBooking.JobBooking.model;

import com.jobBooking.JobBooking.enumeration.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "application_job")
public class Application {

    @Id
    Integer id;

    @Column(name = "company")
    String company;

    @Column(name = "position")
    String position;

    @Column(name = "stack")
    String stack;

    @Column(name = "description")
    String description;

    @Column(name = "link")
    String link;

    @Column(name = "contact")
    String contact;

    @Column(name = "comment")
    String comment;

    @Column(name = "date")
    LocalDate date;

    @Column(name = "status")
    Status status;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    Applicant applicant;

    public Application() {
    }

    public Application(String company, String position, String stack, String description, String link, String contact, String comment, LocalDate date, Status status) {
        this.company = company;
        this.position = position;
        this.stack = stack;
        this.description = description;
        this.link = link;
        this.contact = contact;
        this.comment = comment;
        this.date = date;
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
