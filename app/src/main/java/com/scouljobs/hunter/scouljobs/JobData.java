package com.scouljobs.hunter.scouljobs;

public class JobData {

    private int job_id;
    private String job_title;
    private String job_category;
    private String job_description;
    private String job_qualifications;
    private String job_requirements;
    private String created_at;
    private String updated_at;

    public JobData(int job_id, String job_title, String job_category, String job_description, String job_qualifications, String job_requirements, String created_at, String updated_at) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_category = job_category;
        this.job_description = job_description;
        this.job_qualifications = job_qualifications;
        this.job_requirements = job_requirements;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }


    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_qualifications() {
        return job_qualifications;
    }

    public void setJob_qualifications(String job_qualifications) {
        this.job_qualifications = job_qualifications;
    }

    public String getJob_requirements() {
        return job_requirements;
    }

    public void setJob_requirements(String job_requirements) {
        this.job_requirements = job_requirements;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
