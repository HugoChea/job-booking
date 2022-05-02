package com.jobBooking.JobBooking.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "applicant")
@PrimaryKeyJoinColumn(name = "id")
public class Applicant extends User{

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy="applicant")
    private Set<Application> applicationList;

    public Applicant() {
    }

    public Applicant(String username, String email, String password, String photoUrl, Set<Application> applicationList) {
        super(username, email, password);
        this.photoUrl = photoUrl;
        this.applicationList = applicationList;
    }
}
